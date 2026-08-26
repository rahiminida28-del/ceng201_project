public class SubmissionTimeline {
    //burda node structure Avl tree  left right , seklinde kullanir
    private static class Node {
        Submission data;
        Node left;
        Node right;
        int height;

        Node(Submission data){
            this.data=data;
            this.right=null;
            this.left=null;
            this.height=1;
        }
    }

    private Node root;
    private int visitedNodes;

    public SubmissionTimeline(){
        root=null;
    }
    //from there we will find height
    public int getHeight(Node node){  //tree and structure toplam yuksekligine ve derinligini dondurur
        if (node == null) {
            return 0;
        }

        return node.height; //node tree mevcut yukseklik degerini ifade eder
    }
    public void updateHeight(Node node){

        int leftHeight= getHeight(node.left);
        int rightHeight= getHeight(node.right);

        node.height= 1 + Math.max(leftHeight,rightHeight);
       //Updates the nodes height to 1 plus the max height of its left and right subtrees.
    }
    private int getBalance(Node node){ //node left and  right subtree yükseklik farkını (balance) hesaplar

        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right); //if +-2 , need to make it rotation.
    }
    private Node rotateRight(Node y){   //Rotates the node right to balance the tree.

        Node x= y.left;
        Node temp=x.right;

        x.right = y;

        y.left=temp;

        updateHeight(y);
        updateHeight(x);

        return x;
    }
    private Node rotateLeft(Node x){   //left rotation

        Node y= x.right;
        Node temp=y.left;
        y.left = x;
        x.right=temp;
        updateHeight(x);
        updateHeight(y);

        return y;
    }
    public void insert(Submission sub) {   //start the process from the root node
        root = insertRecursive(root, sub);
    }

    private Node insertRecursive(Node current, Submission sub){
        //Yeni veriyi tree dogru yere eklemek için kullanılır
        if (current == null) {
            return new Node(sub);
        }
        if (sub.getTimestampMs() < current.data.getTimestampMs()) {
            current.left = insertRecursive(current.left, sub);
        }
        else {
            current.right = insertRecursive(current.right, sub);
        }
        updateHeight(current); //update height

        int balanceScore=getBalance(current); //we can check balance

        if (balanceScore > 1 && sub.getTimestampMs() < current.left.data.getTimestampMs()) {
            //there we can use ll case and we can apply right rotation
            return rotateRight(current);
        }

        if (balanceScore < -1 && sub.getTimestampMs() > current.right.data.getTimestampMs()) {
         //there we can use rr case and we cn apply Left Rotation
            return rotateLeft(current);
        }

        if (balanceScore > 1 && sub.getTimestampMs() > current.left.data.getTimestampMs()) { //this is lr case

            current.left = rotateLeft(current.left);

            return rotateRight(current);
        }

         //balance<-1 ,1.sart ve cur right 2sart
        if (balanceScore < -1 && sub.getTimestampMs() < current.right.data.getTimestampMs()) { //rl case
           //iki sartin ayni anda saglanmasi icin
            current.right = rotateRight(current.right);

            return rotateLeft(current);
        }

        return current;

    }

    public int height() {
        return getHeight(root);
    }

    long rootTimestamp() {

        if (root == null) {
            return -1;
        }

        return root.data.getTimestampMs();
    }


    private int countBetween(Node current, long t1, long t2) {

        if (current == null) {
            return 0;
        }

        long time = current.data.getTimestampMs();

        int count = 0;

        if (time > t1) {  //go left side
            count += countBetween(current.left, t1, t2);
        }

        if (time >= t1 && time <= t2) {
            count++;
        }

        if (time < t2) {  //go right side
            count += countBetween(current.right, t1, t2);
        }

        return count;
    }

    private int fillBetween(Node current, long t1, long t2, Submission[] result, int index) {

        if (current == null) {
            return index;
        }

        visitedNodes++;

        long time = current.data.getTimestampMs();

        if (time > t1) {index = fillBetween(current.left, t1, t2, result, index);
        }

        if (time >= t1 && time <= t2) {

            result[index] = current.data;
            index++;
        }

        if (time < t2) {
            index = fillBetween(current.right, t1, t2, result, index);
        }

        return index;
    }

    public Submission[] submittedBetween(long t1, long t2) {

        int count = countBetween(root, t1, t2);

        Submission[] result = new Submission[count];

        visitedNodes = 0;

        fillBetween(root, t1, t2, result, 0);

        return result;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }





}




public class PlainSubmissionBST {
    private static class Node {   //Node Structure like  Tree (right or left)
            Submission data;
            Node left;
            Node right;

            Node(Submission data){
                this.data=data;
                this.right=null;
                this.left=null;
            }
        }

        private Node root;
        private int treeHeight;

        public PlainSubmissionBST(){
            root=null;
            treeHeight=0;
        }

        public void insert(Submission sub) {     //process insertation
            Node newNode = new Node(sub);
            if (root == null) {
                root = newNode;
                treeHeight = 1;
                return;
            }
            Node current = root;
            int depth = 1;

            while (true) {

                depth++;

                if (sub.getTimestampMs() < current.data.getTimestampMs()) {

                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    }
                    current = current.left;
                }
                else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    }
                    current = current.right;
                }
            }
            if (depth > treeHeight) {
                treeHeight = depth;
            }
        }

        public int height(){
            return treeHeight;   //Return tree height
        }





    }



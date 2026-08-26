public class SubmissionRegistry {
    private static class Node{   //node str wth single linked list
        Submission submission;
        Node next;

        Node(Submission submission){
            this.submission=submission;
            this.next=null;
        }
    }

    private Node[] table; //Hash Table
    private int count;    //ne kadar ogrenci submit oldu

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public SubmissionRegistry(){
        table = new Node[INITIAL_CAPACITY];
        count=0;
    }
     //java yerlesik hash code pozitif index cevir
    private int hash(String studentId){
        int hashCode =studentId.hashCode() & 0x7fffffff;
        return hashCode % table.length;
    }
    // put method 0(1) ilk yukleme ve guncelleme
    public void put(Submission sub){

        if ((double) (count+1) / table.length > LOAD_FACTOR) {
            resize();

        }

        int indexing = hash(sub.getStudentId()); //mevcut kaydi guncel yap

        Node newNode = new Node(sub);

        newNode.next=table[indexing];
        table[indexing]=newNode;

        count++;

    }

    private void resize(){
        Node[] oldTable = table;

        table = new Node[oldTable.length*2];

        for (int i = 0; i < oldTable.length; i++) {

            Node current = oldTable[i];

            while (current != null){
                Node nextNode = current.next;

                int newIndex = hash(current.submission.getStudentId());
                 //yeni index  hesapla ve nodu yeni tablonun basina ekle
                current.next = table[newIndex];
                table[newIndex]=current;

                current=nextNode; //next node git
            }

        }

    }
    public Submission lookup(String studentId){
        int indexing = hash(studentId);    //ogrencinin tablodaki index bul

        Node current = table[indexing];
        while (current!=null){     //bagli listede student bul
            if (current.submission.getStudentId().equals(studentId)) {
                return current.submission;  //found
            }
            current=current.next;    //moved to next node
        }
        return null;

    }
    public int updateVersion(String studentId, String fileName, int sizeKb, long timeStampMs){

        Submission submission = lookup(studentId);     //Find  Student (S-0001)

        if (submission == null) {    //sub not find
            return -1;
        }

        submission.replaceFile(fileName,sizeKb,timeStampMs);  //update sub

        return submission.getVersion();   //return updste version num

    }
    //count increase if we use put method
    public int size(){
        return count;
    }

    int bucketIndex(String studentId) {

        return hash(studentId);   //get hash indx for student
    }

    int capacity() {

        return table.length;   //returm total table size
    }







}

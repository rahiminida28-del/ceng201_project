public class CircularUploadQueue {
    private Submission[] Q;
    private int front;
    private int rear;
    private int size;


    //Constructor

    public CircularUploadQueue(int capacity){  //circular  Q veriln capacity olusturr ve
        Q = new Submission[capacity];   // baslangıc degerlerini ayarlar
        this.front=0;
        this.rear=-1;
        this.size=0;
    }

    public boolean enqueue(Submission sub){ //Q yeni sub eklemek icin kullanir

        if (size == Q.length) {   //Q  is Full!
            return false;
        }


        rear=(rear+1) % Q.length; //yeni elemanin eklenecegi arka konuma belirlemek icin kullanilir
        Q[rear]=sub;

        size++;

        return true;

    }

    public Submission dequeue(){

        if (size == 0) {         //Queue burda bos
            return null;
        }

        Submission first= Q[front];

        Q[front] = null;

        front=(front+1)% Q.length;

        size--;

        return first;
    }

    // Dairesel Q, wrap-around kullanarak O(1) işlemi yapar.

    public int size(){
        return  size;
    }

    void printState(){
        System.out.print("\nQueue: [");

        for (int i = 0; i < Q.length; i++) {

            if (Q[i] == null) {
                System.out.print("EMPTY!");
            }
            else{
                System.out.print(Q[i].getStudentId());      //student id yaz sonra comma koy
            }

            if (i < Q.length-1) {
                System.out.print(", ");
            }

        }

        System.out.println("]");

        System.out.println("\nfront =" + front + " rear =" + rear + " size =" + size);



    }


}



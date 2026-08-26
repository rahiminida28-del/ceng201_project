import java.util.NoSuchElementException; //Aranan kayıt veya eleman sistemde bulunamadıgında
// hata fırlatmak icn kullanılır

public class VersionStack {

    //Structure node
    private static class Node{
        VersionRecord data;
        Node next;

        Node(VersionRecord data){
            this.data=data;
            this.next=null;
        }
    }

    private Node top;  //head=top

    //Constructor of VersionStack.
    public VersionStack(){
        top=null;
    }

    //Push Method

    public void push(VersionRecord version){

        Node newNode = new Node(version);

        newNode.next=top;
        top=newNode;

    }



    public VersionRecord pop(){  //pop son elemani listeden silip geri donduren metod

        if (top == null) {

            throw new NoSuchElementException();
        }

        VersionRecord removedElement=top.data;

        top=top.next;  //top elemanini silip bir sonrakine geciyo
        //stack top isaretcisini br altindaki dugme gunceller
        return removedElement;
    }

    //isEmpty Method

    public boolean isEmpty(){
        return top==null;
    }

    public void printStack() {

        Node current = top;

        System.out.print("Stack: ");

        if (current == null) {
            System.out.println("Empty");
            return;
        }

        while (current != null) {

            System.out.print("v" + current.data.getVersion());

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }





}

package demo5;

public class linkedList<T> {
    Node head;

    public linkedList(){
        this.head = null;
    }

    public void addFirst(T v){
        Node newNode = new Node(v);
        newNode.next = this.head;
        this.head = newNode;
    }

    public void printAllItems(){
        Node pointer = this.head;
        while(pointer != null)
        {
            System.out.println(pointer.value);
            pointer = pointer.next;
        }
    }

    public void addLast(T v){
        Node pointer = this.head;
        while(pointer != null){
            if(pointer.next == null){
                Node newNode = new Node(v);
                pointer.next = newNode;
                newNode.next = null;
                break;
            }
            pointer = pointer.next;
        }
    }

}

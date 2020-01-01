package Stack;
/*
public class LinkedStack<T> {

    private Node<T> bottom;
    private Node<T> top;


     * 栈底有一个栈底结点

    public LinkedStack(){
        bottom = new Node<T>(null,null);
        top = bottom;
    }

    public void push(T data){
        Node<T> newNode = new Node<>(data,null);
        Node<T> t = top;
        //top.next = newNode;
        top = newNode;
        if (bottom.next == null){
            bottom.next = newNode;
        }else {
            t.next = newNode;
        }
    }

    public T pop(){
        if (!isEmpty()){
            Node<T> newTop = bottom;
            while (newTop.next != top){
                newTop = newTop.next;
            }
            T data = top.data;
            top = newTop;
            top.next = null;
            return data;
        }else {
            System.out.println("栈是空的！");
            return null;
        }
    }

    private boolean isEmpty() {
        return top == bottom;
    }

    private class Node<T> {
        private T data;
        private Node next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }
}
*/
public class LinkedStack<T> {
    private Node<T> top;

    public void push(T data){
        Node<T> newNode = new Node<>(data, null);
        Node<T> t = top;
        top = newNode;
        top.next = t;
    }

    public T pop(){
        Node<T> t = top;
        if (!isEmpty()){
            top = top.next;
            return t.data;
        }else {
            System.out.println("栈为空！");
            return null;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    private class Node<T> {
        private T data;
        private Node next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

}
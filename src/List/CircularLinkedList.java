package List;

public class CircularLinkedList<T> {
    private Node<T> last;
    private Node<T> headNode;

    public CircularLinkedList(){
        Node<T> node = new Node<>();
        headNode = new Node<T>(null, node);
        last = new Node<T>(null, headNode);
        headNode.nextNode = last;
    }

    /**
     * 添加数据时先调用此函数设置last中的data，然后再使用add添加其他元素
     * @param data last中的data
     */
    public void setLast(T data){
        last.data = data;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data, headNode);
        last.nextNode = newNode;
        last = newNode;
    }

    public T deleteFromLast(){
        Node<T> lastNode = last;
        T data = lastNode.data;
        //lastNode.data = null;
        Node<T> node = lastNode;
        while (node.nextNode != last){
            node = node.nextNode;
        }
        node.nextNode = headNode;
        last = node;
        lastNode.nextNode = null;
        lastNode.data = null;
        return data;
    }

    public boolean combine(CircularLinkedList<T> circularLinkedList){
        Node<T> listHeadNode = circularLinkedList.last.nextNode;
        last.nextNode = circularLinkedList.last.nextNode.nextNode;
        circularLinkedList.last.nextNode = headNode;
        listHeadNode.nextNode = null;
        last = circularLinkedList.last;
        return true;
    }

    private static class Node<T>{
        T data;
        Node<T> nextNode;

        public  Node(){
        }
        private Node(T data, Node<T> next){
            this.data = data;
            this.nextNode = next;
        }
    }
}

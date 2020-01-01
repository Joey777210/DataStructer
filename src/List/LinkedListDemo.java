package List;

import java.nio.file.NotDirectoryException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/*
模仿JDK的双向链表实现一个简单的LinkedList
没有头结点
 */
public class LinkedListDemo<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    public LinkedListDemo(){
        size = 0;
    }

    /**
     * 在表尾插入一个新节点
     * @param data 新节点的数据域
     */
    public void addLast(T data){
        Node<T> last = lastNode;
        Node<T> node = new Node<>(data, last, null);
        lastNode = node;
        if (last == null){
            firstNode = node;
        }
        else{
            last.nextNode = node;
        }
        size++;
    }

    /**
     * 在表头插入一个新结点
     * @param data 新结点的数据域
     */
    public void addFirst(T data){
        Node<T> first = firstNode;
        Node<T> node = new Node<T>(data,null, first);
        firstNode = node;
        if (first == null){
            lastNode = node;
        }else {
            first.preNode = node;
        }
        size++;
    }

    public T get(int index){
        checkIndex(index);
        return search(index).data;
    }

    public int size(){
        return size;
    }

    private Node<T> search(int index){
        checkIndex(index);
        Node<T> pointer = firstNode;
        for (int i = 0; i < index; i++){
            pointer = pointer.nextNode;
        }
        return pointer;
    }
    /**
     * 在index前面插入一个元素
     * @param index 索引
     * @param data 数据域
     */
    public void insert(int index, T data){
        checkIndex(index);
        if (index == 0){
            Node<T> f = firstNode;
            Node<T> newNode = new Node<>(data, null, f);
            firstNode = newNode;
            f.preNode = newNode;
        }else {
            Node<T> n = search(index);
            Node<T> pre = n.preNode;
            Node<T> newNode1 = new Node<>(data, pre, n);
            pre.nextNode = newNode1;
            n.preNode = newNode1;
        }
        size++;
    }

    /**
     * 检查index是否越界.合法的最大index = size - 1.
      * @param index 要检查的索引
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 根据结点的次序来删除结点。 后发现与JDK中的删除操作不同。
     * JDK中LinkedList没有按照次序插入或删除的操作，都使用比较数据域是否相同的方法来删除。
     * @param index 结点的次序
     * @return 被删除结点的数据域
     */
    public T remove(int index){
        checkIndex(index);
        Node<T> pointer = search(index);
        Node<T> pre = pointer.preNode;
        Node<T> next = pointer.nextNode;
        if (firstNode ==  null) {
            pre.nextNode = next;
        }else {
            pre.nextNode = next;
            pointer.nextNode = null;
        }
        if (next == null){
            lastNode = pre;
        }else {
            next.preNode = pre;
            pointer.preNode = null;
        }
        size--;
        return pointer.data;
    }

    /**
     * 清空链表，帮助GC回收内存。
     * 在JDK中，LinkedList实现了Iterator，如果迭代到链表的中间，那么只释放表头的话就不会引起GC回收
     * 所以要在循环中逐一清空每一个结点。
     */
    public void clear(){
        for (Node<T> pointer = firstNode;pointer != null; ){
            Node<T> next = pointer.nextNode;
            pointer.data = null;
            pointer.preNode = null;
            pointer.nextNode = null;
            pointer = next;
        }
        size = 0;
        firstNode = null;
        lastNode = null;
    }


    private static class Node<T>{
        T data;
        Node<T> nextNode;
        Node<T> preNode;

        public  Node(){
        }
        private Node(T data, Node<T> pre, Node<T> next){
            this.data = data;
            this.preNode = pre;
            this.nextNode = next;
        }
    }

    public LinkedListItertor itertor(){
        return new LinkedListItertor();
    }

    class LinkedListItertor implements Iterator{
        private Node<T> currentNode;
        private int nextIndex;

        public LinkedListItertor(){
            currentNode = firstNode;
            nextIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        @Override
        public T next() {
            Node<T> node = currentNode;
            currentNode = currentNode.nextNode;
            nextIndex++;
            return node.data;
        }
    }
}


package List;

import java.util.List;
import java.util.prefs.NodeChangeEvent;

public class StaticLinkList <T>{
    private ListNode<T>[] list;
    private static int DEFAULT_CAPACITY = 1000;
    private int capacity;
    private int size;
//    private ListNode firstNode;
//    private ListNode endNode;
//    private int capacity;
//    private int size;

    private StaticLinkList(int capacity){
        this.capacity = capacity;
        list = new ListNode[capacity];
        list[0] = new ListNode<T>(null, 1);
        list[capacity - 1] = new ListNode<T>(null, 0);
        size = 0;
        /*
        size = 0;
        this.capacity = capacity;
        list = new Object[capacity];
        firstNode = new ListNode<Integer>(null, 1);
        list[0] = firstNode;
        endNode = new ListNode<Integer>(null, 0);
        list[capacity - 1] = endNode;
         */
    }

    public int size(){
        return capacity;
    }

    public StaticLinkList(){
        this(DEFAULT_CAPACITY);
    }

    public void addLast(T data){
        ListNode tail = FindTail();
        ListNode<T> newNode = new ListNode<T>(data, 0);
        list[list[0].cur] = newNode;
        tail.cur = list[0].cur;
        synchronize();
        size++;
    }

    /**
     * 在index前面插入一个元素 由于是单向链表，所以要先取到index上一个元素
     * @param index 角标
     * @param data 数据
     */
    public void insert(int index, T data){
        ListNode beforeIndexNode = searchPreNode(index);
        int indexCur = beforeIndexNode.cur;
        ListNode<T> newNode = new ListNode<T>(data, indexCur);
        list[list[0].cur] = newNode;
        beforeIndexNode.cur = list[0].cur;
        synchronize();
        size++;
    }

    private void synchronize(){
        int i = 1;
        while(list[i] != null){
            i++;
        }
        list[0].cur = i;
    }

    public T delete(int index){
        checkIndex(index);
        ListNode<T> preNode = searchPreNode(index);
        ListNode<T> indexNode = list[preNode.cur];
        //这行报错NullPointerException
        int cur = indexNode.cur;
        preNode.setCur(indexNode.getCur());
        indexNode.cur = 0;
        T data = indexNode.data;
        indexNode.data = null;
        synchronize();
        size--;
        return data;
    }

    public void checkIndex(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    private ListNode FindTail(){
        ListNode tailNode = list[capacity - 1];
        while (tailNode.cur != 0){
            tailNode = list[tailNode.cur];
        }
        return tailNode;
    }

    /**
     * 拿到index - 1这个结点，才能将新结点插入到index位置上
     * @param index 要插入的索引（从0开始）
     * @return 返回查找到的结点
     */

    private ListNode<T> searchPreNode(int index){
        ListNode<T> node = list[capacity - 1];
        for(int i = 0; i < index; i++){
            node = list[node.cur];
        }
        return node;
    }

    private class ListNode<T>{
        private T data;
        private int cur;

        private ListNode(T data, int cur){
            this.data = data;
            this.cur = cur;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setCur(int cur) {
            this.cur = cur;
        }

        private T getData(){
            return data;
        }

        private int getCur(){
            return cur;
        }
    }
}

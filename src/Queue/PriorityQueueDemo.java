package Queue;

import java.util.Arrays;
import java.util.Comparator;

/*
优先队列的作用是能保证每次取出的元素都是队列中权值最小的
（Java的优先队列每次取最小元素，C++的优先队列每次取最大元素）。
这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序（natural ordering），
也可以通过构造时传入的比较器。
 */
public class PriorityQueueDemo<T> {
    private Object[] queue;
    private int size;
    private Comparator<? super T> comparator;



    public PriorityQueueDemo(int capacity){
        queue = new Object[capacity];
        size = 0;
    }

    public PriorityQueueDemo(int capacity,Comparator<? super T> comparator){
        this.comparator = comparator;
        queue = new Object[capacity];
        size = 0;
    }

    /**
     * 这里给出一个堆排序初始化一个小顶堆队列的方法
     * @param data 要被排序的数组
     * @param comparator 定义比较方式的比较器
     */
    public PriorityQueueDemo(T[] data, Comparator<T> comparator) throws Exception {
        int capacity = data.length;
        queue = new Object[capacity];
        size = 0;
        this.comparator = comparator;
        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }


    public void add(T data) throws Exception {
        int i = size;
        if (data == null){
            throw new Exception();
        }else if (i >= queue.length){
            grow(i);
        }
        if (size == 0){
            queue[0] = data;
            size++;
        }
        else {
            queue[i] = data;
            shiftUp(i, data);
            size++;
        }
    }

    public T poll(){
        if (size == 0){
            throw new NullPointerException();
        }
        T polled = (T)queue[0];
        int s = --size;
        T x = (T)queue[s];
        queue[0] = queue[s];
        queue[s] = null;
        if (s != 0){
            shiftDown(0,x);
        }


        return polled;
    }

    private void shiftDown(int i, T x) {
        //父节点是否大于子节点的标志
        //若为true，则需要进入循环。
        boolean flag = true;
        while(flag) {
            int child = (i << 1) + 1;
            T smallerChild = (T) queue[child];
            int right = child + 1;
            if (right >= size || child >= size){
                flag = false;
                break;
            }
            if (comparator.compare(smallerChild, (T) queue[right]) > 0) {
                child = right;
                smallerChild = (T) queue[child];
            }
            if (comparator.compare(x, smallerChild) <= 0) {
                flag = false;
                break;
            }
            queue[i] = smallerChild;
            i = child;
        }
        queue[i] = x;
    }

    /**
     * 新结点上浮
     * @param i i的初始值是size的引用,即尾指针的位置。
     * @param data 新结点的数据
     */
    //理解。
    private void shiftUp(int i,T data) {
        while(i > 0) {
            int parent = (i - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(data, (T)e) >= 0) {
                break;
            }
            queue[i] = e;
            i = parent;
        }
        queue[i] = data;
    }

    private void grow(int i) {
        Object[] newQueue = Arrays.copyOf(queue, i + 1);
        this.size++;
    }

    public int size(){
        return size;
    }
}

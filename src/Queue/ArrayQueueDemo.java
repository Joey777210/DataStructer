package Queue;

public class ArrayQueueDemo<T> {
    /*
    仿照JDK1.8 中的 ArrayQueue 实现一个简单的循环队列,有些许不同
    JDK 13中为什么没找到ArrayQueue？
     */

    private Object[] queue;
    private int capacity;  //实际要装的数据个数
    private int front;
    private int rear;

    public ArrayQueueDemo(int capacity){
        this.capacity = capacity + 1;
        this.queue = new Object[capacity + 1];  //+1之后是数组长度，因为要预留一个空位置给rear。 文中提到的QueueSize是没有 + 1 的capacity
        this.front = 0;
        this.rear = 0;
    }

    public void add(T data){
        queue[rear] = data;
        int newRear = (rear + 1) % capacity;
        if (newRear == front){
            throw new IndexOutOfBoundsException();
        }
        rear = newRear;
    }

    public T remove(){
        if (isFull()){
            throw new IndexOutOfBoundsException();
        }
        Object removed = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        return (T)removed;
    }

    /**
     * 返回最大队列长度
     * @return 最大
     */
    public int capacity(){
        return capacity;
    }

    /**
     * 返回当前队列大小
     * @return
     */
    public int size(){
        return (rear - front + capacity - 1) % (capacity - 1);
    }
    public boolean isFull(){
        return (rear + 1) % (capacity - 1) == front;
    }
}

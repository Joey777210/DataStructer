package List;

import java.util.Iterator;
import java.util.List;

public class ArrayListDemo<T> {
    //初始化后可存放的数量，从1开始计数
    private int capacity;
    //private T[] data; 不能构造泛型数组，我们用Object[]
    private Object[] data;
    //已存的数量，从0开始计数。 当size == capacity 时，满。 size = index + 1.
    private int size;
    private static int DEFAULT_CAPACITY = 10;

    /**
     * 自定义顺序存储线性表的构造函数
     * @param capacity 数组的初始化长度
     */
    public ArrayListDemo(int capacity) {
        //下面写法会报错。因为Java中不能使用泛型数组，有隐含的ClassCastException
        //date = new T[length];
        //那么JDK中的ArrayList是怎么实现的？
        //JDK中使用Object[]来初始化了表
        if (capacity > 0){
            this.capacity = capacity;
            this.data = new Object[capacity];
            size = 0;
        }else {
            throw new IndexOutOfBoundsException("长度不合法");
        }
    }

    public ArrayListDemo() {
        this(DEFAULT_CAPACITY);
    }

    public void add(T element){
        if (size >= capacity){
            grow();
        }
        data[size++] = element;

    }

    private void grow() {
        capacity = (int)(capacity * 1.5);
        Object[] newDataArr = new Object[(int)(capacity)];
        for (int i = 0; i < size; i++){
            newDataArr[i] = data[size];
        }
        data = newDataArr;
    }

    /**
     * 指定序号元素的获取，Java核心技术中称随机存取
     * @param index 得到数组元素的角标
     * @return 对应的数据元素
     */

    public T getElem(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index]; //这里会报警告，如何解决？
    }


    public void insertElem(int index, T elem){
        if (size == capacity){
            grow();
        }
        for (int i = size - 1; i > index; i--){
            data[i + 1] = i;
        }
        size++;
        data[index] = elem;
    }

    private void checkIndex(int index)throws IndexOutOfBoundsException{
        //TODO
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 从指定index的位置上移除元素，且返回该元素的值
     * @param index 要移除的索引
     * @return 被移除的元素的值
     * @throws IndexOutOfBoundsException 超出范围
     */
    public T removeElem(int index)throws IndexOutOfBoundsException{
        checkIndex(index);
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }else {
            Object elem = data[index];
            for (int i = index; i < size - 1; i++){
                data[i] = data[i + 1];
            }
            size--;
            return (T)elem;  //这里会报警告，如何解决？
        }
    }

    public int size(){
        return capacity;
    }

    public ArrayListItertor itertor(){
        return new ArrayListItertor();
    }

    private class ArrayListItertor implements Iterator{
        private int currentIndex;

        public ArrayListItertor(){
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return !(currentIndex >= size);
        }

        @Override
        public Object next() {
            Object o = data[currentIndex];
            currentIndex++;
            return o;
        }
    }
}

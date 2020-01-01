package Stack;

public class ArrayStack<T>{
    private Object[] list;
    private int top;
    private int size;
    private static int DEFAULT_SIZE = 10;

    public ArrayStack(int size){
        list = new Object[size];
        this.size = size;
        top = -1;
    }

    public ArrayStack(){
        this(DEFAULT_SIZE);
    }

    public void push(T data){

        if (!isFull()){
                list[++top] = data;
        }else {
            System.out.println("栈满了");
        }
    }

    public T pop(){
        if (!isEmpty()){
                T obj = (T)list[top];
                list[top] = null;
                top--;
                return obj;

        }else {
            System.out.println("栈是空的");
        }
        return null;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull() {
        return top >= size;
    }

    public int size(){
        return size;
    }

    public int getTop(){
        return top;
    }

    public T getTopData(){
        return (T)list[top];
    }
}

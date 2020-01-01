package Stack;

public class ShareStack<T> {
    private Object[] list;
    private int top1;
    private int top2;
    private int capacity;
    private static int DEFAULT_SIZE = 10;
    //private boolean isFull = false;

    public ShareStack(int capacity){
        list = new Object[capacity];
        this.capacity = capacity;
        top1 = -1;
        top2 = capacity;
    }

    public ShareStack(){
        this(DEFAULT_SIZE);
    }

    public boolean pushStackOne(T data){
        if (!isFull()){
            list[++top1] = data;
            //isFull = isFull();
            return true;
        }else{
            System.out.print("栈满了！");
            return false;
        }
    }

    public boolean pushStackTwo(T data){
        if (!isFull()){
            list[--top2] = data;
            //isFull = isFull();
            return true;
        }else{
            System.out.print("栈满了！");
            return false;
        }
    }

    public T popStackOne(){
        if (!isEmptyOne()){
            T data = (T)list[top1--];
            return data;
        }else {
            System.out.println("栈一是空的!");
            return null;
        }
    }

    public T popStackTwo(){
        if (!isEmptyTwo()){
            T data = (T)list[top2++];
            return data;
        }else {
            System.out.println("栈二是空的！");
            return null;
        }
    }

    private boolean isEmptyTwo() {
        return top2 == capacity;
    }

    private boolean isFull() {
        return top1 == (top2 - 1);
    }

    private boolean isEmptyOne(){
        return top1 == -1;
    }

}

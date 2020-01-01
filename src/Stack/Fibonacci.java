package Stack;

public class Fibonacci {
    public static void main(String[] args) throws Exception {
        System.out.println(Fibonacci(12));
    }

    public static int Fibonacci(int month) throws Exception {
        if (month < 0){
            throw new Exception();
        }
        if (month < 2){
            return month == 0 ? 0 : 1;
        }else {
            return (Fibonacci(month - 1) + Fibonacci(month - 2));
        }
    }
}

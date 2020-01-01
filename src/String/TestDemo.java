package String;

public class TestDemo {
    public static void main(String[] args){
        String s1 = "abaabaabbabaaabaabbabaab";
        String s2 = "abaabbabaab"; //6

        //String s1 = "qwerqwrfqwqewwqr";
        //String s2 = "wrfqw"; //6
        int a = KMPDemo.index_KMP(s1,s2);
        System.out.println(a);
    }
}

package String;

public class KMPDemo {
    public static int index_KMP(String main, String arg) {
        int i = 0;
        int j = 0;
        int[] next = getNext(arg);
        while (i < main.length() && j < arg.length()) {
            if (j == -1 ||
                    main.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= arg.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] getNext(String arg) {
        int[] next = new int[arg.length() + 1];
        next[0] = -1;
        int i = 0;//模式串中当前匹配到的位置的下标值
        int j = -1;//子串匹配前缀最后一个位置的下一个元素的下标
        while (i < arg.length()) {
            if (j == -1 || arg.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {//若上一轮匹配到的前j个元素加上这轮新来的一个元素现在不能满足这么长的前缀等于后缀，则找到之前求得的next[j]，说明当前满足的前缀要变短才能匹配。
                j = next[j];
            }
        }
        return next;
    }

    private static int[] getNextval(String arg) {
        int[] nextval = new int[arg.length() + 1];
        nextval[0] = -1;
        int i = 0;//模式串中当前匹配到的位置的下标值
        int j = -1;//子串匹配前缀最后一个位置的下一个元素的下标
        while (i < arg.length()) {
            if (j == -1 || arg.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
                if (arg.charAt(i) != arg.charAt(j)){
                    nextval[i] = j;
                }else {
                    nextval[i] = nextval[j];
                }
            } else {//若上一轮匹配到的前j个元素加上这轮新来的一个元素现在不能满足这么长的前缀等于后缀，则找到之前求得的next[j]，说明当前满足的前缀要变短才能匹配。
                j = nextval[j];
            }
        }
        return nextval;
    }
}
/*
    private static int[] getNext(String arg){
        int len = arg.length();
        int i = 1;   //模式串中当前匹配到的位置的下标值
        int j = 0; //子串匹配前缀最后一个位置的下一个元素的下标
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        while (i < len && j != -1){
            if (arg.charAt(i) == arg.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else { //若上一轮匹配到的前j个元素加上这轮新来的一个元素现在不能满足这么长的前缀等于后缀，则找到之前求得的next[j]，说明当前满足的前缀要变短才能匹配。
                j = next[j];
            }
        }
        return next;
    }

 */


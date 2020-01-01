package String;
/*
public class IndexDemo {
    public static int index(String main, String arg){
        int index = -1;
        for (int i = 0; i < (main.length() - arg.length() + 1); i++){
            for (int j = 0; j < arg.length(); j++){
                if (main.charAt(i + j) != arg.charAt(j)){
                    break;
                }else if (j == arg.length()-1){
                    index = i;
                }
            }
        }
        if (index == -1){
            return -1;
        }
        return index;
    }
}
*/

public class IndexDemo {
    public static int index(String main, String arg, int pos){
        int i = pos;  //控制main的角标
        int j = 0;  //控制arg的角标
        while(i < main.length() && j < arg.length()){
            if (main.charAt(i) == arg.charAt(j)){
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= arg.length()){
            return i - j;
        }else {
            return -1;
        }
    }
}

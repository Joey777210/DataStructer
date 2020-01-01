package Test;

import java.io.File;

public class TestDemo {
    public static void main(String[] args){
        File dir = new File("f://djwqioejqw/dhuwqeirwq");
        if (!dir.exists()){
            dir.mkdirs();
        }
    }
}

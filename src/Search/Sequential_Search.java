package Search;

import java.util.Comparator;
import java.util.List;

public class Sequential_Search {
    public static int equentialSearch(List list, Object obj){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(obj)){
                return i;
            }
        }
        return -1;
    }

    public static int enquentialSearch2(List list, Object obj){
        list.set(0, obj);
        int index = list.size();
        while (!list.get(index).equals(obj)){
            index--;
        }
        return index;
    }
}

package Search.OrderedList;

import java.util.Comparator;
import java.util.List;

public class Binary_Search {
    public static int Binary_Search(List<Integer> list, int key){
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        });
        int low = 1;
        int high = list.size() - 1;
        while (low <= high){
            int mid = getMid(low, high);
            if (key > list.get(mid)){
                low = mid + 1;
            }else if (key < list.get(mid)){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    private static int getMid(int low, int high) {
        return (low + high) / 2;
    }
}

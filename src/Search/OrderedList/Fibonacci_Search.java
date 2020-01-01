package Search.OrderedList;

import java.util.List;

public class Fibonacci_Search {
    private static int[] F = new int[100];
    public static int Fibonacci_Search(List<Integer> list, int key) throws Exception {
        int low = 0;
        int high = list.size() - 1;
        int n = list.size() - 1;
        int k = 0;
        int mid;
        while (n > F[k] - 1){
            k++;
        }
        for (int i = n; i < F[k] - 1; i++)
        {
            list.set(i, list.get(list.size() - 1));
        }
        while (low <= high){
            mid = low + F[k - 1] - 1;
            if (key < list.get(mid)){
                high = mid - 1;
                k = k - 1;
            } else if (key > list.get(mid)) {
                low = mid + 1;
                k = k - 2;
            }else {
                if (mid <= n){
                    return mid;
                }else {
                    return n;
                }
            }
        }
        return -1;
    }
}

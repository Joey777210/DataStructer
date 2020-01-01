package Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        Integer[] data = new Integer[]{5,7,6,1,4,8,2,52,4,4};

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };
        PriorityQueueDemo<Integer> p = new PriorityQueueDemo<>(data, c);
//        for (int i = 10; i > 0; i--) {
//            p.add(i);
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println(p.poll());
        }
        String a = new String("465qewq");
    }
}

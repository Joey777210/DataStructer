package List;

import java.lang.*;

public class ListDemo {
    public static void main(String[] args) throws InterruptedException {
        CircularLinkedList<Integer> myList = new CircularLinkedList<Integer>();
        myList.setLast(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);

        CircularLinkedList<Integer> mylist = new CircularLinkedList<Integer>();
        mylist.setLast(10);
        mylist.add(11);
        //Thread.sleep(10000);
        /*
        Iterator<Integer> i = myList.itertor();
        while(i.hasNext()){
            System.out.println(i.next());
        }
         */
        System.out.println(myList.combine(mylist));
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
        System.out.println(myList.deleteFromLast());
    }
}


package Tree;

public class ParentChildDemo <T>{

    PCTBox[] nodes; //表头数组
    int r;  //根节点的角标
    int n;  //总结点树
    int parent;  //双亲结点的角标，根节点为-1


    private class PCTBox<T>{
        T data;
        ChildNode firstchild;
    }
    private class ChildNode{
        int child; //存储本结点在表头数组中的下标
        ChildNode next;
    }
}

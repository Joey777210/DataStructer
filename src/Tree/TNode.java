package Tree;

import java.util.Comparator;

public class TNode<T> implements Comparable{
    private T data;
    private int weight;
    private TNode lChild;
    private TNode rChild;

    private Comparator comparator;

    public TNode(T data, int weight){
        this.data = data;
        this.weight = weight;
        this.lChild = null;
        this.rChild = null;
    }

    public TNode(T data, int weight, TNode lChild, TNode rChild){
        this(data, weight);
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public Comparator getComparator(){
        return this.comparator;
    }
    public void setlChild(TNode node){
        this.lChild = node;
    }

    public void setrChild(TNode node){
        this.rChild = node;
    }

    public int getWeight(){
        return this.weight;
    }

    public T getData(){
        return data;
    }

    public TNode getlChild(){
        return lChild;
    }

    public TNode getrChild(){
        return rChild;
    }

    @Override
    public int compareTo(Object o) {
        return ((TNode)o).weight - this.weight;
    }

}

class TNodeComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            return ((TNode)o1).getWeight() - ((TNode)o2).getWeight();
        }

}
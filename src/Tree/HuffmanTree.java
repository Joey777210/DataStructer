package Tree;

import java.util.Comparator;
import java.util.LinkedList;

public class HuffmanTree<T> {
    LinkedList<TNode<T>> tnodes = new LinkedList<>();

    public HuffmanTree(LinkedList<TNode<T>> tnodes){
        this.tnodes = tnodes;
    }

    public TNode<T> buildHuffmanTree(){
        tnodes.sort(new TNodeComparator());
        while (tnodes.size() > 1) {
            TNode<T> newTNode = buildBiTree(tnodes.remove(0), tnodes.remove(0));
            tnodes.add(0, newTNode);
            tnodes.sort(new TNodeComparator());
        }
        return tnodes.remove(0);
    }

    public void PreOrderTraverse(TNode tNode){
        if (tNode == null){
            return;
        }else {
            T tdata = (T) tNode.getData();
            if (tdata != null){
                System.out.println(tdata);
            }
            PreOrderTraverse(tNode.getlChild());
            PreOrderTraverse(tNode.getrChild());
        }
    }

    private TNode buildBiTree(TNode<T> tNode, TNode<T> tNode1) {
        TNode<T> t1 = new TNode<T>(null, tNode.getWeight() + tNode1.getWeight(), tNode, tNode1);
        return t1;
    }
}

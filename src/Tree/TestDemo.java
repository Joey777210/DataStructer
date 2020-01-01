package Tree;

import java.util.LinkedList;

public class TestDemo {
    public static void main(String[] args) {

        TNode<String> t1 = new TNode<String>("A", 5);
        TNode<String> t2 = new TNode<String>("B", 15);
        TNode<String> t3 = new TNode<String>("C", 40);
        TNode<String> t4 = new TNode<String>("D", 30);
        TNode<String> t5 = new TNode<String>("E", 10);
        LinkedList tnodes = new LinkedList();
        tnodes.add(t1);
        tnodes.add(t2);
        tnodes.add(t3);
        tnodes.add(t4);
        tnodes.add(t5);

        HuffmanTree ht = new HuffmanTree(tnodes);
        TNode root = ht.buildHuffmanTree();
        ht.PreOrderTraverse(root);
    }
}
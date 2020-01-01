package Tree;

import Queue.PriorityQueueDemo;
import Stack.ArrayStack;

public class BiTree<T>{
    private Object[] results;
    public BiTree(Object[] arr){
        this.results = arr;
    }
    private static int index = 0;
    public Node<T> buildBiTree(){
        if (index >= results.length || results[index].equals('#')){
            index++;
            return null;
        }
        Node<T> node = new Node<T>((T)results[index++]);
        node.lChild = buildBiTree();
        node.rChild = buildBiTree();
        return node;
    }


    /**
     * 前序遍历的递归写法
     */
    public void PreOrderTraverse1(Node root){
        if (root == null)
            return;
        System.out.println(root.data);
        PreOrderTraverse1(root.lChild);
        PreOrderTraverse1(root.rChild);
    }

    /**
     * 前序遍历的非递归写法
     * 递归转成栈
     */
    public void PreOrderTraverse2(Node root){
        ArrayStack<Node> a = new ArrayStack<>();
        while (root != null || !a.isEmpty()){
            while (root != null){
                System.out.println(root.data);
                a.push(root);
                root = root.lChild;
            }

            if (!a.isEmpty()){
                root = a.pop();
                root = root.rChild;
            }
        }
    }

    /**
     * 中序遍历的递归写法
     */
    public void InOrderTraverse1(Node root){
        Node node = root;
        if (node == null){
            return;
        }
        InOrderTraverse1(node.lChild);
        System.out.println(node.data);
        InOrderTraverse1(node.rChild);
    }

    /**
     * 中序遍历的非递归写法
     */
    public void InOrderTraverse2(Node root){
        ArrayStack<Node> a = new ArrayStack<>();
        while (root != null || !a.isEmpty()){
            while (root.lChild != null){
                a.push(root);
                root = root.lChild;
            }
            if (!a.isEmpty()){
                root = a.pop();
                System.out.println(root.data);
                root = root.rChild;

            }
        }
    }

    /**
     * 在中序遍历的过程中完成线索化
     */

    private Node pre;//根节点的pre是head,head的rtag = 1
    private int flag = 0;
    public void Threading(Node root) {
        Node r = root;
        if (root == null){
            return;
        }else {
            Threading(r.lChild);
            if (r.lChild == null){
                r.ltag = 1;
                r.lChild = pre;
            }
            if ( pre != null && pre.rChild == null){
                pre.rtag = 1;
                pre.rChild = r;
            }
            pre = r;
            Threading(r.rChild);
        }
    }

    /**
     * 按照二叉线索树的线索遍历
     */
    public void InOrderTraverse_Threaded(Node root){
        Node r = root;
        while (r != null) {
            while (r.lChild != null && r.ltag == 0) {
                r = r.lChild;
            }
            System.out.println(r.data);
            while (r.rtag == 1) {
                r = r.rChild;
                System.out.println(r.data);
            }
            r = r.rChild;
        }
    }

    /**
     * 后序遍历的递归写法
     */
    public void PostOrderTraverse1(Node root){
        if (root == null){
            return;
        }
        PostOrderTraverse1(root.lChild);
        PostOrderTraverse1(root.rChild);
        System.out.println(root.data);
    }

    /**
     * 后序遍历的非递归写法 —— 双栈法
     * 将前序遍历的中左右，调换变成左右中
     *
     */
    public void PostOrderTraverse2(Node root){
        ArrayStack<Node> a1 = new ArrayStack<>();
        ArrayStack<Node> a2 = new ArrayStack<>();
        Node r = root;
        while (r != null || !a1.isEmpty()){
            while (r != null){
                a1.push(r);
                a2.push(r);
                r = r.rChild;
            }
            if (!a1.isEmpty()){
                r = a1.pop();
                r = r.lChild;
            }
        }
        while (!a2.isEmpty()){
            r = a2.pop();
            System.out.println(r.data);
        }
    }

    /**
     * 利用队列实现层序遍历(可以不用优先队列，这个优先队列Demo是我前面随笔写的，顺便拿来用用)
     */
    public void LevelOrderTraverse(Node root) throws Exception {
        PriorityQueueDemo<Node> p = new PriorityQueueDemo<>(10);
        Node t;
        p.add(root);
        while (p.size() != 0){
            t = p.poll();
            System.out.println(t.data);
            if (t.lChild != null)  p.add(t.lChild);
            if (t.rChild != null)  p.add(t.rChild);
        }

    }


    public static class Node<T>{
        private T data;
        private Node lChild;
        private Node rChild;

        private int ltag = 0;   //标识左指针是指向左孩子0还是前驱1
        private int rtag = 0;   //标识右指针是指向右孩子0还是后继1

        public Node(T data) {
            this.data = data;
        }

        public Node(T data,int rtag) {
            this.data = data;
            this.rtag = rtag;
        }

        public void setRtag(int rtag){
            this.rtag = rtag;
        }
        public void setNode(T data){
            this.data = data;
        }
    }

}

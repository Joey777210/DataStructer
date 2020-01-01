package Graph.OrthogonalList;

/*
    图结构的十字链表实现
 */
public class GraphOrthogonalList<T>{
    private VertexOL<T>[] vertexs;
    private int numVertex;
    private int maxNumVertex;

    public GraphOrthogonalList(int maxNumVertex){
        this.maxNumVertex = maxNumVertex;
        vertexs = (VertexOL<T>[])new VertexOL[maxNumVertex];
        numVertex = 0;
    }

    public boolean isFull(){
        return numVertex == maxNumVertex;
    }

    /**
     * 添加新顶点
     * @param data 新顶点的数据域
     */
    public void addVertex(T data){
        if (isFull()){
            return;
        }
        VertexOL<T> v = new VertexOL<>(data);
        vertexs[numVertex++] = v;
    }

    public void addEdge(int tail, int head){
        EdgeOL e = new EdgeOL(tail, head);
        //头插法，形成十字链表
        e.setTailLink(vertexs[tail].getFirstOut());
        vertexs[tail].setFirstOut(e);
        e.setHeadLink(vertexs[head].getFirstIn());
        vertexs[head].setFirstIn(e);
    }

    /**
     * 删除一个边结点
     * @param tail
     * @param head
     */
    public void removeEdge(int tail, int head){
        removeFromTailList(tail, head);
        removeFromHeadList(tail, head);
    }

    /**
     * 从邻接表中删除一个边结点
     * @param tail
     * @param head
     */
    private void removeFromTailList(int tail, int head){
        EdgeOL e = vertexs[tail].getFirstOut();
        //从tailLink中删除它
        if (e != null && e.getHeadVex() == head){
            //如果e是第一个但不是最后一个结点，删除它
            if (e.getTailLink() != null){
                vertexs[tail].setFirstOut(e.getTailLink());
            }else {
                //如果e是第一个也是最后一个结点，删除它
                vertexs[tail].setFirstOut(null);
            }
        }else if (e != null){
            //如果e不是第一个结点，那么遍历链表找到要删除的边结点的上一个结点！！
            while (e.getTailLink() != null && e.getTailLink().getHeadVex() != head){
                e = e.getTailLink();
            }
            if (e.getHeadVex() != head){
                //throw new NullPointerException();
                //这里不能抛异常，因为后面要遍历删除边，抛异常会使程序终止
                return;
            }else {
                e.setTailLink(e.getTailLink().getTailLink());
            }
        }
    }

    /**
     * 从逆邻接表中删除一个边结点
     * @param tail
     * @param head
     */
    private void removeFromHeadList(int tail, int head){
        //从headLink中删除它
        EdgeOL e = vertexs[head].getFirstOut();
        if (e != null && e.getTailVex() == tail){
            //如果e1是第一个但不是最后一个结点，删除它
            if (e.getHeadLink() != null){
                vertexs[head].setFirstIn(e.getHeadLink());
            }else {
                //如果e1是第一个也是最后一个结点，删除它
                vertexs[head].setFirstIn(null);
            }
        }else if (e != null){
            //如果e1不是第一个结点，那么遍历链表找到要删除的边结点的上一个结点！！

            while (e.getHeadLink() != null
                    && e.getHeadLink().getTailVex() != tail){
                e = e.getHeadLink();
            }
            if (e.getTailVex() != tail){
                //throw new NullPointerException();
                return;
            }else {
                e.setHeadLink(e.getHeadLink().getHeadLink());
            }
        }
    }

    /**
     * 删除index角标的顶点
     * @param index
     */
    public void removeVertex(int index){
        if (index >= numVertex){
            throw new NullPointerException();
        }

        //删除与该顶点有关的所有边
        for (int i = numVertex - 1; i > 0; i--){
            removeEdge(index, i);
            removeEdge(i, index);
        }

        //删除该结点
        for (int i = index; i < numVertex - 1; i++){
            vertexs[i] = vertexs[i + 1];
        }
        numVertex--;
    }
}

package Graph.AMGraph;

/*
邻接多重表的代码实现
 */
public class GraphAM <T> {
    private VertexAM<T>[] vertexs;
    private int numVertex;
    private int maxNumVertex;

    public GraphAM(int maxNumVertex){
        this.maxNumVertex = maxNumVertex;
        this.vertexs = (VertexAM<T>[])new VertexAM[maxNumVertex];
        numVertex = 0;
    }

    public boolean isFull(){
        return numVertex == maxNumVertex;
    }

    public void addVertex(T data){
        if (isFull()){
            return;
        }
        vertexs[numVertex++] = new VertexAM<>(data);
    }

    /**
     * 将新结点连在iLink链表的链尾和jLink链表的链尾
     * @param ivex
     * @param jvex
     */
    public void addEdge(int ivex, int jvex){
        EdgeAM e = new EdgeAM(ivex, jvex);
        if (vertexs[ivex].getFirstEdge() == null) {
            vertexs[ivex].setFirstEdge(e);
        } else if (vertexs[jvex].getFirstEdge() == null){
            vertexs[jvex].setFirstEdge(e);
        } else {
            EdgeAM ptr = vertexs[ivex].getFirstEdge();
            while (ptr.getIlink() != null){
                ptr = ptr.getIlink();
            }
            ptr.setIlink(e);

            ptr = vertexs[jvex].getFirstEdge();
            while (ptr.getJlink() != null){
                ptr = ptr.getJlink();
            }
            ptr.setJlink(e);
        }
    }

    /**
     * 删除边，如果边结点直连顶点结点，则直接删除；若不直连，先找到边结点的上一个边结点，然后将上一个边结点的link域置空。
     * @param ivex
     * @param jvex
     */
    public void removeEdge(int ivex, int jvex) {
        if (vertexs[ivex].getFirstEdge() != null && vertexs[ivex].getFirstEdge().getJvex() == jvex) {
            vertexs[ivex].setFirstEdge(null);
        } else if (vertexs[jvex].getFirstEdge() != null && vertexs[jvex].getFirstEdge().getIvex() == ivex) {
            vertexs[jvex].setFirstEdge(null);
        } else {
            removeFromLink(ivex, jvex);

            removeFromLink(jvex, ivex);
        }
    }

    private void removeFromLink(int ivex, int jvex){
        EdgeAM ptr = vertexs[ivex].getFirstEdge();
        if (ptr == null){
            return;
        }
        while (ptr.getIlink() != null && ptr.getIlink().getJvex() != jvex) {
            ptr = ptr.getIlink();
        }
        if (ptr.getIlink() == null){
            return;
        }else {
            ptr.setIlink(null);
        }
    }

    /**
     * 先删除与本顶点相连的所有边，然后再删除顶点
     * @param index
     */
    public void removeVertex(int index){
        for (int i = 0; i < numVertex; i++){
            removeEdge(i, index);
            removeEdge(index, i);
        }

        for (int i = index; i < numVertex - 1; i++){
            vertexs[i] = vertexs[i + 1];
        }
        numVertex--;
    }
}

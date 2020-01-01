package Graph.AMGraph;

import Graph.MGraph.Vertex;

/*
邻接多重表的顶点定义
 */
public class VertexAM<T> {
    private T data;
    private EdgeAM firstEdge;

    public VertexAM(T data){
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public EdgeAM getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(EdgeAM firstEdge) {
        this.firstEdge = firstEdge;
    }
}

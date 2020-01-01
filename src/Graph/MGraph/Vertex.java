package Graph.MGraph;

/*
顶点的包装类
 */
public class Vertex<T>{
    private T data;
    public Vertex(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

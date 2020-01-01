package Graph.Adjust;

public class VertexL<T> {
    private T data;
    private EdgeL firstEdge;
    private int in;     //存储入度信息
    public VertexL(T data)
    {
        this.data = data;
    }

    public void setFirstEdge(EdgeL e){
        this.firstEdge = e;
    }

    public EdgeL getFirstEdge(){
        return firstEdge;
    }

    public T getData(){
        return data;
    }
}

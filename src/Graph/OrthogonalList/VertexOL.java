package Graph.OrthogonalList;

/*
    十字链表实现的图结构的顶点定义
 */
public class VertexOL<T> {
    private T data;
    private EdgeOL firstIn;
    private EdgeOL firstOut;

    public VertexOL(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public EdgeOL getFirstIn() {
        return firstIn;
    }

    public void setFirstIn(EdgeOL firstIn) {
        this.firstIn = firstIn;
    }

    public EdgeOL getFirstOut() {
        return firstOut;
    }

    public void setFirstOut(EdgeOL firstOut) {
        this.firstOut = firstOut;
    }
}

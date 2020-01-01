package Graph.CriticalPath;


public class VertexC {
    private int data;
    private int in; //入度
    private int out;    //出度
    private EdgeC edge;

    public VertexC(int data){
        this.data = data;
        this.in = 0;
        this.out = 0;
        edge = null;
    }

    public EdgeC getEdge() {
        return edge;
    }

    public void setEdge(EdgeC next) {
        this.edge = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
}

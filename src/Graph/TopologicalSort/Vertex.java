package Graph.TopologicalSort;

public class Vertex {
    private int data;
    private int in;
    private int out;
    private Edge edge;

    public Vertex(int data){
        this.data = data;
        this.in = 0;
        this.out = 0;
        edge = null;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge next) {
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

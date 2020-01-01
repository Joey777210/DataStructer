package Graph.TopologicalSort;

public class Edge {
    private int begin;
    private int end;
    private Edge next;

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }

    public Edge(int begin, int end){
        this.begin = begin;
        this.end = end;
        this.next = null;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

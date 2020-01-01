package Graph.CriticalPath;

public class EdgeC {
    private int begin;
    private int end;
    private int weight;
    private EdgeC next;

    public EdgeC(int begin, int end, int weight){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
        this.next = null;
    }

    public EdgeC getNext() {
        return next;
    }

    public void setNext(EdgeC next) {
        this.next = next;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

package Graph.Adjust;

public class EdgeL {
    private int adjvex; //存储邻接点对应的下标
    private EdgeL nextEdge;  //存储

    public EdgeL(int adjvex){
        this.adjvex = adjvex;
    }

    public EdgeL(int adjvex, EdgeL e){
        this.adjvex = adjvex;
        this.nextEdge = e;
    }

    public EdgeL getNextEdge(){
        return nextEdge;
    }

    public void setNextEdge(EdgeL e){
        this.nextEdge = e;
    }

    public int getAdjvex(){
        return adjvex;
    }
}

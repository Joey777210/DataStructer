package Graph.AMGraph;

/*
邻接多重表的边结点定义
 */
public class EdgeAM {
    private int ivex;
    private int jvex;
    private EdgeAM ilink;
    private EdgeAM jlink;

    public EdgeAM(int ivex, int jvex){
        this.ivex = ivex;
        this.jvex = jvex;
    }

    public int getIvex() {
        return ivex;
    }

    public void setIvex(int ivex) {
        this.ivex = ivex;
    }

    public int getJvex() {
        return jvex;
    }

    public void setJvex(int jvex) {
        this.jvex = jvex;
    }

    public EdgeAM getIlink() {
        return ilink;
    }

    public void setIlink(EdgeAM ilink) {
        this.ilink = ilink;
    }

    public EdgeAM getJlink() {
        return jlink;
    }

    public void setJlink(EdgeAM jlink) {
        this.jlink = jlink;
    }
}

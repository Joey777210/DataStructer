package Graph.OrthogonalList;

/*
    十字链表实现的图结构的弧定义
 */
public class EdgeOL {
    private int tail;
    private int head;

    public EdgeOL(int tail, int head) {
        this.tail = tail;
        this.head = head;
    }

    public int getTailVex() {
        return tail;
    }

    public void setTailVex(int tailVex) {
        this.tail = tailVex;
    }

    public int getHeadVex() {
        return head;
    }

    public void setHeadVex(int headVex) {
        this.head = headVex;
    }

    public EdgeOL getHeadLink() {
        return headLink;
    }

    public void setHeadLink(EdgeOL headLink) {
        this.headLink = headLink;
    }

    public EdgeOL getTailLink() {
        return tailLink;
    }

    public void setTailLink(EdgeOL tailLink) {
        this.tailLink = tailLink;
    }

    private EdgeOL headLink;
    private EdgeOL tailLink;


}

package Graph.TopologicalSort;

import java.util.Stack;

public class DigraphAdjust {
    private int numVertex;
    private int maxNumVertex;
    private Vertex[] vertexs;

    public DigraphAdjust(int maxNumVertex){
        this.maxNumVertex = maxNumVertex;
        vertexs = new Vertex[maxNumVertex];
        numVertex = 0;
    }

    public void addVertex(int data){
        Vertex newVertex = new Vertex(data);
        vertexs[numVertex++] = newVertex;
    }

    public void addEdge(int begin, int end){
        Edge newEdge = new Edge(begin, end);
        Vertex beginV = vertexs[begin];
        beginV.setOut(vertexs[begin].getOut() + 1);
        vertexs[end].setIn(vertexs[end].getIn() + 1);
        if (beginV.getEdge() == null) {
            beginV.setEdge(newEdge);
        }else {
            Edge e = beginV.getEdge();
            beginV.setEdge(newEdge);
            newEdge.setNext(e);
        }
    }

    public void deleteVertex(int index){
        Edge e = vertexs[index].getEdge();
        int k;
        for (; e != null; e = e.getNext()){
            vertexs[e.getEnd()].setIn(vertexs[e.getEnd()].getIn() - 1);
            k = vertexs[e.getEnd()].getIn();
            if (k == 0){
                zeroIn.push(e.getEnd());
            }
        }
        //这里并非真的删除顶点，而是只让后续结点的入度减一即可
        /*
        for (int i = index; i < numVertex - 1; i++) {
            vertexs[i] = vertexs[i + 1];
        }
        numVertex--;
         */
    }

    private Stack<Integer> zeroIn = new Stack<>();
    //拓扑排序算法
    public boolean TopologicalSort(){

        for (int i = 0; i < numVertex; i++){
            if (vertexs[i].getIn() == 0){
                zeroIn.push(i);
            }
        }

        int count = 0;
        while (!zeroIn.isEmpty()){
            int node = zeroIn.pop();
            System.out.println(vertexs[node].getData() + "  " + ++count);

            deleteVertex(node);
        }

        if (count < numVertex){
            return false;
        }else {
            return true;
        }
    }

}

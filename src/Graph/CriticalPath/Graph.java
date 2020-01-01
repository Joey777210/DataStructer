package Graph.CriticalPath;

import Graph.TopologicalSort.Edge;
import Graph.TopologicalSort.Vertex;

import java.util.Stack;

public class Graph {
    private int numVertex;
    private int maxNumVertex;
    private VertexC[] vertexs;

    public Graph(int maxNumVertex){
        this.maxNumVertex = maxNumVertex;
        vertexs = new VertexC[maxNumVertex];
        numVertex = 0;
    }

    public void addVertex(int data){
        VertexC newVertex = new VertexC(data);
        vertexs[numVertex++] = newVertex;
    }

    public void addEdge(int begin, int end, int weight){
        EdgeC newEdge = new EdgeC(begin, end, weight);
        VertexC beginV = vertexs[begin];
        beginV.setOut(vertexs[begin].getOut() + 1);
        vertexs[end].setIn(vertexs[end].getIn() + 1);
        if (beginV.getEdge() == null) {
            beginV.setEdge(newEdge);
        }else {
            EdgeC e = beginV.getEdge();
            beginV.setEdge(newEdge);
            newEdge.setNext(e);
        }
    }

    public void deleteVertex(int index){
        EdgeC e = vertexs[index].getEdge();
        int k;
        for (; e != null; e = e.getNext()){
            vertexs[e.getEnd()].setIn(vertexs[e.getEnd()].getIn() - 1);
            k = vertexs[e.getEnd()].getIn();
            if (k == 0){
                zeroIn.push(e.getEnd());
            }
            //关键部分：求各顶点事件的最早发生时间。
            //即刚刚被删除的顶点的最早发生时间加上这两点之间权值 与 要求的顶点之前的最早发生时间 之间取较大值
            if(etv[topoStack.peek()]
                    + e.getWeight()
                    > etv[e.getEnd()]){
                etv[e.getEnd()] = etv[topoStack.peek()] + e.getWeight();
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
    private Stack<Integer> topoStack = new Stack<>();
    private int[] etv; //事件的最早发生时间
    private int[] ltv; //事件的最晚发生时间

    //拓扑排序算法
    public boolean TopologicalSort(){

        for (int i = 0; i < numVertex; i++){
            etv = new int[numVertex];
            if (vertexs[i].getIn() == 0){
                zeroIn.push(i);
            }
            etv[i] = 0;
        }

        int count = 0;
        while (!zeroIn.isEmpty()){
            int node = zeroIn.pop();
            //System.out.println(vertexs[node].getData() + "  " + ++count);
            topoStack.push(node);   //将弹出的顶点序号压入拓扑排序的栈
            deleteVertex(node);
        }

        if (count < numVertex){
            return false;
        }else {
            return true;
        }
    }

     public void CriticalPath(){
        int[] ete = new int[numVertex]; //保存边上活动的最早开始时间,其index表示该边begin的index
        int[] lte = new int[numVertex]; //保存边上活动的最晚开始时间
        EdgeC e;    //下面用来保存顶点的临时变量
        TopologicalSort();  //先通过拓扑排序求出etv


        //初始化ltv
         ltv = new int[numVertex];
        for (int i = 0; i < numVertex; i++){
            ltv[i] = etv[numVertex - 1];
        }

        while (!topoStack.isEmpty()) {
            int node = topoStack.pop();
            int adj;

            //求得每一个顶点事件的最晚发生时间,类似反向拓扑排序
            for (e = vertexs[node].getEdge(); e != null; e = e.getNext()) {
                adj = e.getEnd();
                if (ltv[adj] - e.getWeight() < ltv[node]) {
                    ltv[node] = ltv[adj] - e.getWeight();
                }
            }
            for (int i : ltv) {
                System.out.println(i);
            }
        }

        //求关键路径 .
         +
        for (int index = 0; index < numVertex; index++){
            for (e = vertexs[index].getEdge(); e != null; e = e.getNext()){
                ete[index] = etv[index];
                lte[index] = ltv[e.getEnd()] - e.getWeight();
                if (ete[index] == lte[index]){
                    System.out.printf("(%d,%d) : %d \n", vertexs[index].getData(), vertexs[e.getEnd()].getData(), e.getWeight());
                }
            }
        }
     }
}

package Graph.Adjust;

import Stack.ArrayStack;
import Stack.LinkedStack;

import java.util.ArrayDeque;

/*
    无向图（无权值）的邻接表存储。
    邻接表用LinkedList
 */
public class GraphAdjList <T>{
    private VertexL<T>[] vertexs;
    private int numVertex;
    private int maxNumVertex;

    public GraphAdjList(int maxNumVertex){
        this.maxNumVertex = maxNumVertex;
        this.vertexs =(VertexL<T>[]) new VertexL[maxNumVertex];
        numVertex = 0;
    }

    public boolean isFull(){
        return numVertex == maxNumVertex;
    }

    private int getNumVertex(){
        return numVertex;
    }
    /**
     * 添加顶点
     * @param data
     */
    public void addVertex(T data){
        if (isFull()){
            throw new IndexOutOfBoundsException();
        }
        VertexL<T> v = new VertexL<T>(data);
        vertexs[numVertex++] = v;
    }

    /**
     * 添加边
     * @param data1
     * @param data2
     */
    public void addEdge(T data1, T data2){
        int indexOfData1 = getIndex(data1);
        int indexOfData2 = getIndex(data2);

        if (vertexs[indexOfData1].getFirstEdge() == null){
            vertexs[indexOfData1].setFirstEdge(new EdgeL(indexOfData2));
        }else {
            vertexs[indexOfData1].getFirstEdge().setNextEdge(new EdgeL(indexOfData2, vertexs[indexOfData1].getFirstEdge().getNextEdge()));
        }

        if (vertexs[indexOfData2].getFirstEdge() == null){
            vertexs[indexOfData2].setFirstEdge(new EdgeL(indexOfData1));
        }else {
            vertexs[indexOfData2].getFirstEdge().setNextEdge(new EdgeL(indexOfData1, vertexs[indexOfData1].getFirstEdge().getNextEdge()));
        }
    }


    private int getIndex(T data){
        int i = 0;
        for (; i < numVertex; i++){
            if (data.equals(vertexs[i].getData())){
                break;
            }

        }
        if (!data.equals(vertexs[i].getData()) && i == numVertex){
            throw new NullPointerException();
        }
        return i;
    }

    /**
     * 删除边
     * @param data1
     * @param data2
     */
    public void removeEdge(T data1, T data2){
        int indexOfData1 = getIndex(data1);
        int indexOfData2 = getIndex(data2);

        VertexL v = vertexs[indexOfData1];
        EdgeL e = v.getFirstEdge();
        if (e.getAdjvex() == indexOfData2){
            if (v.getFirstEdge().getNextEdge() == null) {
                v.setFirstEdge(null);
            }else {
                v.setFirstEdge(e.getNextEdge());
            }
        }else {
            while (e.getNextEdge().getAdjvex() != indexOfData2){
                e = e.getNextEdge();
            }
            if (e.getNextEdge().getNextEdge() != null) {
                e.setNextEdge(e.getNextEdge().getNextEdge());
            }else {
                e.setNextEdge(null);
            }
        }
    }

    /**
     * 删除顶点
     * @param data
     */
    public void removeVertex(T data){
        int index = getIndex(data);
        for (int i = 0; i < numVertex; i++){
            if (i == index){
                continue;
            }
            removeEdge(vertexs[i].getData(), data);
        }
        for (int i = index; i < numVertex - 1; i++){
            vertexs[i] = vertexs[i + 1];
        }
    }

    //标识结点是否被访问过
    private boolean[] visited;
    //深度优先遍历操作入口
    public void DFSTraverse(){
        this.visited = new boolean[getNumVertex()];
        for (boolean bool : visited){
            bool = false;
        }
        //若该顶点没被访问过，则从该顶点为起点深度优先遍历,若为连通图，则只会执行一次DFS
        for (int i = 0; i < getNumVertex(); i++){
            if (!visited[i]) {
                DFS(i);
            }
        }
    }
    //深度优先遍历算法
    private void DFS(int i) {
        visited[i] = true;
        System.out.println(vertexs[i].getData());
        if (!(vertexs[i].getFirstEdge() == null)) {
            EdgeL e = vertexs[i].getFirstEdge();  //取到邻接表的第一个元素
            while (e != null) {      //邻接表不为空
                if (!visited[e.getAdjvex()]) {     //如果该元素没有被访问过，则以该元素为起点再次进行深度优先遍历；如果访问过，则取到邻接表的下一个结点（可以理解为往右走走不通，变成往左走）
                    DFS(e.getAdjvex());
                }
                e = e.getNextEdge();
            }
        }
    }

    //——————————————————————————————————————————DFS的非递归写法————————————————————————————————————
    //标识结点是否被访问过
    private boolean[] visited_2;
    //深度优先遍历操作入口
    public void DFSTraverse_2(){
        this.visited = new boolean[getNumVertex()];
        for (int i = 0; i < numVertex; i++){
            visited[i] = false;
        }
        for (int i = 0; i < numVertex; i++) {
            ArrayStack<Integer> s = new ArrayStack<>();
            visit(i);
            s.push(i);
            while (!s.isEmpty()) {
                int topIndex = s.getTopData();

                EdgeL p = vertexs[topIndex].getFirstEdge();
                //遍历邻接表，直到找到邻接表中没有被访问过的结点
                while (p != null) {
                    if (!visited[p.getAdjvex()]) {
                        visit(p.getAdjvex());
                        s.push(p.getAdjvex());
                    } else if(p.getNextEdge() != null && !visited[p.getNextEdge().getAdjvex()]){
                        p = p.getNextEdge();
                    }
                }
                s.pop();
            }
        }
    }

//    public void visit(int i){
//        System.out.println(vertexs[i].getData());
//        visited[i] = true;
//    }

    //——————————————————————————————————————————————————————BFS广度优先遍历——————————————————————————————————————————————————————————————
    public void BFSTraverse(){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        this.visited = new boolean[getNumVertex()];
        for (int i = 0; i < getNumVertex(); i++){
            visited[i] = false;
        }

        for (int i = 0; i < getNumVertex(); i++){
            if (!visited[i]) {
                queue.add(i);
            }
            while (!queue.isEmpty()){
                int node = queue.remove();
                visit(node);
                EdgeL e = vertexs[node].getFirstEdge();
                if (e != null && !queue.contains(e.getAdjvex()) && !visited[e.getAdjvex()]) {
                    queue.add(e.getAdjvex());

                    while (e != null && e.getNextEdge() != null && !queue.contains(e.getAdjvex()) && !visited[e.getAdjvex()]) {
                        queue.add(e.getAdjvex());
                        e = e.getNextEdge();
                    }
                }
            }
        }
    }

    private void visit(int i){
        System.out.println(vertexs[i].getData());
        visited[i] = true;
    }

}

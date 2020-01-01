package Graph.MGraph;

import java.util.*;

import Stack.ArrayStack;

import javax.xml.stream.XMLInputFactory;

/*
邻接矩阵实现图的创建
 */
public class MGraph<T> {
    private boolean flag;   //若为true 则为有向图，否则为无向图
    private Vertex<T>[] vertexs;
    private int[][] edges;
    private int numVertex;          //顶点的实际数量
    private int maxNumVertex;       //顶点的最大数量
    private int INFINITY = 65535;


    public MGraph(int maxNumVertex, boolean flag){
        this.maxNumVertex = maxNumVertex;
        numVertex = 0;
        this.vertexs = (Vertex<T>[]) new Vertex[maxNumVertex];
        this.edges = new int[maxNumVertex][maxNumVertex];
        initEdges();

        this.flag = flag;
    }

    private void initEdges(){
        for (int i = 0; i < maxNumVertex; i++) {
            for (int j = 0; j < maxNumVertex; j++) {
                edges[i][j] = INFINITY;
            }
        }
        for (int i = 0; i < maxNumVertex; i++) {
            edges[i][i] = 0;
        }
    }

    public int getNumVertex(){
        return numVertex;
    }

    public int getMaxNumVertex(){
        return maxNumVertex;
    }

    public boolean isFull(){
        return numVertex == maxNumVertex;
    }

    public void addVertex(T data){
        if(isFull()){
            throw new RuntimeException("图满了");
        }
        Vertex<T> v = new Vertex<T>(data);
        vertexs[numVertex++] = v;
    }

    /**
     * 删除图中与data相等的顶点
     * @param data 要删除的顶点的data值
     * @return 返回删除了几个顶点
     */
    public int removeVertex(T data){
        int flag = 0;
        for (int i = 0; i < numVertex; i++){
            if (vertexs[i].getData().equals(data)){
                for (int j = i; j < numVertex - 1; j++){
                    vertexs[j] = vertexs[j + 1];
                }
                //删除矩阵的第 i 行
                for (int row = i; row < numVertex - 1; row++){
                    for (int col = 0; col < numVertex; col++){
                        edges[col][row] = edges[col][row + 1];
                    }
                }
                //删除矩阵的第 i 列
                for (int row = 0; row < numVertex; row++){
                    for (int col = i; col < numVertex - 1; col++){
                        edges[col][row] = edges[col + 1][row];
                    }
                }
                numVertex--;
                flag++;
            }
        }
        return flag;
    }

    private int getIndexOfData(T data){
        int i = 0;
        while (!vertexs[i].getData().equals(data)){
            i++;
        }
        if (vertexs[i].getData().equals(data)) {
            return i;
        }else {
            throw new NullPointerException();
        }
    }

    /**
     * 若为无向图，data的顺序随意；若为有向图，则添加的边是data1指向data2
     * @param data1 弧尾
     * @param data2 弧头
     * @param weight 权值
     */
    public void addEdge(T data1, T data2, int weight){
        if (flag) {
            int index1 = getIndexOfData(data1);
            int index2 = getIndexOfData(data2);
            edges[index1][index2] = weight;
        }else {
            int index1 = getIndexOfData(data1);
            int index2 = getIndexOfData(data2);
            edges[index1][index2] = weight;
            edges[index2][index1] = weight;
        }
    }

    public void removeEdge(T data1, T data2){
        int index1 = getIndexOfData(data1);
        int index2 = getIndexOfData(data2);
        edges[index1][index2] = INFINITY;
    }

    public void printMatrix(){
        for (int row = 0; row < numVertex; row++){
            for (int col = 0; col < numVertex; col++){
                System.out.print(edges[row][col] + "\t");
            }
            System.out.println();
        }
    }

    //标识结点是否被访问过
    private boolean[] visited;
    //深度优先遍历操作入口
    public void DFSTraverse(){
        this.visited = new boolean[getNumVertex()];
        for (int i = 0; i < numVertex; i++){
            visited[i] = false;
        }
        //若该顶点没被访问过，则从该顶点为起点深度优先遍历,若为连通图，则只会执行一次DFS
        for (int i = 0; i < getNumVertex(); i++){
            DFS(i);
        }
    }
    //深度优先遍历算法
    private void DFS(int i) {
        visited[i] = true;
        System.out.println(vertexs[i].getData());
        for (int j = 0; j < numVertex; j++){
            if ( !visited[j] && (edges[i][j] != 0 || edges[i][j] != INFINITY)){
                DFS(j);
            }
        }
    }

    //————————————————————————————————————DFS的非递归写法——————————————————————————————————————————
    //标识结点是否被访问过
    private boolean[] visited_2;

    /**
     * 利用栈来实现，如果该顶点被访问，则压栈；
     * 当走到死胡同，查询栈顶元素是否有其他未被访问的邻接点，如果有，则访问它，并压栈，如果没有，则将栈顶元素弹栈，直到栈为空。
     */
    public void DFSTraverse_2(){
        this.visited = new boolean[numVertex];
        //如果是连通图，那么这个循环只进行一次。
        for (int i = 0; i < numVertex; i++){
            visited[i] = false;
        }

        ArrayStack<Integer> s = new ArrayStack<Integer>();
        int i = 0;
        visit(i);
        s.push(i);
        while (!s.isEmpty()){
            int j = 0;
            int top = s.getTop();
            for (; j < numVertex; j++){
                if ( !visited[j] && (edges[top][j] != 0 || edges[top][j] != INFINITY)){
                    visit(j);
                    visited[j] = true;
                    s.push(j);
                    break;
                }
            }
            if (j == numVertex){
                s.pop();
            }
        }
    }
/*
    private void visit(int i){
        System.out.println(vertexs[i].getData());
        visited[i] = true;
    }
 */
//—————————————————————————————————————————————————BFS广度优先遍历————————————————————————————————————————————————————————

    public void BFSTraverse(){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        this.visited = new boolean[getNumVertex()];
        for (int i = 0; i < numVertex; i++){
            visited[i] = false;
        }
        for(int i = 0; i < numVertex; i++){
            if (!visited[i]) {
                queue.add(i);
            }
            while (!queue.isEmpty()){
                int row = queue.remove();
                visit(row);
                for (int j = 0; j < numVertex; j++){
                    //如果存在这条边，且这条边的邻接点没有被访问过，且邻接点不在队列中，则将该邻接点入队
                    if ((edges[row][j] != 0 && edges[row][j] != INFINITY) && !visited[j] && !queue.contains(j)){
                        queue.add(j);
                    }
                }
            }
        }
    }

    private void visit(int i){
        System.out.println(vertexs[i].getData());
        visited[i] = true;
    }

    //——————————————————————————————————————————————————————普里姆算法最小生成树————————————————————————————————————————————————————————
    public void MiniSpanTree_Prim(){

        int[] adjvex = new int[numVertex];
        int[] lowcost = new int[numVertex];

        adjvex[0] = 0;
        lowcost[0] = 0;
        /*
        for (int i = 1; i < numVertex; i++){
            lowcost[i] = INFINITY;
        }

         */

        for (int i = 1; i < numVertex; i++)
        {
            lowcost[i] = edges[0][i];
            adjvex[0] = 0;
        }

        for (int i = 1; i < numVertex; i++){
            int min = INFINITY;
            int k = 0;
            for (int j = 1; j < numVertex; j++){
                if (lowcost[j] != 0 && lowcost[j] < min){
                  min = lowcost[j];
                  k = j;
                }
            }
            System.out.printf("(%d,%d,w = %d)加入生成树",adjvex[k],k,min);
            System.out.println();

            lowcost[k] = 0;
            for (int j = 1; j < numVertex; j++){
                if (lowcost[j] != 0 && lowcost[j] > edges[k][j]){
                    lowcost[j] = edges[k][j];
                    adjvex[j] = k;
                }
            }
        }
    }

    //——————————————————————————————————————————————————————————————————————克鲁斯卡尔算法最小生成树————————————————————————————————————————————————————————————————————————————————————————

    /**
     * 得到排序好的边集数组，用ArrayList存储
     * @return
     */
    public ArrayList<Edge> getOrderedEdges() {
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int row = 0; row < numVertex; row++){
            for (int col = row; col < numVertex; col++){
                if(edges[row][col] != 0 && edges[row][col] != INFINITY){
                    edgeList.add(new Edge(row, col, edges[row][col]));
                }
            }
        }
        Collections.sort(edgeList);
        return edgeList;
    }

    /**
     * 克鲁斯卡尔算法
     */
    public void MiniSpanTree_Kruskal(){
        ArrayList<Edge> edgeList = getOrderedEdges();
        int[] parent = new int[numVertex];
        for (int i = 0; i < numVertex; i++){
            parent[i] = 0;
        }

        for (int i = 0; i < edgeList.size(); i++){
            int m = findRoot(edgeList.get(i).getBegin(), parent);
            int n = findRoot(edgeList.get(i).getEnd(), parent);
            if (m != n){
                link(edgeList.get(i), parent, m, n);
            }
        }


    }

    /*
        连接两点，并且设置parent数组
     */
    private void link(Edge edge, int[] parent, int m, int n) {
        System.out.printf("(%d,%d),weight = %d 加入最小生成树", edge.getBegin(), edge.getEnd(), edge.getWeight());
        System.out.println();

        parent[m] = n;
    }

    /*
    找到本子树的根节点
     */
    private int findRoot(int root, int[] parent) {
        while (parent[root] > 0){
            root = parent[root];
        }
        return root;
    }

    /*
        定义边结构的内部类。
     */
    private class Edge implements Comparable<Edge> {
        private int begin;
        private int end;
        private int weight;

        private Edge(int begin, int end, int weight){
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
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

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    //———————————————————————————————————————————————————————迪杰斯特拉算法最短路径——————————————————————————————————————————————————————————————
    public void ShortestPath_Dijkstra(){
        int[] ShortPathTable= new int[numVertex];
        int[] Patharc = new int[numVertex];
        int[] finals = new int[numVertex];

        //初始化
        for (int i = 0; i < numVertex; i++){
            ShortPathTable[i] = edges[0][i];    //初始化为v0的邻接边
            Patharc[i] = 0;
            finals[i] = 0;
        }
        finals[0] = 1;  //v0无需找自己的邻边

        int minIndex = 0;   //用来保存当前已经发现的点中到V0距离最短的点
        for (int i = 1; i < numVertex; i++){
            //找到目前距离V0最近的点
            int min = INFINITY;
            for (int index = 0; index < numVertex; index++){
                if (finals[index] != 1 && ShortPathTable[index] < min){
                    minIndex = index;
                    min = ShortPathTable[index];
                }
            }
            finals[minIndex] = 1;
            //更新还未加入最短路径的点到V0的距离
            for (int index = 0; index < numVertex; index++){
                if (finals[index] != 1 && (ShortPathTable[index] > (min + edges[minIndex][index]))){
                    ShortPathTable[index] = min + edges[minIndex][index];
                    Patharc[index] = minIndex;
                }
            }
        }
        //输出最短路径
        int s = 8;
        System.out.println(8);
        while ( s != 0){
            s = Patharc[s];
            System.out.println(s);
        }
    }

    //——————————————————————————————————————————————————————————————————弗洛伊德算法最短路径——————————————————————————————————————————————————————————————————————
    public void ShortestPath_Floyd(){
        int[][] ShortPathTable = new int[numVertex][numVertex];
        int[][] Patharc = new int[numVertex][numVertex];
        //初始化两个矩阵
        for (int row = 0; row < numVertex; row++){
            for (int col = 0; col < numVertex; col++){
                ShortPathTable[row][col] = edges[row][col];
                Patharc[row][col] = col;
            }
        }

        for (int path = 0; path < numVertex; path++){
            for (int row = 0; row < numVertex; row++){
                for (int col = 0; col < numVertex; col++){
                    if (ShortPathTable[row][col] > (ShortPathTable[row][path] + ShortPathTable[path][col])){
                        ShortPathTable[row][col] = (ShortPathTable[row][path] + ShortPathTable[path][col]);
                        Patharc[row][col] = Patharc[row][path];
                    }
                }
            }
        }

        //打印看结果
        for (int row = 0; row < numVertex; row++) {
            for (int col = 0; col < numVertex; col++) {
                System.out.print(ShortPathTable[row][col] + "\t");
            }
            System.out.println();
        }
        System.out.println("***********************************");
        for (int row = 0; row < numVertex; row++) {
            for (int col = 0; col < numVertex; col++) {
                System.out.print(Patharc[row][col] + "\t");
            }
            System.out.println();
        }
    }
}

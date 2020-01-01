package Graph.CriticalPath;

public class TestDemo {
    public static void main(String[] args) {
        Graph g = new Graph(10);
        for (int i = 0; i <= 9; i++){
            g.addVertex(i);
        }

        g.addEdge(0,1,3);
        g.addEdge(0,2,4);
        g.addEdge(1,3,5);
        g.addEdge(1,4,6);
        g.addEdge(2,3,8);
        g.addEdge(2,5,7);
        g.addEdge(3,4,3);
        g.addEdge(4,6,9);
        g.addEdge(4,7,4);
        g.addEdge(5,7,6);
        g.addEdge(6,9,2);
        g.addEdge(7,8,5);
        g.addEdge(8,9,3);

        g.CriticalPath();

    }
}


package Graph.TopologicalSort;

public class TestDemo {
    public static void main(String[] args){
        DigraphAdjust digraph = new DigraphAdjust(14);

        digraph.addVertex(0);
        digraph.addVertex(1);
        digraph.addVertex(2);
        digraph.addVertex(3);
        digraph.addVertex(4);
        digraph.addVertex(5);
        digraph.addVertex(6);
        digraph.addVertex(7);
        digraph.addVertex(8);
        digraph.addVertex(9);
        digraph.addVertex(10);
        digraph.addVertex(11);
        digraph.addVertex(12);
        digraph.addVertex(13);


        digraph.addEdge(0,4);
        digraph.addEdge(0,5);
        digraph.addEdge(0,11);
        digraph.addEdge(1,4);
        digraph.addEdge(1,8);
        digraph.addEdge(1,2);
        digraph.addEdge(2,5);
        digraph.addEdge(2,6);
        digraph.addEdge(2,9);
        digraph.addEdge(3,2);
        digraph.addEdge(3,13);
        digraph.addEdge(4,7);
        digraph.addEdge(5,8);
        digraph.addEdge(5,12);
        digraph.addEdge(6,5);
        digraph.addEdge(8,7);
        digraph.addEdge(9,10);
        digraph.addEdge(9,11);
        digraph.addEdge(10,13);
        digraph.addEdge(12,9);


        digraph.TopologicalSort();






    }
}

package Graph;

import Graph.AMGraph.GraphAM;
import Graph.Adjust.GraphAdjList;
import Graph.MGraph.MGraph;

public class TestDemo {
    public static void main(String[] args){
        MGraph<Integer> m = new MGraph<>(9,false);  //无向图
        for (int i = 0; i <= 8; i++){
            m.addVertex(i);
        }

        m.addEdge(0,1,1);
        m.addEdge(1,3,7);
        m.addEdge(0,2,5);
        m.addEdge(1,2,3);
        m.addEdge(1,4,5);
        m.addEdge(2,5,7);
        m.addEdge(2,4,1);
        m.addEdge(3,4,2);
        m.addEdge(3,6,3);
        m.addEdge(4,5,3);
        m.addEdge(4,7,9);
        m.addEdge(4,6,6);
        m.addEdge(5,7,5);
        m.addEdge(6,7,2);
        m.addEdge(6,8,7);
        m.addEdge(7,8,4);




//        m.printMatrix();
        m.ShortestPath_Floyd();

//        m.BFSTraverse();


    }
}



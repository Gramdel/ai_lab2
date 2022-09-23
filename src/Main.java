import graph.GraphManager;
import graph.Vertex;
import graph.SearchAlgorithms;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        if (GraphManager.createGraphFromFile("config.txt")) {
            System.out.println("Введены следующие города:");
            int count = 0;
            for (Vertex v : GraphManager.getGraph().values()) {
                System.out.println(++count + ". " + v);
            }
        } else {
            System.out.println("Введено 0 городов!");
            System.exit(-1);
        }

        HashMap<String, Vertex> graph = GraphManager.getGraph();
        SearchAlgorithms.runBreadthFirstSearch(graph.get("Москва"), graph.get("Рига"));
        SearchAlgorithms.runDepthFirstSearch(graph.get("Брест"), graph.get("Воронеж"));
        SearchAlgorithms.runDepthLimitSearch(graph.get("Брест"), graph.get("Воронеж"), 2);
        SearchAlgorithms.runIterativeDeepeningDepthFirstSearch(graph.get("Рига"), graph.get("Одесса"));
        SearchAlgorithms.runBidirectionalSearch(graph.get("Симферополь"), graph.get("Мурманск"));
        SearchAlgorithms.runBestFirstSearch(graph.get("Орел"), graph.get("Рига"));
    }
}

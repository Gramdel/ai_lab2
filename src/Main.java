import graph.GraphManager;
import graph.SearchAlgorithms;
import graph.Vertex;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        if (GraphManager.createGraphFromFile("graph_config.txt")) {
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
        SearchAlgorithms.runBidirectionalSearch(graph.get("Рига"), graph.get("Одесса"));
        SearchAlgorithms.runBestFirstSearch(graph.get("Брест"), graph.get("Казань"));
        if (GraphManager.createHeuristicsFromFile("heuristics_config.txt")) {
            SearchAlgorithms.runAStarSearch(graph.get("Брест"), graph.get("Казань"));
        }
    }
}

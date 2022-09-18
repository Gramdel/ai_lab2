import graph.GraphManager;
import graph.Vertex;
import graph.SearchAlgorithms;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        HashMap<String, Vertex> graph = GraphManager.createGraphFromFile("config.txt");
        if (graph != null) {
            System.out.println("Введены следующие города:");
            int count = 0;
            for (Vertex v : graph.values()) {
                System.out.println(++count + ". " + v);
            }
        } else {
            System.out.println("Введено 0 городов!");
        }

        SearchAlgorithms.runDepthFirstSearch(graph.get("Брест"), graph.get("Воронеж"));
        GraphManager.resetVisits(graph);
        SearchAlgorithms.runBreadthFirstSearch(graph.get("Москва"), graph.get("Рига"));
        GraphManager.resetVisits(graph);

        /*
        System.out.println();
        try {
            SearchAlgorithms.runDepthFirstSearch(graph.get("Киев"), graph.get("Житомир"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        GraphManager.resetVisits(graph);
         */
    }
}

import graph.GraphManager;
import graph.Vertex;
import graph.SearchAlgorithms;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Vertex> graph = GraphManager.createGraphFromFile("config.txt");
        if (graph != null) {
            for (Vertex v : graph.values()) {
                System.out.println(v.getName());
            }
        }

        System.out.println();
        try {
            SearchAlgorithms.runDepthFirstSearch(graph.get("Брест"), graph.get("Воронеж"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        GraphManager.resetVisits(graph);

        System.out.println();
        try {
            SearchAlgorithms.runDepthFirstSearch(graph.get("Киев"), graph.get("Житомир"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        GraphManager.resetVisits(graph);
    }
}

import graph.GraphCreator;
import graph.Vertex;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Vertex> graph = GraphCreator.createGraphFromFile("config.txt");
        if (graph != null) {
            for (Vertex v : graph) {
                System.out.println(v.getName());
            }
        }
    }
}

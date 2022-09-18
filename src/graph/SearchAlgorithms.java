package graph;

import java.util.LinkedList;

public class SearchAlgorithms {
    private static final LinkedList<Vertex> path = new LinkedList<>();

    public static void runDepthFirstSearch(Vertex start, Vertex finish) {
        depthFirstSearch(start, finish);
        path.addFirst(start);
        for (Vertex v : path) {
            System.out.println(v.getName());
        }
        path.clear();
    }

    private static boolean depthFirstSearch(Vertex current, Vertex finish) {
        current.setWasVisited(true);
        if (current != finish) {
            for (Vertex v : current.getNeighbours().keySet()) {
                if (!v.getWasVisited()) {
                    if (depthFirstSearch(v, finish)) {
                        path.addFirst(v);
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
}

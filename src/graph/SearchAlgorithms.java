package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class SearchAlgorithms {
    private static final LinkedList<Vertex> path = new LinkedList<>();

    public static void runBreadthFirstSearch(Vertex start, Vertex finish) {
        LinkedList<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(start, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            current.setWasVisited(true);
            if (current == finish) {
                break;
            }
            for (Vertex v : current.getNeighbours().keySet()) {
                if (!v.getWasVisited()) {
                    parents.put(v, current);
                    queue.add(v);
                }
            }
        }

        Vertex parent = finish;
        while (parent != null) {
            path.addFirst(parent);
            parent = parents.get(parent);
        }
        System.out.println("\nПоиск в ширину выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
    }

    public static void runDepthFirstSearch(Vertex start, Vertex finish) {
        depthFirstSearch(start, finish);
        path.addFirst(start);
        System.out.println("\nПоиск в глубину выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
    }

    private static boolean depthFirstSearch(Vertex current, Vertex finish) {
        current.setWasVisited(true);
        if (current != finish) {
            for (Vertex v : current.getNeighbours().keySet()) {
                if (!v.getWasVisited() && depthFirstSearch(v, finish)) {
                    path.addFirst(v);
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private static void printPath() {
        Iterator<Vertex> it = path.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            if (it.hasNext()) {
                System.out.println(" ↓");
            }
        }
    }
}

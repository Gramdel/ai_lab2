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
        depthLimitSearch(start, finish, Integer.MAX_VALUE);
        path.addFirst(start);
        System.out.println("\nПоиск в глубину выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
    }

    public static void runDepthLimitSearch(Vertex start, Vertex finish, int limit) {
        depthLimitSearch(start, finish, limit);
        path.addFirst(start);
        if (path.getLast() == finish) {
            System.out.println("\nПоиск в глубину (ограничение глубины " + limit + ") выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
            printPath();
        } else {
            System.out.println("\nПоиск в глубину (ограничение глубины " + limit + ") выполнен, но путь из города " + start + " в город " + finish + " не найден! Возможно, не хватило глубины.");
        }
        path.clear();
    }

    private static boolean depthLimitSearch(Vertex current, Vertex finish, int limit) {
        current.setWasVisited(true);
        if (current == finish) {
            return true;
        } else if (limit != 0) {
            for (Vertex v : current.getNeighbours().keySet()) {
                if (!v.getWasVisited() && depthLimitSearch(v, finish, limit - 1)) {
                    path.addFirst(v);
                    return true;
                }
            }
        }
        return false;
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

package graph;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SearchAlgorithms {
    private static final LinkedList<Vertex> path = new LinkedList<>();

    public static void runBreadthFirstSearch(Vertex start, Vertex finish) {
        System.out.println("\nРабота поиска в ширину:");
        LinkedList<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(start, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.println(current);
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
        GraphManager.resetVisits();
    }

    public static void runDepthFirstSearch(Vertex start, Vertex finish) {
        System.out.println("\nРабота поиска в глубину:");
        depthLimitSearch(start, finish, Integer.MAX_VALUE);
        path.addFirst(start);
        System.out.println("\nПоиск в глубину выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
        GraphManager.resetVisits();
    }

    public static void runDepthLimitSearch(Vertex start, Vertex finish, int limit) {
        System.out.println("\nРабота поиска в глубину (ограничение глубины " + limit + "):");
        depthLimitSearch(start, finish, limit);
        path.addFirst(start);
        if (path.getLast() == finish) {
            System.out.println("\nПоиск в глубину (ограничение глубины " + limit + ") выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
            printPath();
        } else {
            System.out.println("\nПоиск в глубину (ограничение глубины " + limit + ") выполнен, но путь из города " + start + " в город " + finish + " не найден! Возможно, не хватило глубины.");
        }
        path.clear();
        GraphManager.resetVisits();
    }

    public static void runIterativeDeepeningDepthFirstSearch(Vertex start, Vertex finish) {
        int limit;
        for (limit = 1; limit < Integer.MAX_VALUE; limit++) {
            path.clear();
            GraphManager.resetVisits();
            System.out.println("\nРабота поиска с итеративным углублением (глубина " + limit + "):");
            if (depthLimitSearch(start, finish, limit)) {
                break;
            }
        }
        path.addFirst(start);
        System.out.println("\nПоиск с итеративным углублением выполнен (глубина " + limit + ")! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
        GraphManager.resetVisits();
    }

    public static void runBidirectionalSearch(Vertex start, Vertex finish) {
        System.out.println("\nРабота двунаправленного поиска:");
        LinkedList<Vertex> queueA = new LinkedList<>();
        LinkedList<Vertex> queueB = new LinkedList<>();
        HashMap<Vertex, Pair<Boolean, Boolean>> visitMap = createVisitMap();
        HashMap<Vertex, Vertex> parentsA = new HashMap<>();
        HashMap<Vertex, Vertex> parentsB = new HashMap<>();
        parentsA.put(start, null);
        parentsB.put(finish, null);
        queueA.add(start);
        queueB.add(finish);
        Vertex parent = null;

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            Vertex currentA = queueA.poll();
            Vertex currentB = queueB.poll();
            System.out.println(currentA + ", " + currentB);
            if (currentA == currentB || visitMap.get(currentA).getValue()) {
                parent = currentA;
                break;
            } else if (visitMap.get(currentB).getKey()) {
                parent = currentB;
                break;
            }
            visitMap.put(currentA, new Pair<>(true, false));
            visitMap.put(currentB, new Pair<>(false, true));
            for (Vertex v : currentA.getNeighbours().keySet()) {
                if (!visitMap.get(v).getKey()) {
                    parentsA.put(v, currentA);
                    queueA.add(v);
                }
            }
            for (Vertex v : currentB.getNeighbours().keySet()) {
                if (!visitMap.get(v).getValue()) {
                    parentsB.put(v, currentB);
                    queueB.add(v);
                }
            }
        }

        while (parent != null) {
            path.addFirst(parent);
            parent = parentsA.get(parent);
        }
        parent = parentsB.get(path.getLast());
        while (parent != null) {
            path.addLast(parent);
            parent = parentsB.get(parent);
        }

        System.out.println("\nДвунаправленный поиск выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
    }

    public static void runBestFirstSearch(Vertex start, Vertex finish) {
        System.out.println("\nРабота поиска по первому наилучшему совпадению:");
        PriorityQueue<WeighedVertex> queue = new PriorityQueue<>();
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        parents.put(start, null);
        queue.add(new WeighedVertex(start, 0));

        while (!queue.isEmpty()) {
            queue.forEach(v -> System.out.print(v + " "));
            System.out.println();
            WeighedVertex current = queue.poll();
            current.getVertex().setWasVisited(true);
            if (current.getVertex() == finish) {
                break;
            }
            for (Vertex v : current.getVertex().getNeighbours().keySet()) {
                if (!v.getWasVisited()) {
                    parents.put(v, current.getVertex());
                    queue.add(new WeighedVertex(v, current.getVertex().getNeighbours().get(v)));
                }
            }
        }

        Vertex parent = finish;
        while (parent != null) {
            path.addFirst(parent);
            parent = parents.get(parent);
        }
        System.out.println("\nПоиск по первому наилучшему совпадению выполнен! Найден следующий путь из города " + start + " в город " + finish + ":");
        printPath();
        path.clear();
        GraphManager.resetVisits();
    }

    private static boolean depthLimitSearch(Vertex current, Vertex finish, int limit) {
        System.out.println(current);
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

    private static HashMap<Vertex, Pair<Boolean, Boolean>> createVisitMap() {
        HashMap<Vertex, Pair<Boolean, Boolean>> visitMap = new HashMap<>();
        for (Vertex v : GraphManager.getGraph().values()) {
            visitMap.put(v, new Pair<>(false, false));
        }
        return visitMap;
    }
}

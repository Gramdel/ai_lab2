package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class GraphManager {
    private static final HashMap<String, Vertex> graph = new HashMap<>();

    public static boolean createGraphFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String nameA = line.split(" ")[0];
                String nameB = line.split(" ")[1];
                int distance = new Integer(line.split(" ")[2]);

                Vertex a = new Vertex(nameA);
                Vertex b = new Vertex(nameB);
                if (!graph.isEmpty()) {
                    Vertex tmp = graph.get(nameA);
                    if (tmp != null) {
                        a = tmp;
                    }
                    tmp = graph.get(nameB);
                    if (tmp != null) {
                        b = tmp;
                    }
                }
                a.addNeighbour(b, distance);
                b.addNeighbour(a, distance);
                graph.put(nameA, a);
                graph.put(nameB, b);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Конфигурационный файл с именем \"" + filename + "\" не найден!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Некорректная структура конфигурационного файла!");
        }
        return false;
    }

    public static void resetVisits() {
        for (Vertex v : graph.values()) {
            v.setWasVisited(false);
        }
    }

    public static HashMap<String, Vertex> getGraph() {
        return graph;
    }
}

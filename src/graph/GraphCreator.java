package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

public class GraphCreator {
    public static HashSet<Vertex> createGraphFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));
            HashSet<Vertex> graph = new HashSet<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String nameA = line.split(" ")[0];
                String nameB = line.split(" ")[1];
                int distance = new Integer(line.split(" ")[2]);

                Vertex a = new Vertex(nameA);
                Vertex b = new Vertex(nameB);
                if (!graph.isEmpty()) {
                    Optional<Vertex> optional = graph.stream().filter(v -> v.getName().equals(nameA)).findFirst();
                    if (optional.isPresent()) {
                        a = optional.get();
                        graph.remove(a);
                    }
                    optional = graph.stream().filter(v -> v.getName().equals(nameB)).findFirst();
                    if (optional.isPresent()) {
                        b = optional.get();
                        graph.remove(b);
                    }
                }
                a.addNeighbour(b, distance);
                b.addNeighbour(a, distance);
                graph.add(a);
                graph.add(b);
            }
            return graph;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Конфигурационный файл с именем \"" + filename + "\" не найден!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Некорректная структура конфигурационного файла!");
        }
        return null;
    }
}

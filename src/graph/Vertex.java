package graph;

import java.util.HashMap;

public class Vertex {
    private String name;
    private final HashMap<Vertex, Integer> neighbours = new HashMap<>();

    public Vertex(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNeighbour(Vertex neighbour, int distance) {
        neighbours.put(neighbour, distance);
    }

    public HashMap<Vertex, Integer> getNeighbours() {
        return neighbours;
    }
}

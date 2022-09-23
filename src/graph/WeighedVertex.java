package graph;

public class WeighedVertex implements Comparable<WeighedVertex> {
    private final Vertex vertex;
    private final int weight;

    public WeighedVertex(Vertex vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }


    @Override
    public int compareTo(WeighedVertex v) {
        return weight - v.getWeight();
    }

    @Override
    public String toString() {
        return vertex + " (вес " + weight + ")";
    }
}

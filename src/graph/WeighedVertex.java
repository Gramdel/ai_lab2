package graph;

public class WeighedVertex implements Comparable<WeighedVertex> {
    private final Vertex vertex;
    private final int g;
    private final int h;
    private final int f;

    public WeighedVertex(Vertex vertex, int f) {
        this.vertex = vertex;
        this.g = 0;
        this.h = 0;
        this.f = f;
    }

    public WeighedVertex(Vertex vertex, int g, int h) {
        this.vertex = vertex;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
        return f;
    }

    @Override
    public int compareTo(WeighedVertex v) {
        return f - v.getF();
    }

    @Override
    public String toString() {
        return f + "(" + vertex + ")";
    }
}

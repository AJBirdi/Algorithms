public class Edge {
    private int id;
    private Vertex source;
    private Vertex destination;
    private int weight;

    public Edge (int id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getWeight () {
        return weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Source: " + source + ", destination: " + destination + ", weight: " + weight;
    }
}
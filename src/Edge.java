public class Edge {
    private int id;
    private Vertex beginning;
    private Vertex end;
    private int weight;

    public Edge (int id, Vertex beginning, Vertex end, int weight) {
        this.id = id;
        this.beginning = beginning;
        this.end = end;
        this.weight = weight;
    }

    public int getWeight () {
        return weight;
    }

    public Vertex getBeginning () {
        return beginning;
    }

    public Vertex getEnd () {
        return end;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Beginning: " + beginning + ", end: " + end + ", weight: " + weight;
    }
}
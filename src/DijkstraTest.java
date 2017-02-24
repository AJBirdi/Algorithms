import java.util.ArrayList;
import java.util.List;

/**
 * Created by aj on 2/23/17.
 */
public class DijkstraTest {
    private static DijkstraTest ourInstance = new DijkstraTest();

    public static DijkstraTest getInstance() {
        return ourInstance;
    }

    private DijkstraTest() {
    }

    public static void main (String[] args) {
		List<Vertex> vertexList = new ArrayList<Vertex>();

		vertexList.add(new Vertex(0));
		vertexList.add(new Vertex(1));
		vertexList.add(new Vertex(2));
		vertexList.add(new Vertex(3));
		vertexList.add(new Vertex(4));
		vertexList.add(new Vertex(5));
		vertexList.add(new Vertex(6));
		vertexList.add(new Vertex(7));
		vertexList.add(new Vertex(8));

		List<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(new Edge(0, vertexList.get(0), vertexList.get(1), 7));
		edgeList.add(new Edge(1, vertexList.get(0), vertexList.get(2), 9));
		edgeList.add(new Edge(2, vertexList.get(0), vertexList.get(8), 14));
		edgeList.add(new Edge(3, vertexList.get(1), vertexList.get(2), 10));
		edgeList.add(new Edge(4, vertexList.get(1), vertexList.get(3), 15));
		edgeList.add(new Edge(5, vertexList.get(2), vertexList.get(3), 11));
		edgeList.add(new Edge(6, vertexList.get(2), vertexList.get(4), 1));
		edgeList.add(new Edge(7, vertexList.get(2), vertexList.get(8), 2));
		edgeList.add(new Edge(8, vertexList.get(3), vertexList.get(5), 5));
		edgeList.add(new Edge(9, vertexList.get(4), vertexList.get(5), 4));
		edgeList.add(new Edge(10, vertexList.get(4), vertexList.get(7), 5));
		edgeList.add(new Edge(11, vertexList.get(5), vertexList.get(6), 10));
		edgeList.add(new Edge(12, vertexList.get(6), vertexList.get(7), 11));
		edgeList.add(new Edge(13, vertexList.get(7), vertexList.get(8), 4));

		Graph graph = new Graph(vertexList, edgeList);

		DijkstrasAlgorithm dijkstra = new DijkstrasAlgorithm(graph);

		dijkstra.specificDestination(vertexList.get(0), vertexList.get(6));
	}
}

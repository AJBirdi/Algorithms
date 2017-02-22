/**
 * Created by aj on 2/20/17.
 */
public class DijkstrasAlgorithm {

	public static void Main (String[] args) {
        Vertex vertex1 = new Vertex(1);
		Vertex vertex2 = new Vertex(2);
		Vertex vertex3 = new Vertex(3);
		Vertex vertex4 = new Vertex(4);
		Vertex vertex5 = new Vertex(5);
		Vertex vertex6 = new Vertex(6);
		Vertex vertex7 = new Vertex(7);
		Vertex vertex8 = new Vertex(8);

		//TODO Make all edges connect from the smaller number vertex to the higher number vertex
		Edge edge1 = new Edge(1, vertex1, vertex7, 5);
		Edge edge2 = new Edge(2, vertex1, vertex2, 3);
		Edge edge3 = new Edge(3, vertex1, vertex6, 3);
		Edge edge4 = new Edge(4, vertex7, vertex6, 1);
		Edge edge5 = new Edge(5, vertex7, vertex2, 3);
		Edge edge6 = new Edge(6, vertex2, vertex3, 6);
		Edge edge7 = new Edge(7, vertex7, vertex8, 3);
		Edge edge8 = new Edge(8, vertex6, vertex5, 1);
		Edge edge9 = new Edge(9, vertex8, vertex5, 4);
		Edge edge10 = new Edge(10, vertex8, vertex3, 2);
		Edge edge11 = new Edge(11, vertex8, vertex4, 8);
		Edge edge12 = new Edge(12, vertex5, vertex4, 10);
		Edge edge13 = new Edge(13, vertex3, vertex4, 4);
	}
}

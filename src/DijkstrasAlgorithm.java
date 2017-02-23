import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class DijkstrasAlgorithm {

	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	private List<Vertex> path;
	private Set<Vertex> unvisitedNodes;
	private Set<Vertex> visitedNodes;
	private Map<Vertex, Integer> distance;
	private Map<Vertex, Vertex> previous;

	public DijkstrasAlgorithm (Graph graph){
		vertexList = graph.getVertices();
		edgeList = graph.getEdges();
	}

	//Runs the algorithm on a specific start and specific end node
	public void specificDestination(Vertex source, Vertex destination) {
        distance = new HashMap<Vertex, Integer>();
		unvisitedNodes = new HashSet<Vertex>();
		visitedNodes = new HashSet<Vertex>();
		path = new LinkedList<Vertex>();
		previous = new HashMap<Vertex, Vertex>();

		distance.put(source, 0);
		previous.put(source, source);
		unvisitedNodes.add(source);

		while((source.getId() != destination.getId()) && (source.getId() != -1)){
			unvisitedNodes.add(source);
			Vertex closest = getClosestNeighbor(source);
		    visitedNodes.add(source);
		    unvisitedNodes.remove(source);
		    path.add(source);
		    previous.put(closest, source);
		    if(source.getId() != destination.getId() && (closest.getId() == -1)){
				source = previous.get(source);
				System.out.println("previous node is " + previous.get(source));
			}
			else{
				source = closest;
			}
		    System.out.println("Destination node is " + destination + " and source node is " + source);
		}
		path.add(destination);
		System.out.println(path);
	}

	//Returns a list of all the nodes that neighbor the source
	private List<Vertex> getNeighbors(Vertex source) {
		List<Vertex> neighbors= new ArrayList<Vertex>();

		for(int x = 0; x < edgeList.size(); x++) {
			//TODO Allow edgelist destiantion vertices to also be checked against the source
			if((edgeList.get(x).getSource() == source) && !visited(edgeList.get(x).getDestination())) {
				neighbors.add(edgeList.get(x).getDestination());
				distance.put(edgeList.get(x).getDestination(), edgeList.get(x).getWeight());
			}
		}

		for(int y = 0; y < neighbors.size(); y++){
			System.out.println("Unvisited neighbors are " + neighbors.get(y));
		}
		return neighbors;
	}

	//Returns the closest node to the source node
	private Vertex getClosestNeighbor(Vertex source) {
		List<Vertex> neighbors = getNeighbors(source);
		if(neighbors.size() > 0){
			Vertex closest = neighbors.get(0);
		    int closestDistance = distance.get(closest);

		    for(int x = 0; x < neighbors.size(); x++) {
		    	if(distance.get(neighbors.get(x)) < closestDistance){
		    		closest = neighbors.get(x);
		    		closestDistance = distance.get(neighbors.get(x));
		    	}
		    }
		    System.out.println("Closest node is " + closest + "\nDistance is " + closestDistance);
		    return closest;
		}
		else{
			return new Vertex(-1);
		}
	}

	//Returns whether or not the source node has been visited or not
	private boolean visited(Vertex source) {
		if(visitedNodes.contains(source)) {
			return true;
		}
		else {
			return false;
		}
	}
}

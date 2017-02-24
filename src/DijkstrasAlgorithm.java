import java.util.*;
import java.util.Map;

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
		previous = new LinkedHashMap<Vertex, Vertex>();

		distance.put(source, 0);
		previous.put(source, source);
		unvisitedNodes.add(source);

		while((source.getId() != destination.getId()) && (source.getId() != -1)){
			unvisitedNodes.add(source);
			System.out.println("Source node is " + source + " visitedNodes are " + visitedNodes);
			Vertex closest = getClosestNeighbor(source);
		    visitedNodes.add(source);
		    unvisitedNodes.remove(source);
		    path.add(source);
		    previous.put(source, closest);
		    if(source.getId() != destination.getId() && (closest.getId() == -1)){
				backtrack();
			}
			else{
				source = closest;
			}
		}
		path.add(destination);
		System.out.println(path);
	}

	//Returns a list of all the nodes that neighbor the source
	private List<Vertex> getNeighbors(Vertex source) {
		List<Vertex> neighbors= new ArrayList<Vertex>();

		for(int x = 0; x < edgeList.size(); x++) {
			if(edgeList.get(x).getSource() == source && !visited(edgeList.get(x).getDestination())) {
				neighbors.add(edgeList.get(x).getDestination());
				distance.put(edgeList.get(x).getDestination(), edgeList.get(x).getWeight());
			}
			else if(edgeList.get(x).getDestination() == source && !visited(edgeList.get(x).getSource())) {
                neighbors.add(edgeList.get(x).getSource());
				distance.put(edgeList.get(x).getSource(), edgeList.get(x).getWeight());
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

	private void backtrack() {

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

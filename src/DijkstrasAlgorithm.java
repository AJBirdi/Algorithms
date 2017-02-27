import java.util.*;
import java.util.Map;


public class DijkstrasAlgorithm {

	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	private List<Vertex> path;
	private List<Integer> weights;
	private Set<Vertex> unvisitedNodes;
	private Set<Vertex> visitedNodes;
	private Map<Vertex, Integer> distance;
	private Map<Vertex, Vertex> previous;

	private boolean isPossible;

	public DijkstrasAlgorithm (Graph graph){
		vertexList = graph.getVertices();
		edgeList = graph.getEdges();
	}

	//Runs the algorithm on a specific start and specific end node
	public void specificDestination(Vertex source, Vertex destination) {
		Vertex originalSource = source;
		isPossible = true;

        distance = new HashMap<Vertex, Integer>();
		unvisitedNodes = new HashSet<Vertex>();
		visitedNodes = new HashSet<Vertex>();
		path = new LinkedList<Vertex>();
		previous = new LinkedHashMap<Vertex, Vertex>();
		weights = new LinkedList<Integer>();

		distance.put(source, 0);
		unvisitedNodes.add(source);
		previous.put(source, source);

		while(isPossible) {
			while((source.getId() != destination.getId()) && (source.getId() != -1)) {
                unvisitedNodes.add(source);
                Vertex closest = getClosestNeighbor(source);
                visitedNodes.add(source);
                unvisitedNodes.remove(source);
                path.add(source);
                previous.put(closest, source);
                if(source.getId() != destination.getId() && (closest.getId() == -1)){
                    source = backtrack(source, closest);
                }
                else{
                    source = closest;
                }
            }
            isPossible = false;
		}
		Iterator<Integer> iterator = weights.iterator();
		int weight = 0;
		while(iterator.hasNext()){
			weight += iterator.next();
		}
		path.add(source);
		System.out.println("The source was " + originalSource +  ", the destination was " + destination + ", the weight is " + weight + ", and the path was " + path + ".\n");
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
			weights.add(closestDistance);
		    System.out.println("Closest node is " + closest + "\nDistance is " + closestDistance);
		    return closest;
		}
		else{
			return new Vertex(-1);
		}
	}

	//Moves 1 node back while marking the current node as visited if the current node has no vertices it can visit
	//  but the target node has not been reached
	private Vertex backtrack(Vertex source, Vertex closest) {
		previous.remove(closest);

        if(previous.size() >= 1) {
		    Vertex tempSource = previous.get(source);
            visitedNodes.remove(previous.get(source));
		    previous.remove(source);
		    System.out.print("I backtracked from " + source);
		    source = tempSource;
		    weights.remove(weights.size() - 1);
		    //Double removal because when specificDestination continues, it will add the vertex back the to the path
		    path.remove(path.size() - 1);
		    path.remove(path.size() - 1);
		}
		else{
		    System.out.println("No possible solution.");
		    isPossible = false;
		}
		System.out.println(" back to " + source + ".");
		return source;
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

import java.util.*;
import java.util.Map;

public class DijkstrasAlgorithm {

	private List<Vertex> vertexList;
	private List<Vertex> path;
	private List<Edge> edgeList;
	private List<Integer> weights;
	private Map<Vertex, Integer> distance;
	private Map<Vertex, Vertex> previous;
	private Set<Vertex> unvisitedNodes;
	private Set<Vertex> visitedNodes;

	private boolean isPossible;
	private boolean isCompleted;

	public DijkstrasAlgorithm (Graph graph) {
		vertexList = graph.getVertices();
		edgeList = graph.getEdges();
	}

	//Runs the algorithm on a specific start and specific end node
	public void specificDestination(Vertex source, Vertex destination) {
		System.out.println("The source node is " + source + " while the destination is " + destination + ".");
		Vertex originalSource = source;
		isPossible = true;
		isCompleted = false;

		distance = new HashMap<Vertex, Integer>();
		visitedNodes = new HashSet<Vertex>();
		unvisitedNodes = new HashSet<Vertex>();
		previous = new LinkedHashMap<Vertex, Vertex>();
		path = new LinkedList<Vertex>();
		weights = new LinkedList<Integer>();

		distance.put(source, 0);
		unvisitedNodes.add(source);
		//Previous always contains a pair of the original source nodes to guarantee there is always 1 entry in previous
		previous.put(source, source);

		while((source.getId() != destination.getId()) && (source.getId() != -1) && isPossible) {
			while(isPossible && !isCompleted) {
				unvisitedNodes.add(source);
				Vertex closest = getClosestNeighbor(source);
				visitedNodes.add(source);
				unvisitedNodes.remove(source);
				path.add(source);
				previous.put(closest, source);
				//Check if there are no more neighbors, but the destination hasn't been reached yet
				if(source.getId() != destination.getId() && (closest.getId() == -1)) {
					source = backtrack(source, closest);
				}
				else {
					source = closest;
				}
				if(source.getId() == destination.getId()) {
					isCompleted = true;
				}
			}
		}
		Iterator<Integer> iterator = weights.iterator();
		int weight = 0;

		//Adds up value of weight
		while(iterator.hasNext()) {
			weight += iterator.next();
		}
		//Only print out completion information if the path was completed
		if(isPossible) {
			path.add(source);
	System.out.println("The source was " + originalSource +  ", the destination was " + destination
				+ ", the weight is " + weight + ", and the path was " + path + ".\n");
		}
	}

	//Returns a list of all the nodes that neighbor the source
	private List<Vertex> getNeighbors(Vertex source) {
		List<Vertex> neighbors= new ArrayList<Vertex>();

		//2 ifs to check if the source is equal to an edge's destination as well to check if the source is equal to an edge's source
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
		for(int y = 0; y < neighbors.size(); y++) {
			System.out.println("Unvisited neighbors are " + neighbors.get(y) + ".");
		}
		return neighbors;
	}

	//Returns the closest node to the source node
	private Vertex getClosestNeighbor(Vertex source) {
		List<Vertex> neighbors = getNeighbors(source);
		//Has to be at least 1 neighbor to get the closest one
		if(neighbors.size() > 0) {
			Vertex closest = neighbors.get(0);
			int closestDistance = distance.get(closest);

			for(int x = 0; x < neighbors.size(); x++) {
				//If the current neighbor has a smaller weight, make that neighbor the current closest
				if(distance.get(neighbors.get(x)) < closestDistance) {
					closest = neighbors.get(x);
					closestDistance = distance.get(neighbors.get(x));
				}
			}
			weights.add(closestDistance);
			System.out.println("Closest node is " + closest + ".\nDistance is " + closestDistance + ".");
			return closest;
		}
		//Returns a vertex with id of -1 that shows that there aren't anymore neighbors
		else {
			return new Vertex(-1);
		}
	}

	//Moves 1 node back while marking the current node as visited if the current node has no vertices it can visit
	//  but the target node has not been reached
	private Vertex backtrack(Vertex source, Vertex closest) {
		previous.remove(closest);

		//Previous will always contain a link to the original source node, so the size will always be >1 if the path has moved at all
		if(previous.size() > 1) {
			Vertex tempSource = previous.get(source);
			visitedNodes.remove(previous.get(source));
			previous.remove(source);
			System.out.print("I backtracked from " + source);
			source = tempSource;
			weights.remove(weights.size() - 1);
			//Double removal because when specificDestination continues, it will add the vertex back the to the path
			path.remove(path.size() - 1);
			path.remove(path.size() - 1);
			System.out.println(" back to " + source + ".");
		}
		//If the size is less than one, or even one, it has backtracked past the original node, which means there is no solution
		else {
			System.out.println("No possible solution.\n");
			isPossible = false;
		}
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

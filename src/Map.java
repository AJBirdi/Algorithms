//First attempt at creating Dijkstra's Algorithm
//Way too complicated and too buggy, switched to much simpler Edge, Vertex, and Graph classes

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {

	private int nodeCount;
	private SecureRandom random = new SecureRandom();
	private ArrayList<int[]> weightedMap = new ArrayList<int[]>();
	
	public Map(int nodes){
		nodeCount = nodes;
	}

		public void createWeightedMap() {
			
			boolean next;
			boolean add;
			int weight;
			int firstNode;
			int tempJ;
			int tempI;
			int arrayListIndex = 0;
			int arrayListIterator = 0;
			
			int[] edges = new int[nodeCount];
		
			for(int i = 0; i < nodeCount; i++){
				
				//Guarantees every node is connected to at least one other node
				weight = random.nextInt(15)+1;
				firstNode = (random.nextInt(nodeCount));
				
				//Don't let any node connect to itself
				while(firstNode == i){
					firstNode = (random.nextInt(nodeCount));
				}
				
				weightedMap.add(new int[]{i, firstNode, weight});
				edges[i]++;
				
				for(int j = 0; j < nodeCount; j++){
					
					//Generates random boolean which determines whether two nodes will be connected or not
					next = random.nextBoolean();
					
					//Prevents nodes from linking to themselves or double linking to firstNode
					if(i == j || firstNode == j){
						next = false;
					}
					
					if(next){
						tempI = i;
						tempJ = j;
						
						for(int x = 0; x < j; x++){
							arrayListIndex += edges[x];
						}
						
						int[] array = weightedMap.get(arrayListIndex);

						while(arrayListIterator <= edges[tempJ]){
							if(weightedMap.get(arrayListIndex)[1] == tempI){
								add = false;
							}
							else{
								add = true;
							}
							
							if(add){
								//If 2 nodes are connected, generate a weight between 1 and 15 then add the nodes and their weight to the map
								weight = (random.nextInt(15)+1);
								weightedMap.add(new int[]{i, j, weight});
							}
							
							arrayListIterator++;
						}
						
						//Keeps track of how many edges each node has
						edges[i]++;
					}
				}
			}			
			System.out.println(weightedMap.size());
			for(int i = 0; i < weightedMap.size(); i++){
				System.out.println(Arrays.toString(weightedMap.get(i)));
			}
		}
}

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

		public void createMap() {
			
			boolean next;
			int weight;
			int firstNode;
		
			for(int i = 0; i < nodeCount; i++){
				//Guarantees every node is connected to at least one other node
				weight = random.nextInt(15)+1;
				firstNode = (random.nextInt(nodeCount));
				
				//Don't let any node connect to itself
				while(firstNode == i){
					firstNode = (random.nextInt(nodeCount));
				}
				
				weightedMap.add(new int[]{i, firstNode, weight});
				
				for(int j = 0; j < nodeCount; j++){
					//Generates random boolean which determines whether two nodes will be connected or not
					next = random.nextBoolean();
					
					//Prevents nodes from linking to themselves or double linking to firstNode
					if(i == j || firstNode == j){
						next = false;
					}
					
					//If 2 nodes are connected, generate a weight between 1 and 15 then add the 2 nodes and their weight to the map
					if(next){
						weight = (random.nextInt(15)+1);
						weightedMap.add(new int[]{i, j, weight});
					}
				}
			}
			System.out.println(weightedMap.size());
			for(int i = 0; i < weightedMap.size(); i++){
				System.out.println(Arrays.toString(weightedMap.get(i)));
			}
		}
}

import java.security.SecureRandom;
import java.util.Scanner;

public class Dijkstra_Algorithm {
	
	private int num_nodes;
	
	public static void main (String [] args){
		
		Scanner keyboard = new Scanner(System.in);
		
		int num_nodes;
		
		System.out.print("Enter number of nodes :: ");
		num_nodes = keyboard.nextInt();
		
		int[][] node_links = new int[num_nodes][];
		
	}
	
	//TODO Create map with unweighted links from node to node
	public int[][] createMap(int num_nodes) {
		
		SecureRandom random = new SecureRandom();
		
		for(int i = 0; i < num_nodes; i++){
			System.out.println("\n\n " + i);
			for(int j = 0; j < num_nodes; j++){
				System.out.println(random.nextBoolean());
			}
		}
		
		int[][] myArray = new int[5][5];
		return myArray;
	}
}

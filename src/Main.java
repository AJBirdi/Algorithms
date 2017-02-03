import java.util.Scanner;
import java.util.ArrayList;

public class Main {
		
	public static void main (String [] args){
		
		Scanner keyboard = new Scanner(System.in);
		
		int num_nodes;
		
		System.out.print("Enter number of nodes :: ");
		num_nodes = keyboard.nextInt();
		
		Map map = new Map(num_nodes);
		map.createMap();
	}
}

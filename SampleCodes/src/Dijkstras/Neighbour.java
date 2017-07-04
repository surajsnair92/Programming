package Dijkstras;

public class Neighbour {
	int index;
	int weight;
	Neighbour next;
	
	public Neighbour(int index, int weight, Neighbour next) {
		this.index = index;
		this.weight = weight;
		this.next = next;
	}
	
	
}

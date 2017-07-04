package Dijkstras;

public class Test {
	public static void main(String[] args) {
		Graph graph = new Graph(9);
		for (int i = 0; i < 9; i++) 
			graph.insertVertex(i);
		graph.insertEdge(0, 1, 4);
		graph.insertEdge(0, 7, 8);
		graph.insertEdge(1, 0, 4);
		graph.insertEdge(1, 7, 11);
		graph.insertEdge(1, 2, 8);
		graph.insertEdge(2, 1, 8);
		graph.insertEdge(2, 3, 7);
		graph.insertEdge(2, 8, 2);
		graph.insertEdge(2, 5, 4);
		graph.insertEdge(3, 2, 7);
		graph.insertEdge(3, 4, 9);
		graph.insertEdge(3, 5, 14);
		graph.insertEdge(4, 3, 9);
		graph.insertEdge(4, 5, 10);
		graph.insertEdge(5, 2, 4);
		graph.insertEdge(5, 3, 14);
		graph.insertEdge(5, 4, 10);
		graph.insertEdge(5, 6, 2);
		graph.insertEdge(6, 5, 2);
		graph.insertEdge(6, 7, 1);
		graph.insertEdge(6, 8, 6);
		graph.insertEdge(7, 0, 8);
		graph.insertEdge(7, 1, 11);
		graph.insertEdge(7, 6, 1);
		graph.insertEdge(7, 8, 7);
		graph.insertEdge(8, 2, 2);
		graph.insertEdge(8, 6, 6);
		graph.insertEdge(8, 7, 7);
		graph.shortestPath(0);

	}
}

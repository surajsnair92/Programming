package Prim;

import java.util.HashMap;

public class Vertex {


	public String name;
	public HashMap<Vertex, Integer> adjacentEdges;
	public int distance;
	public Vertex previous;

	public Vertex(String vertex)
	{
		this.name = vertex;
		this.adjacentEdges = new HashMap<>();
		this.distance = 0;
		this.previous = null;
	}

	public void addAdjacent(Vertex vertex, int distance)
	{
		if(!this.adjacentEdges.containsKey(vertex))
		{
			this.adjacentEdges.put(vertex, distance);
		}
	}

	public int getDistance(){
		return this.distance;
	}
}

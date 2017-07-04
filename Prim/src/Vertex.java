import java.util.*;

class Vertex{
	public int vertexIndex;
	//public HashMap<Vertex, Integer> adjacentEdges;
	public List<Edge> listOfEdges = new ArrayList<>();
	public List<Vertex> listOfVertices = new ArrayList<>();
	public int distance;
	public Vertex previous;
	
	public Vertex(int vertex)
	{
		this.vertexIndex = vertex;
		//this.adjacentEdges = new HashMap<>();
		this.distance = 0;
		this.previous = null;
	}
	
	public List<Edge> getListOfEdges() {
		return listOfEdges;
	}

	public List<Vertex> getListOfVertices() {
		return listOfVertices;
	}

	
	
	
	
//	public void addAdjacent(Vertex vertex, int distance)
//	{
//		if(!this.adjacentEdges.containsKey(vertex))
//		{
//			this.adjacentEdges.put(vertex, distance);
//		}
//	}
	
	public int getDistance(){
		return this.distance;
	}
	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		else
			return false;
	}
	
	public void addAdj(Vertex v, Edge e){
		listOfEdges.add(e);
		listOfVertices.add(v);
	}
	
	public String toString(){
		return String.valueOf(vertexIndex);
		
	}
	
//	public void displayAllEdges(){
//	String str = null;	
//	for(Map.Entry<Vertex, Integer> entry : this.adjacentEdges.entrySet()){
//		// str = this.vertexIndex.toString() + " has adjacent vertex: " + entry.getKey().vertexIndex + " with edge weight " + entry.getValue()+"\n";
//		 System.out.println(this.vertexIndex + " , " + entry.getKey().vertexIndex + " , " + entry.getValue());
//	}
//	
//	
//	}
	
}
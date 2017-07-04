import java.util.*;
import java.util.List;
import java.util.Map;

class Graph
{
	public Map<Integer, Vertex> VertexList;
	public List<Edge> edgeList;
	public int VertexListSize;
	
	public Graph()
	{
		this.VertexList = new HashMap<>();
		this.edgeList = new ArrayList<Edge>();
		this.VertexListSize = 0;
	}
	
//	public void addVertex(Vertex vertex) {
//		if(vertex instanceof Vertex && !this.VertexList.containsKey(vertex.vertexIndex)){
//			this.VertexList.put(vertex.vertexIndex, vertex);
//			this.VertexListSize += 1;
//		}
//		else{
//			System.out.println("This is not a Vertex");
//		}
//	}
	
	public void addEdge(int v1, int v2, int weight) {
//		if(this.VertexList.containsKey(v1.vertexIndex) && this.VertexList.containsKey(v2.vertexIndex)){
//			v1.addAdjacent(v2, weight);
//		}
//		else{
//			System.out.println("Enter valid Vertex or weight");
//		}
		Vertex firstV = null;
		if(VertexList.containsKey(v1))
			firstV = VertexList.get(v1);
		else{
			firstV = new Vertex(v1);
			VertexList.put(v1, firstV);
		}
		
		Vertex secondV  = null;
		if(VertexList.containsKey(v2))
			secondV = VertexList.get(v2);
		else{
			secondV = new Vertex(v2);
			VertexList.put(v2, secondV);
		}
		
		Edge e = new Edge(firstV, secondV, weight);
		edgeList.add(e);
		firstV.addAdj(secondV, e);
		secondV.addAdj(firstV, e);
	}
	
	public Map<Integer, Vertex> getVertexList() {
		return VertexList;
	}
	
//	public int getWeight(Vertex v1, Vertex v2){
//		int someNumber = 0;
//		if(v1 instanceof Vertex){
//			for(Vertex w : v1.adjacentEdges.keySet()){
//				if(w == v2)
//					someNumber = v1.adjacentEdges.get(v2);
//			}
//		}
//		return someNumber;
//	}
	
	public static void main(String[] args){
//		Graph g = new Graph();
//		Vertex a = new Vertex("A");
//		Vertex b = new Vertex("B");
//		Vertex c = new Vertex("C");
//		Vertex d = new Vertex("D");
//		Vertex e = new Vertex("E");
//		Vertex f = new Vertex("F");
//		g.addVertex(a);
//		g.addVertex(b);
//		g.addVertex(c);
//		g.addVertex(d);
//		g.addVertex(e);
//		g.addVertex(f);
//		
//		g.addEdge(a, d, 1);
//		g.addEdge(a, b, 3);
//		g.addEdge(d, e, 6);
//		
//		g.addEdge(d, b, 3);
//		g.addEdge(d, c, 1);
//		g.addEdge(b, c, 1);
//		g.addEdge(c, e, 5);
//		
//		g.addEdge(c, f, 4);
//		g.addEdge(e, f, 2);
		
//		for(Map.Entry<Vertex, Integer> entry : a.adjacentEdges.entrySet()){
//			System.out.println(a.vertexIndex + " , "+ entry.getKey().vertexIndex+ ": " +entry.getValue() );
//			
//		}
		
		
	}
}

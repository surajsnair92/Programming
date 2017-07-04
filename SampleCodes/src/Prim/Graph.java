package Prim;

import java.util.HashMap;


class Graph{
	//public HashMap<String, Vertex> VertexList;
	public int size;
	Vertex[] vertices;

	public Graph(int size){
		this.size = size;
		this.vertices = new Vertex[size];
	}

	public void addVertex(String name) {
        vertices[size++] = new Vertex(name);
    }

	public void addEdge(Vertex v1, Vertex v2, int weight) {
//		if(this.VertexList.containsKey(v1.name) && this.VertexList.containsKey(v2.name)){
//			v1.addAdjacent(v2, weight);
//		}
		if(this.contains(v1) && this.contains(v2)){
			
		}
		else{
			System.out.println("Enter valid Vertex or weight");
		}
	}
	public boolean contains(Vertex v){
		boolean hasVertex = false;
		for (Vertex vertex : vertices) {
			if(vertex.name.equals(v.name)){
				hasVertex = true;
				break;
			}
		}
		return hasVertex;
	}
}
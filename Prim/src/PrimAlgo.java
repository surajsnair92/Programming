import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrimAlgo {

	public List<Edge> mst(Graph g){
		MinHeap minHeap = new MinHeap();
		Map<Vertex, Edge> connection = new HashMap<>();
		List<Edge> solution = new ArrayList<>();

		for (Map.Entry<Integer, Vertex> entry : g.getVertexList().entrySet()) {
			//entry.getValue().distance = Integer.MAX_VALUE;
			minHeap.insertVertex(entry.getValue(), Integer.MAX_VALUE);

		}
		//Vertex v = g.VertexList.entrySet().iterator().next().getValue();
		Vertex v = g.VertexList.get(0);
		//minHeap.decreaseCost(v, 0);
		
		while(!minHeap.empty()){
			Vertex currentVertex = minHeap.popMinimum();
			Edge span = connection.get(currentVertex);


			if(span != null)
				solution.add(span);

			for (Edge edge : currentVertex.getListOfEdges()) {
				Vertex adj = getVertexFromEdge(currentVertex, edge);
				if(minHeap.containsVertex(adj) && minHeap.getCost(adj) > edge.getCost()){
					minHeap.decreaseCost(adj, edge.getCost());
					connection.put(adj, edge);
				}
			}
		}
		return solution;
	}

	private Vertex getVertexFromEdge(Vertex v, Edge e){
		if(e.getV1().equals(v))
			return e.getV2();

		else
			return e.getV1();
	}

	public static void main(String[] args) throws IOException{
		Graph g = new Graph();


//		g.addEdge(65, 68, 1);// ad
//		g.addEdge(65, 66, 3);// ab
//		g.addEdge(68, 69, 6);// de
//
//		g.addEdge(68, 66, 3);// db
//		g.addEdge(68, 67, 1);// dc
//		g.addEdge(66, 67, 1);// bc
//		g.addEdge(67, 69, 5);// ce
//
//		g.addEdge(67, 70, 4);// cf
//		g.addEdge(69, 70, 2);// ef
		BufferedReader in = new BufferedReader(new FileReader("/Users/surajsatheeshnair/Documents/workspace/Prim/src/Untitled.txt"));
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
//        g.addEdge(1, 2, 3);
//        g.addEdge(2, 3, 1);
//        g.addEdge(3, 1, 1);
//        g.addEdge(1, 4, 1);
//        
//        g.addEdge(2, 4, 3);
//        g.addEdge(4, 5, 6);
//        g.addEdge(5, 6, 2);
//       
//        g.addEdge(3, 5, 5);
//        g.addEdge(3, 6, 4);

	
		
		String arr = list.get(0);
		int n = Integer.parseInt(arr);
		for(int i = 1;i<n;i++){
			arr = list.get(i);
			String[] split = arr.split("\\s+");
			int splitLen = split.length;
			int j = 0;
			g.addEdge(Integer.parseInt(split[j]), Integer.parseInt(split[j+1]), Integer.parseInt(split[j+2]));
				
		}
		PrimAlgo p = new PrimAlgo();
		List<Edge> finalList = p.mst(g);
		for (Edge edge : finalList) {
			System.out.println(edge);
		}

	}

}

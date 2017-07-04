import java.util.*;
import java.util.HashMap;


public class MinHeap {
	public class Node{
		public int cost;
		public Vertex vertex;

	}
	public List<Node> vertices;
	public HashMap<Vertex, Integer> position = new HashMap<>();
	
	MinHeap(){
		vertices = new ArrayList<Node>();
	}
	
	public void insertVertex(Vertex vertex, int cost){
		Node n = new Node();
		//this.vertices.add(n);
		n.cost = cost;
		n.vertex = vertex;
		vertices.add(n);
		int size = vertices.size();
		int currentIndex = size - 1;
		position.put(n.vertex, currentIndex);
		bubbleUp(currentIndex);
	}


	private void bubbleUp(int currentIndex){
		int parentIndex = (currentIndex - 1) / 2;
		while(parentIndex >= 0){
			Node p = vertices.get(parentIndex);
			Node c = vertices.get(currentIndex);
			if(p.cost > c.cost){
				swapPositions(p,c);
				updatePos(p.vertex,c.vertex,parentIndex, currentIndex);
				currentIndex = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			}
			else
				break;
		}
	}

	public void decreaseCost(Vertex v, int c){
		int pos = position.get(v);
		vertices.get(pos).cost = c;
		int parentIndex = (pos - 1) / 2;
		while(parentIndex >= 0){
			if(vertices.get(parentIndex).cost > vertices.get(pos).cost){
				swapPositions(vertices.get(parentIndex),vertices.get(pos));
				updatePos(vertices.get(parentIndex).vertex,vertices.get(pos).vertex,parentIndex, pos);
				pos = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			}
			else
				break;
		}
	}
	private void updatePos(Vertex parent, Vertex current, int parentIndex, int currentIndex) {
		position.remove(parent);
		position.remove(current);
		position.put(parent, parentIndex);
		position.put(current, currentIndex);
	}

	private void swapPositions(Node p, Node c){
		int tempCost = p.cost;
		Vertex v = p.vertex;

		p.cost = c.cost;
		p.vertex = c.vertex;

		c.cost = tempCost;
		c.vertex = v;
	}



//	public int findMinChild(int pos){
//		if(this.size < pos*2+1)
//			return pos*2;
//		else if(this.vertices.get(pos*2)<this.vertices.get(pos*2+1))
//			return pos*2;
//		else
//			return pos*2+1;
//
//	}

	//	public void bubbleDown(int pos){
	//		while(pos*2 <= this.size){
	//			int minChildIndex = findMinChild(pos);
	//			if(this.vertices.get(pos) > this.vertices.get(minChildIndex)){
	//				int temp = this.vertices.get(pos);
	//				this.vertices.set(pos, this.vertices.get(minChildIndex));
	//				this.vertices.set(minChildIndex, temp);
	//			}
	//			pos = minChildIndex;
	//		}
	//	}

	//	public Vertex popMinimum(){
	//		int popVal = this.vertices.get(1);
	//		this.vertices.set(1, this.vertices.get(this.size));
	//		this.vertices.remove(this.size);
	//		this.size -= 1;
	//		bubbleDown(1);
	//		return popVal;
	//	}

	public Vertex popMinimum(){
		Node n = new Node();
		n.vertex = vertices.get(0).vertex;
		n.cost = vertices.get(0).cost;
		int size = vertices.size();
		int smallestIndex;
		vertices.get(0).cost = vertices.get(size -1).cost;
		vertices.get(0).vertex = vertices.get(size -1).vertex;
		position.remove(n.vertex);
		position.remove(vertices.get(0));
		position.put(vertices.get(0).vertex, 0);
		vertices.remove(size - 1);
		int currentIndex = 0;
		size -= 2;

		while(true){
			int leftChild = 2*currentIndex + 1;
			int rightChild = 2*currentIndex + 2;
			if(size < leftChild)
				break;

			if(size < rightChild)
				rightChild = leftChild;

			
			if(vertices.get(leftChild).cost <= vertices.get(rightChild).cost)
				smallestIndex = leftChild;

			else
				smallestIndex = rightChild;

			if(vertices.get(currentIndex).cost > vertices.get(smallestIndex).cost){
				swapPositions(vertices.get(currentIndex), vertices.get(smallestIndex));
				updatePos(vertices.get(currentIndex).vertex, vertices.get(smallestIndex).vertex, currentIndex, smallestIndex);
				currentIndex = smallestIndex;

			}
			else
				break;

		}

		return n.vertex;		
	}

//
//	public void heapify(ArrayList<Integer> arr){
//		int heapify_start_pos = arr.size()/2;
//		this.size = arr.size();
//		this.vertices.addAll(arr);
//		while(heapify_start_pos > 0){
//			bubbleDown(heapify_start_pos);
//			heapify_start_pos -= 1;
//		}
//	}

	public boolean empty(){
		return vertices.size() == 0;
	}
	
	public boolean containsVertex(Vertex v){
		return position.containsKey(v);
	}
	
	public Integer getCost(Vertex v){
		Integer pos = position.get(v);
		if(pos != null)
			return vertices.get(pos).cost;
		return 0;
			
	}

	//	public static void main(String[] args){
	//		Heap a = new Heap();
	//		ArrayList<Integer> b = new ArrayList<>();
	//		b.add(92);
	//		b.add(45);
	//		b.add(10);
	//		b.add(-2);
	//		b.add(-60);
	//		b.add(79);
	//		b.add(14);
	//		a.heapify(b);
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//		System.out.println(a.popMinimum());
	//	}

}

package Dijkstras;

import java.util.Arrays;

public class Graph {
	public enum State{
		NEW,IN_Q,VISITED;
	}
	Vertex[] vertices;
	//int size;
	int capacity;
	
	public Graph(int capacity){
		this.capacity = capacity;
		vertices = new Vertex[capacity];
	}
	
	public void insertVertex(int name){
		capacity += 1;
		vertices = Arrays.copyOf(vertices, capacity);
		vertices[capacity-1] = new Vertex(name);
	}
	
	public void insertEdge(int source, int destination, int weight){
		vertices[source].connection = new Neighbour(destination, weight, vertices[source].connection);
	}
	public void shortestPath(int source){
		dijkstrasAlgorithm(vertices[source]);
	}
	public class Vertex {
		int name;
		int cost;
		Neighbour connection;
		State state;
		public Vertex(int name){
			this.name = name;
			cost = Integer.MAX_VALUE;
		}
	}
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
	public void dijkstrasAlgorithm(Vertex source){
		MinHeap heap = new MinHeap(capacity);
        heap.insert(source);
        source.state = State.IN_Q;
        source.cost = 0;
        while (!heap.isEmpty()) {
            Vertex u = heap.delete();
            u.state = State.VISITED;
            Neighbour temp = u.connection;
            while (temp != null) {
                if (vertices[temp.index].state == State.NEW) {
                    heap.insert(vertices[temp.index]);
                    vertices[temp.index].state = State.IN_Q;
                }
                if (vertices[temp.index].cost > u.cost + temp.weight) {
                    vertices[temp.index].cost = u.cost + temp.weight;
                    heap.structChangeAfterInsert();
                }
                temp = temp.next;
            }
        }
	}
	public static class MinHeap {
		int currentSize;
		Vertex arr[];
		MinHeap(int currentSize){
			this.currentSize = currentSize;
			arr = new Vertex[currentSize];
		}
		public boolean isEmpty(){
			return currentSize == 0;
		}
		public void insert(Vertex ele){
			//arr = new int[currentSize];
			if(currentSize == 0)
				arr[0] = ele;
			else{
				currentSize += 1;
				arr = Arrays.copyOf(arr, currentSize);
				arr[currentSize-1] = ele;
			}
			structChangeAfterInsert();
		}
		public void structChangeAfterInsert(){
			int currentIndex = currentSize -1;
			int parentIndex = (currentIndex - 1)/2;
			while(arr[currentIndex].cost < arr[parentIndex].cost){
				if(arr[currentIndex].cost < arr[parentIndex].cost)
					swapPositions(currentIndex, parentIndex);
				currentIndex = parentIndex;
				parentIndex = (currentIndex - 1)/2;
			}
		}
		public Vertex delete(){
			arr[0] = arr[currentSize-1];
			currentSize -=1;
			arr = Arrays.copyOf(arr, currentSize);
			structChangeAfterDelete(0);
			return arr[0];
		}
		public void structChangeAfterDelete(int currentIndex){
			int leftChildIndex = 1; 
			int rightChildIndex;
			while(((2*currentIndex) + 1) < currentSize){
				leftChildIndex = (2*currentIndex) + 1;
				rightChildIndex = (2*currentIndex) + 2;
				int toBeCompared = compareChildren(leftChildIndex,rightChildIndex);
				if(arr[currentIndex].cost > arr[toBeCompared].cost)
					swapPositions(currentIndex,toBeCompared);
				currentIndex = toBeCompared;
			}
		}
		public int compareChildren(int left, int right){
			if(arr[left].cost < arr[right].cost)
				return left;
			else
				return right;
		}
		public void swapPositions(int currIndex, int parIndex){
			Vertex temp = arr[currIndex];
			arr[currIndex] = arr[parIndex];
			arr[parIndex] = temp;
		}
		public void print(){
			int len = arr.length;
			for(int i = 0; i<len; i++){
				//System.out.println(arr[i]);
			}
		}
	}
}

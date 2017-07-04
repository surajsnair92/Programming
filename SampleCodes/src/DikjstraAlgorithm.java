public class DikjstraAlgorithm {
    public static void main(String[] args) {

        Graph graph = new Graph(9);
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 0, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 2, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 2, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 0, 8);
        graph.addEdge(7, 1, 11);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 2, 2);
        graph.addEdge(8, 6, 6);
        graph.addEdge(8, 7, 7);
        graph.findShortestPaths(0);
    }

    public static class Graph {
        Vertex[] vertices;
        int maxSize;
        int size;

        public Graph(int maxSize) {
            this.maxSize = maxSize;
            vertices = new Vertex[maxSize];
        }

        public void addVertex(int name) {
            vertices[size++] = new Vertex(name);
        }

        public void addEdge(int sourceName, int destinationName, int weight) {
            int srcIndex = sourceName;
            int destiIndex = destinationName;
            vertices[srcIndex].adj = new Neighbour(destiIndex, weight, vertices[srcIndex].adj);
            vertices[destiIndex].indegree++;
        }
        
        public void findShortestPaths(int sourceName){
            applyDikjstraAlgorith(vertices[sourceName]);
            for(int i = 0; i < maxSize; i++){
                System.out.println("Distance of "+vertices[i].name+" from Source: "+ vertices[i].cost);
            }
        }
        
        public class Vertex {
            int cost;
            int name;
            Neighbour adj;
            int indegree;
            State state;

            public Vertex(int name) {
                this.name = name;
                cost = Integer.MAX_VALUE;
                state = State.NEW;
            }

//            public int compareTo(Vertex v) {
//                if (this.cost == v.cost) {
//                    return 0;
//                }
//                if (this.cost < v.cost) {
//                    return -1;
//                }
//                return 1;
//            }
        }

        public enum State {
            NEW, CURRENT, VISITED
        }

        public class Neighbour {
            int index;
            Neighbour next;
            int weight;

            Neighbour(int index, int weight, Neighbour next) {
                this.index = index;
                this.next = next;
                this.weight = weight;
            }
        }

        public void applyDikjstraAlgorith(Vertex source) {
            Heap heap = new Heap(maxSize);
            heap.add(source);
            source.state = State.CURRENT;
            source.cost = 0;
            while (!heap.isEmpty()) {
                Vertex u = heap.remove();
                u.state = State.VISITED;
                Neighbour temp = u.adj;
                while (temp != null) {
                    if (vertices[temp.index].state == State.NEW) {
                        heap.add(vertices[temp.index]);
                        vertices[temp.index].state = State.CURRENT;
                    }
                    if (vertices[temp.index].cost > u.cost + temp.weight) {
                        vertices[temp.index].cost = u.cost + temp.weight;
                        heap.heapifyUP(vertices[temp.index]);
                    }
                    temp = temp.next;
                }
            }
        }

        public static class Heap {
            Vertex[] arr;
            int capacity;
            int currSize;

            public Heap(int maxSize) {
                this.capacity = maxSize;
                arr = new Vertex[maxSize];
            }

            public void add(Vertex u) {
                arr[currSize++] = u;
                heapifyUP(currSize - 1);
            }

            public void heapifyUP(Vertex u) {
                for (int i = 0; i < capacity; i++) {
                    if (u == arr[i]) {
                        heapifyUP(i);
                        break;
                    }
                }
            }

            public void heapifyUP(int position) {
                int currentIndex = position;
                Vertex currentItem = arr[currentIndex];
                int parentIndex = (currentIndex - 1) / 2;
                Vertex parentItem = arr[parentIndex];
                while (currentItem.cost < parentItem.cost) {
                    swapPositions(currentIndex, parentIndex);
                    currentIndex = parentIndex;
                    if (currentIndex == 0) {
                        break;
                    }
                    currentItem = arr[currentIndex];
                    parentIndex = (currentIndex - 1) / 2;
                    parentItem = arr[parentIndex];
                }
            }

            public Vertex remove() {
                Vertex v = arr[0];
                swapPositions(0, currSize - 1);
                arr[currSize - 1] = null;
                currSize--;
                heapifyDown(0);
                return v;
            }

            public void heapifyDown(int pos) {
                if (currSize == 1) {
                    return;
                }
                int currentIndex = pos;
                Vertex currentItem = arr[currentIndex];
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;
                int childIndex;
                if (arr[leftChildIndex] == null) {
                    return;
                }
                if (arr[rightChildIndex] == null) {
                    childIndex = leftChildIndex;
                } else if (arr[rightChildIndex].cost > arr[leftChildIndex].cost) {
                    childIndex = rightChildIndex;
                } else {
                    childIndex = leftChildIndex;
                }
                Vertex childItem = arr[childIndex];
                while (currentItem.cost > childItem.cost) {
                    swapPositions(currentIndex, childIndex);
                    currentIndex = childIndex;
                    currentItem = arr[currentIndex];
                    leftChildIndex = 2 * currentIndex + 1;
                    rightChildIndex = 2 * currentIndex + 2;
                    if (arr[leftChildIndex] == null)
                    	return;
                    
                    if (arr[rightChildIndex] == null)
                    	childIndex = leftChildIndex;
                    else if (arr[rightChildIndex].cost < arr[leftChildIndex].cost)
                        childIndex = rightChildIndex;
                    else 
                        childIndex = leftChildIndex;
                    
                }
            }

        	public void swapPositions(int currIndex, int parIndex){
        		Vertex temp = arr[currIndex];
        		arr[currIndex] = arr[parIndex];
        		arr[parIndex] = temp;
        	}

            public boolean isEmpty(){
            	return currSize == 0;
            }
        }
    }
}
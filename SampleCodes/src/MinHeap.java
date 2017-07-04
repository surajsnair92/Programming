import java.util.*;
public class MinHeap {
	int currentSize;
	int arr[];
	MinHeap(int size, int a[]){
		currentSize = size;
		arr = Arrays.copyOf(a, size);
	}
	public void insert(int ele){
		//arr = new int[currentSize];
		if(currentSize == 0)
			arr[0] = ele;
		else{
			currentSize += 1;
			arr = Arrays.copyOf(arr, currentSize);
			arr[currentSize-1] = ele;
		}
		heapifyUp();
	}
	public void heapifyUp(){
		int currentIndex = currentSize -1;
		int parentIndex = (currentIndex - 1)/2;
		while(arr[currentIndex] < arr[parentIndex]){
			if(arr[currentIndex] < arr[parentIndex])
				swapPositions(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = (currentIndex - 1)/2;
		}
	}
	public void delete(){
		arr[0] = arr[currentSize-1];
		currentSize -=1;
		arr = Arrays.copyOf(arr, currentSize);
		heapifyDown(0);
	}
	public void heapifyDown(int currentIndex){
		int leftChildIndex = 1; 
		int rightChildIndex;
		while(((2*currentIndex) + 1) < currentSize){
			leftChildIndex = (2*currentIndex) + 1;
			rightChildIndex = (2*currentIndex) + 2;
			int toBeCompared = compareChildren(leftChildIndex,rightChildIndex);
			if(arr[currentIndex] > arr[toBeCompared])
				swapPositions(currentIndex,toBeCompared);
			currentIndex = toBeCompared;
		}
	}
	public int compareChildren(int left, int right){
		if(arr[left] < arr[right])
			return left;
		else
			return right;
	}
	public void swapPositions(int currIndex, int parIndex){
		int temp = arr[currIndex];
		arr[currIndex] = arr[parIndex];
		arr[parIndex] = temp;
	}
	public void print(){
		int len = arr.length;
		for(int i = 0; i<len; i++){
			System.out.println(arr[i]);
		}
	}
	public static void main(String...args){
		int ch;
		int arr[] = {3,22,34,27,29,41,47,33,38,39,60};
		Scanner s = new Scanner(System.in);
		//arr = Arrays.copyOf(arr, arr.length+1);
		//		System.out.println("Enter element to be inserted");
		//		ch = s.nextInt();
		MinHeap mh = new MinHeap(arr.length,arr);
		mh.insert(62);
		System.out.println("After Insert");
		mh.print();
		mh.delete();
		System.out.println("After Delete");
		mh.print();
	}
}

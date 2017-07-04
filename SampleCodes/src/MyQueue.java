import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueue {
	List<Integer> lst = new ArrayList<Integer>();
	int index;
	
	public void enqueue(int num){
		lst.add(num);
	}
	public void dequeue(){
		lst.remove(0);
	}
	public static void main(String...args){
		MyQueue queue = new MyQueue();
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);
		queue.enqueue(60);
		
		
	}

}

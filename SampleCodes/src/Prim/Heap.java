package Prim;
import java.util.ArrayList;

public class Heap {

	public ArrayList<Integer> key;
	public int size = 0;
	
	public Heap(){
		this.key = new ArrayList<>();
		this.key.add(0);
		this.size = 0;
	}
	
	public void bubbleUp(int pos){
		if((int) pos/2 > 0){
			while(this.key.get(pos) > this.key.get(pos/2)){
				int temp = this.key.get(pos/2);
				this.key.set(pos/2, this.key.get(pos));
				this.key.set(pos, temp);
				pos/=2;
			}
		}
	}
	
	public void insertKey(int number){
		this.key.add(number);
		this.size += 1;
		bubbleUp(this.size);
	}
	
	public int findMinChild(int pos){
		if(this.size < pos*2+1)
			return pos*2;
		else if(this.key.get(pos*2)<this.key.get(pos*2+1))
			return pos*2;
		else
			return pos*2+1;
		
	}
	
	public void bubbleDown(int pos){
		while(pos*2 <= this.size){
			int minChildIndex = findMinChild(pos);
			if(this.key.get(pos) > this.key.get(minChildIndex)){
				int temp = this.key.get(pos);
				this.key.set(pos, this.key.get(minChildIndex));
				this.key.set(minChildIndex, temp);
			}
			pos = minChildIndex;
		}
	}
	
	public int popMinimum(){
		int popVal = this.key.get(1);
		this.key.set(1, this.key.get(this.size));
		this.key.remove(this.size);
		this.size -= 1;
		bubbleDown(1);
		return popVal;
	}
	
	public void heapify(ArrayList<Integer> arr){
		int heapify_start_pos = arr.size()/2;
		this.size = arr.size();
		this.key.addAll(arr);
		while(heapify_start_pos > 0){
			bubbleDown(heapify_start_pos);
			heapify_start_pos -= 1;
		}
	}
	
//	public static void main(String[] args){
//		Heap a = new Heap();
//		ArrayList<Integer> b = new ArrayList<>();
//		b.add(92);
//		b.add(45);
//		b.add(10);
//		b.add(2);
//		b.add(60);
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

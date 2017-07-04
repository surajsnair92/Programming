
class Node{
	int data;
	Node next;
	
	Node(){
		data =0;
		next = null;
	}
	Node(int d){
		this.data = d;
		next = null;
	}
	
	public int getData(){
		return data;
	}
	public Node getNext(){
		return next;
	}
	public void setNext(Node temp){
		next = temp;
	}
	public void setData(int d){
		data = d;
	}
}
public class SLinkList extends Node{
	Node start,end;
	int size;
	SLinkList(){
		start = null;
		end = null;
		size =0;
	}
	SLinkList(Node n){
		start = n;
		end = n;
	}
	public void getSize(Node n){
		while(n!= null){
			size+=1;
			n = n.next;
		}
	}
	public void insertAtHead(int d){
		Node newNode = new Node(d);
		if(this.start == null){
			start = newNode;
			end = start;
		}
		else{
			newNode.setNext(start);
			start = newNode;
		}
	}
	
	public void insertAtPos(int d, int pos){
		Node newNode = new Node(d);
		Node temp = this.start;
		pos -=1;
//		for(int i =1;i< size; i++){
//			if (i == pos){
//				Node t = temp.getNext();
//				temp.setNext(newNode);
//				newNode.setNext(t);
//				break;
//			}
//			temp = temp.getNext();
//			
//		}
		int i =0;
		while(i!=pos){
			temp = temp.getNext();
			i++;
		}
		Node t = temp.getNext();
		temp.setNext(newNode);
		newNode.setNext(t);
		size++;
		//return newNode;
	}
	
	public void deleteAtPos(int pos){
		Node temp = this.start;
		pos -=1;
		int i =0;
		while(i!=pos-1){
			temp = temp.getNext();
			i++;
		}
		
		Node t = temp.getNext();
		temp.setNext(t.getNext());
		
//		for (int i =0; i< size; i++){
//			if(i == pos){
//				Node t = temp.getNext();
//				temp.setNext(t.getNext());
//			}
//			temp = temp.getNext();
//		}
	}
	public void display(){
		Node temp = this.start;
		while(temp!= null){
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	public static void main(String...args){
		SLinkList sl = new SLinkList();
		Node n = new Node(15);
		sl.start = n;
		Node n1 = new Node(25);
		n.next = n1;
		sl.end = n1;
		//sl.end = n1;
		
		sl.insertAtHead(5);
		sl.insertAtHead(6);
		sl.insertAtHead(7);
		sl.insertAtHead(8);
		sl.insertAtPos(22, 6);
		//sl.deleteAtPos(5);
		//sl.display();
		Node n2 = new Node(99);
		n1.next = n2;
		sl.end = n2;
		sl.display();
		
	}
}

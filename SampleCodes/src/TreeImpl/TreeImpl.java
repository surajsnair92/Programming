package TreeImpl;

import java.util.Scanner;

public class TreeImpl {
	Node root;
	public void insert(int data){
		root = insertImpl(root,data);
	}
	public Node insertImpl(Node n, int d){
		if(n == null){
			n = new Node(d);
		}
		else{
			if(n.left != null)
				n.left = insertImpl(n.left,d);
			else
				n.right = insertImpl(n.right,d);
		}
		return n;
	}
	public void inorder(){
		inorderImp(root);
	}
	private void inorderImp(Node n){
		if(n!= null){
			inorderImp(n.left);
			System.out.println(n.data);
			inorderImp(n.right);
		}
	}
	public void search(int d){
		int p = searchImpl(root,d);
		if(p == 0)
			System.out.println("Found");
		else
			System.out.println("Not Found");
	}
	private int searchImpl(Node n,int d){
		int flag = 1;
		if(n!= null){
			if(n.data == d){
				flag = 0;
			}
			else{
				searchImpl(n.left,d);
				searchImpl(n.right,d);
			}
			//searchImpl(n.left);
			//searchImpl(n.right);
		}
		return flag;
	}
	public static void main(String...args){
		Scanner s = new Scanner(System.in);
		TreeImpl t = new TreeImpl();
		int ch =1;
		while(ch == 1){
			t.insert(s.nextInt());
			ch = s.nextInt();
		}
		t.inorder();
		System.out.println("Search?");
		int j = s.nextInt();
		if(j==2)
			t.search(10);
	}
}

import java.util.*;
import java.util.concurrent.ExecutionException;


public class LecTripletWeight {
	static int pho = 0;
	public static void main(String...args){
		//Scanner scan = new Scanner(System.in);
	    int A[] = {1, 4, 10};
	    int B[] = {2, 15, 20};
	    int C[] = {10, 12};
		int aLen = A.length;
		int bLen = B.length;
		int cLen = C.length;
		//triplet(A, B, C, aLen, bLen, cLen);
		execution();
	}
	public static void triplet(int a[],int b[], int c[], int p, int q, int r){
		int i = 0,j = 0,k = 0;
		int difference = Integer.MAX_VALUE;
		int fi = 0,fj = 0,fk = 0;
		while(i<p && j<q && k<r){
			int minimum = Math.min(a[i], Math.min(b[j],c[k]));
			int maximum = Math.max(a[i], Math.max(b[j],c[k]));
			
			if(maximum-minimum <difference){
				fi=i;
				fj=j;
				fk=k;
				difference = maximum - minimum;
			}
			if(a[i] == minimum)
				i++;
			else if(b[j] == minimum)
				j++;
			else
				k++;
		}
		System.out.println(a[fi] + " " + b[fj] + " " +c[fk]);
	}
	public static void execution(){
		
		for(int i =1;i<=8;i++){
			for(int j=1;j<=i;j++){
				for(int k=1;k<=j;k++){
				System.out.println(i+" "+j+" "+k +" "+ ++pho);
				}
			}
		}
		//System.out.println(pho);
		// n+k-1 C k
	}
	public static void triplet1(int a[],int b[], int c[], int p, int q, int r){
		int i = 0,j = 0,k = 0;
		int difference = Integer.MAX_VALUE;
		int fi = 0,fj = 0,fk = 0;
		while(i<p && j<q && k<r){
			int minimum = Math.min(a[i], Math.min(b[j],c[k]));
			int maximum = Math.max(a[i], Math.max(b[j],c[k]));
			
			if(maximum-minimum <difference){
				fi=i;
				fj=j;
				fk=k;
				difference = maximum - minimum;
			}
			if(a[i] == minimum)
				i++;
			else if(b[j] == minimum)
				j++;
			else
				k++;
		}
		System.out.println(a[fi] + " " + b[fj] + " " +c[fk]);
	}
}

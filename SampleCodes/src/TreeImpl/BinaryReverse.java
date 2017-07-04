package TreeImpl;

import java.util.Scanner;

public class BinaryReverse {
	public static void main(String...args){
		int arr[];
		int n;
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		int k = 1;
		arr = new int[n];
		arr[0] = 0;
		revFunc(arr,k,n>>1);
		for (int i=0;i<n;i++){
			System.out.print(arr[i]+" ");
		}
	}
	public static void revFunc(int arr[],int k, int n){
		if(n!=0){
			for(int i =0;i<k;i++)
				arr[i+k] = arr[i] ^ n;
		revFunc(arr,k*2,n>>1);
		}
		
	}
}

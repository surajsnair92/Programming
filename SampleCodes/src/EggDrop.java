import java.util.*;
public class EggDrop {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of floors");
		int floor = scan.nextInt();
		System.out.println("Enter the number of eggs");
		int eggs = scan.nextInt();
		 calculate(floor, eggs);
		
	}
	public static void calculate(int f, int e){
		int arr[][] = new int [e+1][f+1];
		int val = 0;
		int temp = 0;
		for(int i = 1;i<=e;i++){
			arr[i][1] = 1;
			arr[i][0] = 0;
		}
		for(int j=1; j<=f; j++)
			arr[1][j] = j;
		for(int i =2;i<=e;i++){
			for(int j =2;j<=f;j++){
				arr[i][j] = Integer.MAX_VALUE;
				for(int k=1; k<=j;k++){
					val = 1 + Math.max(arr[i-1][k-1], arr[i][j-k]); //arr[egg remaining][floor remaining]
					if(val < arr[i][j])
						arr[i][j] = val;
						
				}
			}
		}
		System.out.println(arr[e][f]);
	}
}
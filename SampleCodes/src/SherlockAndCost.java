
import java.io.*;
import java.util.*;

public class SherlockAndCost {
	

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int numT,inp;
        Scanner scan = new Scanner(System.in);
        numT = scan.nextInt();
        int sum = 0;
        while(numT > 0){
        	int fSum = Integer.MIN_VALUE;
            inp = scan.nextInt();
            int arr[] = new int[inp];
//            int arr[] = {10,1,10,1,10};
            for(int i =0;i<inp;i++){
                arr[i] = scan.nextInt();
            }
            int tempSum = 0;
            
            
           for(int j = 0; j<inp;j++ ){
        	   int tempArr[] = new int[arr[j]];
        	   for(int k=1,p=0;k<= arr[j];k++,p++){
        		   tempArr[p] = k;
        		   tempSum = calculate(tempArr, tempArr.length);
        	   }
        	   if(fSum < tempSum){
        		   fSum = tempSum;
        	   }
           }
           System.out.println(fSum);
            numT--;
        }
    }
    public static int calculate(int arr[],int inp){
    	  int sum = 0;
    	  for(int j=1;j<inp;j++){
              sum = sum + Math.abs((arr[j]-arr[j-1]));
          }
    	  return sum;
    }
}
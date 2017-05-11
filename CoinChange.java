/* Dynamic Programming Java implementation of Coin
   Change problem */
import java.util.Arrays;
 
class CoinChange
{
    static long countWays(int S[], int m, int n){
    	int[] table = new int[n+1];
    	Arrays.fill(table,0);
    	table[0] = 1;
    	for(int i=0;i<m;i++){
    		for (int j = S[i];j<=n;j++){
    			table[j] = table[j] + table[j-S[i]];
    		}
    	}
    	return table[n];
    }
    static long minCoins(int S[], int m, int n){
    	int[] table = new int[n+1];
    	Arrays.fill(table, 0);
    	table[0] = 0;
    	int temp;
    	for(int i=0;i<m;i++){
    		for(int j=S[i];j<=n;j++){
    			temp = table[j-S[i]] + 1;
    			if(i == 0)
    				table[j] = temp;
    			else
    				table[j] = Math.min(temp, table[j]);
    		}
    	}
    	return table[n];
    }
 
    // Driver Function to test above function
    public static void main(String args[])
    {
        int arr[] = {1,2,4,5};
        int m = arr.length;
        int n = 17;
        System.out.println(minCoins(arr, m, n));
    }
}
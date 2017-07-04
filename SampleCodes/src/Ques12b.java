
public class Ques12b {
	static int max = 0;
	public static int findMax(int arr[][], int rows, int mid){
	    int max_index = 0;
	    for (int i = 0; i < rows; i++)
	    {
	        if (max < arr[i][mid])
	        {
	            // Saving global maximum and its index
	            // to check its neighbours
	            max = arr[i][mid];
	            max_index = i;
	        }
	    }
	    return max_index;
	}
	public static int beatTheNeighbor(int arr[][], int rows, int columns,int mid){
		  
		    int max_index = findMax(arr, rows, mid);
		 
		    // If we are on the first or last column,
		    // max is a peak
		    if (mid == 0 || mid == columns-1)
		        return max;
		 
		    // If mid column maximum is also peak
		    if (max >= arr[max_index][mid-1] &&
		            max >= arr[max_index][mid+1])
		        return max;
		 
		    // If max is less than its left
		    if (max < arr[max_index][mid-1])
		        return beatTheNeighbor(arr, rows, columns, mid - mid/2);
		 
		    // If max is less than its left
		    // if (max < arr[max_index][mid+1])
		    return beatTheNeighbor(arr, rows, columns, mid+mid/2);
	}
	public static void main(String...args){
		int arr[][] = {{ 10, 8, 10, 10 },
                { 14, 13, 12, 11 },
                { 15, 9, 11, 21 },
                { 16, 17, 19, 20 } };
		int rows = 4, columns = 4;
System.out.println(beatTheNeighbor(arr, rows, columns,columns/2));

	}
}

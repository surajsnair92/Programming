import java.io.*;

// i is the lower index and j is the higher index
class Ques12a{
	static int beatTheNeighbor(int arr[], int i, int j, int n){
		int mid = (i+j)/2; 

		if ((mid == 0 || arr[mid-1] <= arr[mid]) && 
				(mid == n-1 || arr[mid+1] <= arr[mid]))
			return mid;

		else if (mid > 0 && arr[mid-1] > arr[mid])
			return beatTheNeighbor(arr, i, (mid -1), n);
		
		else
			return beatTheNeighbor(arr, (mid + 1), j, n);

	}
	public static void main (String...args){
		int arr[] = {5,4,3,2,1,7,8,9,10,11,12};
		int n = arr.length;
		System.out.println("Number is " +
				arr[beatTheNeighbor(arr, 0, n-1, n)]);
	}
}
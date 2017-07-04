
public class MergeSort {
	public void divide(int arr[],int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			divide(arr,low,mid);
			divide(arr,mid+1,high);
			merge(arr,low,mid,high);
		}

	}
	public void merge(int arr[],int low,int mid,int high){
		int l1 = mid-low+1;
		int l2 = high - mid;
		int i=0,j=0,k=low;
		int t1[] = new int[l1];
		int t2[] = new int[l2];
		
		for(int a =0;a<l1;a++){
			t1[a] = arr[low + a];
		}
		for(int p =0;p<l2;p++){
			t2[p] = arr[mid+1+p];
		}
		
		while(i<l1 && j< l2){
			if(t1[i] <= t2[j]){
				arr[k] = t1[i];
				i++;
			}
			else{
				arr[k] = t2[j];
				j++;
			}
			k++;
		}
		while(i< l1){
			arr[k] = t1[i];
			i++;
			k++;
		}
		while(j< l2){
			arr[k] = t2[j];
			j++;
			k++;
		}
	}
	public static void print(int arr[]){
		int l = arr.length;
		for(int i=0;i<l;i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
	}
	public static void main(String...args){
		int arr[] = {1,6,2,31,52,12,4,8,12,3,7};
		int len = arr.length;
		MergeSort m = new MergeSort();
		//m.merge(arr, 0, (len-1)/2, len-1);
		m.print(arr);
		m.divide(arr, 0, len-1);
		m.print(arr);
	}
}


public class Assign1Q15 {
	public static void main(String...args){
		double arr[] = {0.3,1.8,0.9,1,3,4.3,8.9,0.6};
		int m = arr.length;
		double newArr[] = new double[m];
		int p = 0;
		for(int i =0;i<m;i++){
			if(arr[i] < 2){
				newArr[p] = arr[i];
				p++;
			}
		}
		for(int i =0;i<p;i++)
			System.out.println(newArr[i]);
	}
	public void calc(int[] arr,int size){
		int i = 0;
		int l = i+1;
		int j = size -1;
		boolean status = false;
		while(i < size){
			if( (arr[i] + arr[l] + arr[j] > 1) && (arr[i] + arr[l] + arr[j] < 2)){
				status = true;
				System.out.println("("+ arr[i] +" "+ arr[l]+" "+ arr[j]);
			}
		}
		if(!status)
			System.out.println("No triplets found");
		
	}
}
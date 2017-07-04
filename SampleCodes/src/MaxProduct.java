
public class MaxProduct {
	public static void main(String...args){
		int a[] = {1,3,4,1,34,-9,0,41};
		int  low = 0;
		int high = a.length;
		int mid = high/2;
		// maxProd(a,low,high,mid);
	}
	public void maxProd(int a[],int low,int mid,int high){
		int suml = a[mid];
		int maxl = suml;
		int minl = suml;
		int sumr = a[mid + 1];
		int maxr = sumr;
		int minr = sumr;
		for(int i = mid-1;i>=low;i--){
			suml *=a[i];
			if(suml > maxl)
				maxl = suml;
			if(suml < minl)
				minl = suml;
		}
		for(int j = mid+2;j<=high;j++){
			sumr*=a[j];
			if(sumr>maxr)
				maxr = sumr;
			if(sumr < minr)
				minr = sumr;
		}
		
		
	}

}

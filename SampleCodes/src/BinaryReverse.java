public class BinaryReverse {
	
	public int reverseBinary(int number){
		int res = 0;
		while(number>0){
			res=res<<1; 		
			res = res|(number & 1); 			
			number=number>>1;
		}
		return res;
	}
	public static void main(String args[]){
		int x =6;
		BinaryReverse b = new BinaryReverse();
		System.out.println(b.reverseBinary(x));
	}

}
// The UTCs class defines static methods for the UTC type.
// In particular, it defines a static factory method for creating
// values of the UTC type.
public class UTCs{

	// NonNegInteger NonNegInteger -> UTC
	// GIVEN:    h and m which represents hours and minutes respectively.
	// RETURNS:  a UTC
	// EXAMPLES: u1.make(10,20);
	// STRATEGY: Use template for UTC.
	// EXAMPLE: UTCs.make(10,10) => new GMT(10,10);
	public static UTC make (int h, int m) {
		return new GMT(h,m);
	}












	////////////////////////////TEST/////////////////////////////////
	public static void main(String...args){
		int h = 10;
		int m = 10;
		UTC t1 = UTCs.make(h, m);
		System.out.println(t1.equals(new GMT(h,m)));
	}

}
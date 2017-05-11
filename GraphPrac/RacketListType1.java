// Constructor template for RacketListType1<E>: new RacketListType1<E> (f,r)
// Interpretation: 
//        f is of type E which represents the first element of the list. 
//        r is of type RacketList<E> which represents a list whose rest is everything but the first.
public class RacketListType1<E> implements RacketList<E> {

	RacketList<E> rest;
	E first;

	// java constructor
	public RacketListType1(){
		rest = null;
		first = null;
	}
	// java constructor
	public RacketListType1(E f){
		first = f;
		rest = null;
	}
	// java constructor
	public RacketListType1(E f, RacketList<E> r){
		first = f;
		rest = r;	
	}

	// RETURNS: true iff first and rest are null.
	// EXAMPLES: RacketList<Flight> fs00 = RacketLists.empty();
	//           fd00.isEmpty() => true
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(rest ==null&&first == null)
			return true;
		return false;
	}

	// WHERE: this list is non-empty
	// RETURNS: first element of this list
	public E first() {
		return first;
	}

	// WHERE: this list is non-empty
	// RETURNS: rest of this list
	public RacketList<E> rest() {
		return rest;
	}

	// GIVEN:   an arbitrary value x of type E
	// RETURNS: a list whose first element is x and whose
	//          rest is this list
	// EXAMPLE: RacketLists1<String> testlist = new RacketLists1<String>("abc",null);
	//			testlist.cons("1");
	//          testlist.cons("2");
	//          testlist.cons("3");
	//          System.out.println(testlist.toString()) 
	//       => RacketLists [rest=RacketLists 
	//          [rest=RacketLists [rest=RacketLists [rest=null, first=abc], first=1], first=2], first=3]
	// STRATEGY: Divide into cases for RacketList<E>
	public RacketList<E> cons(E x) {
		if(this.isEmpty()){
			this.first = x;
		}
		else if(rest == null){
			this.rest = new RacketListType1<E>(first);
			this.first = x;
		}
		else{
			rest = this.rest.cons(first);
			first = x;
		}
		RacketListType1<E> newlist = new RacketListType1<E>(first,rest);
		return newlist;
	}
	@Override
	// RETURNS: a String.
	public String toString() {
		return "RacketLists [rest=" + rest + ", first=" + first + "]";
	}

	//	public static void main(String...args){
	//		RacketListType1<String> testlist = new RacketListType1<String>("abc",null);
	//		testlist.cons("1");
	//		testlist.cons("2");
	//		testlist.cons("3");
	//		System.out.println(testlist.toString());}


	//		RacketList<Flight> fs00 = RacketLists.empty();
	//		RacketList<Flight> fs01 = fs00.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		RacketList<Flight> fs02 = fs01.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		
	//		RacketList<Flight> fs10 = RacketLists.empty();
	//		RacketList<Flight> fs11 = fs00.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		RacketList<Flight> fs12 = fs01.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		
	//		RacketList<Flight> fs3 = RacketLists.empty();
	//		//fs3.cons(fs12); --> This is what fails
	//		
	//		System.out.println(fs3.toString());
	//		System.out.println(fs12.toString());
	//	}



}

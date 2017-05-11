// The RacketLists class defines static methods for the RacketLists<E> type.
// In particular, it defines a static factory method for creating
// values of the RacketLists<E> type.
public class RacketLists<E>  {

	// GIVEN: no arguments
	// RETURNS: an empty RacketList<E>
	// EXAMPLE: RacketList<Flight> fs00 = RacketLists.empty();
	// STRATEGY: combine simpler functions.
	public static <E> RacketList<E> empty () {
		return new RacketListType1<E>();
	}


	//	public static void main(String...args){
	////		RacketLists<String> testlist = new RacketLists<String>("abc",null);
	////		testlist.cons("1");
	////		testlist.cons("2");
	////		testlist.cons("3");
	////		System.out.println(testlist.toString());
	//		
	//		RacketList<Flight> fs00 = RacketLists.empty();
	//		RacketList<Flight> fs01 = fs00.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		RacketList<Flight> fs02 = fs01.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		
	//		RacketList<Flight> fs10 = RacketLists.empty();
	//		RacketList<Flight> fs11 = fs00.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		RacketList<Flight> fs12 = fs01.cons (flt ("Delta 0121", "LGA", "MSP", 1100, 1409));
	//		
	//		RacketList<Flight> fs3 = RacketLists.empty();
	//		//fs3.cons(fs12);
	//		
	//		//System.out.println(fs3.toString());
	//	}



}

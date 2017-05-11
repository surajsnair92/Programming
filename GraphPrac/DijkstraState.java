import java.util.ArrayList;

public class DijkstraState {
	final int INFINITY_TIME = Integer.MAX_VALUE; // infinity time

	// Construct Template: new DijkstraState()
	//						ArrayList<Flight> list;
	//						list.add(flt);
	//						new DijkstraState(flt, 0, lit)
	//						new DijkstraState(new DijkstraState());
	//  Interp:
	//			flight is a flight
	//			besttime is the fastest time from start flight to this one
	//			bestpath is the list flight that come from start flight to this one,inlcusive

	private Flight flight;
	private int besttime;	
	private ArrayList<Flight> bestpath;

	// Java Constructor
	public DijkstraState(){
		besttime = INFINITY_TIME;
		flight = null;
		bestpath = new ArrayList<Flight>();
	}


	public DijkstraState(Flight f, int time,ArrayList<Flight> path){
		flight = f;
		bestpath = path;
		besttime = time;
	}

	public DijkstraState(DijkstraState djs){
		flight = djs.getFlight();
		besttime = djs.getBesttime();
		bestpath = new ArrayList<Flight>(djs.getBestpath());
	}


	//////////////////Getters and Setters////////////////////
	public Flight getFlight(){return flight;}

	public int getBesttime() {
		return besttime;
	}
	public void setBesttime(int besttime) {
		this.besttime = besttime;
	}
	public ArrayList<Flight> getBestpath() {
		return bestpath;
	}
	public void setBestpath(ArrayList<Flight> bestpath) {
		this.bestpath = bestpath;
	}
	public boolean isEmpty(){
		if(besttime == INFINITY_TIME && flight == null &&
				bestpath.isEmpty())
			return true;
		else
			return false;
	}

	////////////////////Functions/////////////////////////
	// DijkstraState -> Boolean
	// GIVEN: a DijkstraState
	// RETURNS:  true iff the given DijkstraState is equal to this one.
	// EXAMPLES:DijkstraState s1 = new DijkstraStae();
	//           DijkstraState s2 = new DijkstraState(s1);
	//           s1.isEqual(s2) => true.
	// STRATEGY: combine simpler functions.
	public boolean isEqual (DijkstraState s2) {
		return (flight.equals(s2.getFlight()) && 
				(besttime == s2.getBesttime()) &&
				bestpath.equals(s2.getBestpath()));
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	@Override
	// Object -> Boolean
	// GIVEN:    an object of type Object.
	// RETURNS:  true iff x is of type DijkstraState, and
	// is equal to this UTC
	// EXAMPLES: UTC t1 = new GMT(11, 50);
	//           UTC t2 = new GMT(11, 50);
	//           t1.equals(t2) =>  true
	// STRATEGY: divide into cases to check if the type is UTC.	
	public boolean equals( Object x )
	{
		if (x instanceof DijkstraState) {
			DijkstraState s2 = (DijkstraState) x;
			return isEqual (s2);
		}
		else 
			return false;
	}



	////////////////////////TEST///////////////////////
	public static void main(String[] arg1){

		UTC t1 = new GMT(1, 00);
		UTC t2 = new GMT(11, 50);
		UTC t3 = new GMT(12, 50);
		UTC t4 = new GMT(16, 45);
		Flight testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
		Flight testf2 = new FlightType1("fgh", "LGA","PDX",t3,t4);
		Flight testf3 = new FlightType1("abc", "BOS","MSP",t1,t2);
		ArrayList<Flight> testpath = new ArrayList<Flight>();
		testpath.add(testf1);
		testpath.add(testf2);

		DijkstraState emptystate = new DijkstraState();
		DijkstraState state1 = new DijkstraState(testf1, 100, testpath);
		DijkstraState state2 = new DijkstraState(state1);

		System.out.println(true == emptystate.isEmpty());
		System.out.println(false == state1.isEmpty());
		System.out.println(true == state1.equals(state2));
		System.out.println(false ==state1.equals(emptystate));
		System.out.println(false ==state1.equals("555"));
	}

}

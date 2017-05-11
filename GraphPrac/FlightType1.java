// Constructor template for FlightType1: new FlightType1 (n,start,end,t1,t2)
// Interpretation:
//     n is a string which represents name of the airport.
//     start is a String that represents departure airport.
//     end is a String that represents arrival airport.
//     t1 is an UTC which has hour (between 0 and 23, inclusive) and minute (between 0 and 59, inclusive) 
//     t2 is an UTC which has hour (between 0 and 23, inclusive) and minute (between 0 and 59, inclusive) 
public class FlightType1 implements Flight{

	private String name;
	private String departs;
	private String arrives;
	private GMT departsAt;
	private GMT arrivesAt;
	final int minutesInADay = 1440;

	// Constructor for Flights
	public FlightType1(String n, String start, String end, UTC t1, UTC t2){
		name = n;
		departs = start;
		arrives = end;
		departsAt = new GMT(t1.hour(),t1.minute());
		arrivesAt = new GMT(t2.hour(),t2.minute());
	}


	// RETURNS: String which represents name of the flight.
	public String name() {
		return name;
	}

	// RETURNS: String which represents departure airport
	public String departs() {
		return departs;
	}

	// RETURNS: String which represents arrival airport.
	public String arrives() {
		return arrives;
	}

	// RETURNS: a UTC which represent the departure time
	public UTC departsAt() {
		return departsAt;
	}

	// RETURNS: a UTC which represent the arrival time
	public UTC arrivesAt() {
		return arrivesAt;
	}

	// GIVEN:    a Flight.
	// RETURNS:  true iff the current flight is same as the input flight.
	// EXAMPLES: UTC t1 = new GMT(1, 00);
	//           UTC t2 = new GMT(11, 50);
	//           FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2); 
	//           FlightType1 testf3 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//           testf1.isEqual(testf3) =>  true
	// STARTEGY: combine simpler functions.
	public boolean isEqual(Flight f2) {
		return ((f2.name().compareTo(name) ==0) && f2.departsAt().isEqual(departsAt) && f2.arrivesAt().isEqual(arrivesAt) 
				&& (f2.departs().compareTo(departs) ==0) && ((f2.arrives().compareTo(arrives) == 0)));

	}

	@Override
	public int hashCode(){
		return 0;
	}


	@Override
	// GIVEN:    an object of type Object.
	// RETURNS:  true iff x is of type Flight.
	// EXAMPLES: UTC t1 = new GMT(1, 00);
	//           UTC t2 = new GMT(11, 50);
	//           FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2); 
	//           FlightType1 testf3 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//           testf1.equals(testf3) =>  true
	// STARTEGY: Divide into cases to check if the type if Flight.
	public boolean equals( Object x ){
		if (x instanceof Flight) {
			Flight f = (Flight) x;
			return isEqual (f);
		}
		else 
			return false;
	}



	////////////////////////////TEST/////////////////////////////////
	public static void main(String...args){

		UTC t1 = new GMT(1, 00);
		UTC t2 = new GMT(11, 50);
		UTC t3 = new GMT(12, 50);
		UTC t4 = new GMT(16, 45);
		FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
		FlightType1 testf2 = new FlightType1("fgh", "LGA","PDX",t3,t4);
		FlightType1 testf3 = new FlightType1("abc", "BOS","MSP",t1,t2);
		System.out.println(false == testf1.isEqual(testf2));
		System.out.println(true == testf1.isEqual(testf1));

	}
}
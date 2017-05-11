// The Flights class defines static methods for the Flight type.
// In particular, it defines a static factory method for creating
// values of the Flight type.
public class Flights {

	// GIVEN:    the name of a flight, the name of the airport from
	//           which the flight departs, the name of the airport at
	//           which the flight arrives, the time of departure in UTC,
	//           and the time of arrival in UTC
	// RETURNS:  a flight value that encapsulates the given information.
	// EXAMPLES: Flight f = Flights.make("flt1","MSP","NRT",UTCs.make(20, 15),UTCs.make(10, 40));
	// STRATEGY: Use template for FlightType1.
	public static Flight make (String n, String ap1, String ap2,
			UTC t1, UTC t2) {
		Flight f = new FlightType1(n,ap1,ap2,t1,t2);
		return f;
	}
	////////////////////////////TEST/////////////////////////////////
	public static void main(String args[]){
		Flight f = Flights.make("flt1","MSP","NRT",UTCs.make(20, 15),UTCs.make(10, 40));
		System.out.println(f.equals(new FlightType1("flt1","MSP","NRT",UTCs.make(20, 15),UTCs.make(10, 40))));
	}
}
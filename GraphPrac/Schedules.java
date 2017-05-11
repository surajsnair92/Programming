import java.util.ArrayList;


public class Schedules {

	public static void printRacketListFlight(RacketList<Flight> flst){
		if(flst == null){
			System.out.println("No Fligth");
		}
		else if(!(flst.rest() == null)){
			System.out.println(flst.first().name());
			printRacketListFlight(flst.rest());
		}
		else{
			System.out.println(flst.first().name());
		}
	}

	// GIVEN: the names of two airports, ap1 and ap2 (respectively),
	//     and a RacketList<Flight%gt; that describes all of the flights a
	//     traveller is willing to consider taking
	// RETURNS: true if and only if it is possible to fly from the
	//     first airport (ap1) to the second airport (ap2) using
	//     only the given flights
	// EXAMPLES:
	//     canGetThere ("06N", "JFK", FlightExamples.panAmFlights)  =>  false
	//     canGetThere ("JFK", "JFK", FlightExamples.panAmFlights)  =>  true
	//     canGetThere ("06N", "LAX", FlightExamples.deltaFlights)  =>  false
	//     canGetThere ("LAX", "06N", FlightExamples.deltaFlights)  =>  false
	//     canGetThere ("LGA", "PDX", FlightExamples.deltaFlights)  =>  true

	public static boolean canGetThere (String ap1,
			String ap2,
			RacketList<Flight> flights) {
		if(ap1.equals(ap2))
		{
			return true;
		}

		else if(fastestItinerary(ap1,ap2,flights).isEmpty())
		{
			return false;
		}
		else {
			return true;
		}


	}

	// GIVEN: the names of two airports, ap1 and ap2 (respectively),
	//     and a RacketList<Flight> that describes all of the flights a
	//     traveller is willing to consider taking
	// WHERE: it is possible to fly from the first airport (ap1) to
	//     the second airport (ap2) using only the given flights
	// RETURNS: a list of flights that tells how to fly from the
	//     first airport (ap1) to the second airport (ap2) in the
	//     least possible time, using only the given flights
	// NOTE: to simplify the problem, your program should incorporate
	//     the totally unrealistic simplification that no layover
	//     time is necessary, so it is possible to arrive at 1500
	//     and leave immediately on a different flight that departs
	//     at 1500
	// EXAMPLES:
	//     fastestItinerary ("JFK", "JFK", FlightExamples.panAmFlights)
	//         =>  RacketLists.empty()
	//
	//     fastestItinerary ("LGA", "PDX", FlightExamples.deltaFlights)
	// =>  RacketLists.empty().cons
	//         (Flights.make ("Delta 2163",
	//                        "MSP", "PDX",
	//                        UTCs.make (15, 0), UTCs.make (19, 2))).cons
	//             (Flights.make ("Delta 0121",
	//                            "LGA", "MSP",
	//                            UTCs.make (11, 0),
	//                            UTCs.make (14, 9)))
	//
	// (Note: The Java syntax for a method call makes those calls
	// to cons look backwards from what you're used to in Racket.)

	public static
	RacketList<Flight> fastestItinerary (String ap1,
			String ap2,
			RacketList<Flight> flights) {

		if(ap1.equals(ap2)){

			return RacketLists.empty();
		}
		else{

			RacketList<Flight> fastest = RacketLists.empty();
			DijkstraState fasteststate = new DijkstraState();
			Graph g = new Graph();
			g.buildGraph(flights);
			ArrayList<Flight> initiallist = g.query(ap1);
			for (Flight f : initiallist){
				DijkstraGraph dg = new DijkstraGraph(f, g);
				DijkstraState possiblebest = new DijkstraState(dg.getFastestItenerary(ap2));
				if (fasteststate.getBesttime() > possiblebest.getBesttime()){
					fasteststate = possiblebest;
				}
			}

			if (fasteststate.isEmpty()){
				return fastest;
			}
			else{
				for(int i = fasteststate.getBestpath().size()-1; i>=0; i--){
					fastest.cons(fasteststate.getBestpath().get(i));
				}
				return fastest;
			}
		}

	}

	// GIVEN: the names of two airports, ap1 and ap2 (respectively),
	//     and a RacketList<Flight> that describes all of the flights a
	//     traveller is willing to consider taking
	// WHERE: it is possible to fly from the first airport (ap1) to
	//     the second airport (ap2) using only the given flights
	// RETURNS: the number of minutes it takes to fly from the first
	//     airport (ap1) to the second airport (ap2), including any
	//     layovers, by the fastest possible route that uses only
	//     the given flights
	// EXAMPLES:
	//     travelTime ("JFK", "JFK", FlightExamples.panAmFlights)  =>  0
	//     travelTime ("LGA", "PDX", FlightExamples.deltaFlights)  =>  482

	public static int travelTime (String ap1,
			String ap2,
			RacketList<Flight> flights) {
		if(ap1.equals(ap2)){
			return 0;
		}
		else{

			RacketList<Flight> fastest = RacketLists.empty();
			DijkstraState fasteststate = new DijkstraState();
			Graph g = new Graph();
			g.buildGraph(flights);
			ArrayList<Flight> initiallist = g.query(ap1);
			for (Flight f : initiallist){
				DijkstraGraph dg = new DijkstraGraph(f, g);
				DijkstraState possiblebest = new DijkstraState(dg.getFastestItenerary(ap2));
				if (fasteststate.getBesttime() > possiblebest.getBesttime()){
					fasteststate = possiblebest;
				}
			}
			return fasteststate.getBesttime();

		}
	}


	public static void main(String[] arg1){
		printRacketListFlight(fastestItinerary("BOS","PDX",(FlightExamples.simpleTestFlights)));
		printRacketListFlight(fastestItinerary("PDX","LHR",(FlightExamples.deltaFlights)));
		System.out.println(2410 == travelTime("BZN","LGA",(FlightExamples.deltaFlights)));
		System.out.println(105 == travelTime("BOS","PDX",(FlightExamples.simpleTestFlights)));
		System.out.println(false == canGetThere("ABC","LGA",(FlightExamples.deltaFlights)));
		System.out.println(true == canGetThere("ABC","ABC",(FlightExamples.deltaFlights)));
		System.out.println(false == canGetThere("LGA","ABC",(FlightExamples.deltaFlights)));
		System.out.println(true == canGetThere("LGA","LAX",(FlightExamples.deltaFlights)));
		System.out.println(560 == travelTime("LGA","LAX",(FlightExamples.deltaFlights)));
		System.out.println(false == canGetThere("LGA","ABC",(FlightExamples.deltaFlights)));

	}

}

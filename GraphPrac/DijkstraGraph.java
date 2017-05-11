import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraGraph {
	final static int minutesInADay = 1440;

	private Flight start;
	private PriorityQueue<DijkstraState> unvisited;
	private Map<String, DijkstraState> bestit;
	private ArrayList<String> visited;
	Graph graph;

	// java constructor
	DijkstraGraph (Flight f, Graph g){
		start = f;
		graph = g;
		unvisited = new PriorityQueue<DijkstraState>(g.getSize()*g.getSize(),DijkstraStateomparator);
		visited = new ArrayList<String>();
		ArrayList<Flight> initiallist = new ArrayList<Flight>();
		initiallist.add(f);
		DijkstraState state = new DijkstraState(f,0,initiallist);
		bestit = new HashMap<String, DijkstraState>();
		bestit.put(f.name(), state);
		unvisited.add(state);

	}

	public static Comparator<DijkstraState> DijkstraStateomparator = new Comparator<DijkstraState>(){  	   
		@Override  
		public int compare(DijkstraState s1, DijkstraState s2) {  
			return (s2.getBesttime() - s1.getBesttime());  
		}  
	}; 


	///////////////Getters and Setters/////////////////////////////
	public Flight getStart() {
		return start;
	}

	public void setStart(Flight start) {
		this.start = start;
	}

	public PriorityQueue<DijkstraState> getUnvisited() {
		return unvisited;
	}

	public void setUnvisited(PriorityQueue<DijkstraState> unvisited) {
		this.unvisited = unvisited;
	}

	public Map<String, DijkstraState> getBestit() {
		return bestit;
	}

	public void setBestit(Map<String, DijkstraState> bestit) {
		this.bestit = bestit;
	}

	public ArrayList<String> getVisited() {
		return visited;
	}

	public void setVisited(ArrayList<String> visited) {
		this.visited = visited;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	///////////////////////Functions//////////////////////////////  
	// DijkstraGraph -> Boolean
	// GIVEN: a DijkstraGraph
	// RETURNS:  true iff the given DijkstraGraph is equal to this DijkstraGraph.
	// EXAMPLES: Graph g = new Graph();
	//			 g.buildgraph(FlightExamples.deltaFlights);
	//			 FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//			 DijkstraGraph d1 = new DijkstraGraph(g, testf1);
	//           DijkstraGraph d2 = new DijkstraGraph(g, testf1);
	//           d1.isEqual(d2) => true.
	// STRATEGY: combine simpler functions.
	public boolean isEqual (DijkstraGraph d2) {
		return (start.equals(d2.getStart())) &&
				(unvisited.equals(d2.getUnvisited())) &&
				(visited.equals(d2.getVisited())) &&
				(bestit.equals(d2.getBestit())) &&
				(graph.equals(d2.getGraph()));
	}



	@Override
	// Object -> Boolean
	// GIVEN:    an object of type Object.
	// RETURNS:  true iff x is of type DijkstraGraph, and
	// is equal to this DijkstraGraph
	// EXAMPLES:  Graph g = new Graph();
	//			 g.buildgraph(FlightExamples.deltaFlights);
	//			 FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//			 DijkstraGraph d1 = new DijkstraGraph(g, testf1);
	//           DijkstraGraph d2 = new DijkstraGraph(g, testf1);
	//           d1.equals(d2) => true.
	// STARTEGY: divide into cases to check if the type is UTC.	
	public boolean equals( Object x )
	{
		if (x instanceof DijkstraGraph) {
			DijkstraGraph d2 = (DijkstraGraph) x;
			return isEqual (d2);
		}
		else 
			return false;
	}


	// String -> DijkstraState
	// GIVEN: the destination airport
	// RETURN: the DijkstraState that the flight is the destination airport
	//         and the time is shortest
	//		
	// EXAMPLE: beststate = new DijkstraState(testf1, 170, path)
	//			graph.getFastestItinerary("PDX") => beststate
	public DijkstraState getFastestItenerary(String end){
		this.expand();
		DijkstraState fastest = new DijkstraState();
		Iterator<Entry<String, DijkstraState>> iterator = bestit.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, DijkstraState> entry = iterator.next();  
			//System.out.println(entry.getKey());      
			//System.out.println(entry.getValue());    
			if(entry.getValue().getFlight().arrives() == end){
				if(fastest.getBesttime() > entry.getValue().getBesttime()){
					fastest = entry.getValue();
				}
			}
		}   	  
		return fastest;
	}


	// UTC -> NonNegInteger
	// RETURNS:  non negative integer which represents time in minutes.
	// EXAMPLES: GMT t1 = (GMT) UTCs.make(20, 10);
	//           t1.toMinutes() => 1210
	// STRATEGY: combine simpler functions.
	private static int toMinutes(UTC t){	
		return (t.hour()*60) + (t.minute());

	}


	// UTC UTC -> NonNegInteger
	// GIVEN:    time in UTC.
	// RETURNS:  non negative integer which represents the difference between 
	//           current and input time in minutes.
	// EXAMPLES: GMT t1 = (GMT) UTCs.make(20, 10);
	//           GMT t2 = (GMT) UTCs.make(15, 10); 
	//           timeDifference(t2,t1) = 1140
	private static int timeDifference(UTC start, UTC end){
		int differenceInMinutes = toMinutes(end) - toMinutes(start);
		if (differenceInMinutes < 0)
			return minutesInADay + differenceInMinutes;
		else
			return differenceInMinutes;
	}

	// Flight -> NonNegInteger
	// RETURNS: a Non negative integer which represents the flight duration in minutes.
	// EXAMPLE: UTC t1 = new GMT(1, 00);
	//          UTC t2 = new GMT(11, 50);
	//          FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//          flightDuration(testf1) => 650
	// STARTEGY: combine simpler functions.
	private static int flightDuration(Flight f){
		return timeDifference(f.departsAt(),f.arrivesAt());
	}

	// Flight Flight -> NonNegInteger
	// GIVEN: two flights
	// RETURNS: a Non negative integer which represents the layover duration.
	// EXAMPLE: UTC t1 = new GMT(1, 00);
	//          UTC t2 = new GMT(11, 50);
	//  		UTC t3 = new GMT(12, 50);
	//          UTC t4 = new GMT(16, 45);
	//          FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//  		FlightType1 testf2 = new FlightType1("fgh", "LGA","PDX",t3,t4);
	//          layoverDuration(testf1,testf2) => 60
	// STARTEGY: combine simpler functions.
	private static int layoverDuration(Flight ffirst, Flight fsecond){
		return timeDifference(ffirst.arrivesAt(), fsecond.departsAt());
	}


	// ArrayList<Flight> -> NonNegInteger
	// GIVEN: a arraylist of flight
	// RETURN: the total time from start airport to end airport
	// EXAMPLE: UTC t1 = new GMT(11, 00);
	//          UTC t2 = new GMT(11, 50);
	//  		UTC t3 = new GMT(12, 50);
	//          UTC t4 = new GMT(13, 50);
	//          FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//  		FlightType1 testf2 = new FlightType1("fgh", "MSP","PDX",t3,t4);
	//			ArrayList<Flight> flist = new ArrayList<Flight>();
	//			flist.add(testf1); flist.add(test f2)
	//          itineraryDuration(fllist) => 170
	private static int itineraryDuration(ArrayList<Flight> flst){
		if(flst.isEmpty()){return 0;}
		else if(flst.size() == 1) {
			return flightDuration(flst.get(0));
		}
		else{
			int time = 0;
			for (int i = 0; i< flst.size(); i++){
				time += flightDuration(flst.get(i));
			}
			for (int i = 0; i< flst.size()-1; i++){
				time += layoverDuration(flst.get(i), flst.get(i+1));
			}
			return time;
		}
	}



	// ArrayList<Flight> Flight -> Boolean
	// GIVEN: a ArrayList of Flight and a Flight
	// RETURN: true iff add this flight to flight list will create a loop
	// EXAMPLE: UTC t1 = new GMT(11, 00);
	//          UTC t2 = new GMT(11, 50);
	//  		UTC t3 = new GMT(12, 50);
	//          UTC t4 = new GMT(13, 50);
	//          FlightType1 testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
	//  		FlightType1 testf2 = new FlightType1("fgh", "MSP","PDX",t3,t4);
	//			FlightType1 testf3 = new FlightType1("zzz", "PDX", "BOS". t1, t3);
	//			ArrayList<Flight> flist = new ArrayList<Flight>();
	//			flist.add(testf1); flist.add(test f2)
	//          hasloop(flist testf3) => true

	private static boolean hasloop (ArrayList<Flight> flst, Flight f){
		for(Flight finlist: flst){
			if(f.arrives() == finlist.departs()){
				return true;
			}
		}
		return false;
	}

	// GIVEN: none
	// RETURN: none
	// Halting measure: length of unvisited
	// Interp: pop first flight in priority queue unvisited,
	//		   get current best state of this flight,
	//         create new DijkstraState by add connected flights to 
	//		   that flight. Then call update function
	// EXAMPLE: expand() 

	private void expand(){
		Flight exploreflight = this.getUnvisited().poll().getFlight();
		visited.add(exploreflight.name());
		ArrayList<Flight> flightlist = graph.query(exploreflight.arrives());
		update(exploreflight, flightlist);
		while(!unvisited.isEmpty()){
			expand();
		}
	}
	
	// Flight ArrayList<Flight> -> none
	// GIVEN: a flight that pop from priority queue, and the list
	// of flight that start airport is the end airport of given flight
	// RETURN: none
	// INTERP:  create new state after append flight in flight list to current best
	// path of explore flight, compare the new state to current best state in list.
	// if the new state is better, replace the old one, if the new state is not in list,
	// add it to bestit.
	// EXAMPLE: update(exploreflight, flightlist)
	private void update(Flight exploreflight, ArrayList<Flight> flightlist){

		if(!flightlist.isEmpty()){
			for(Flight f: flightlist){
				DijkstraState beststate =  bestit.get(exploreflight.name());
				ArrayList<Flight> path = new ArrayList<Flight>(beststate.getBestpath());

				if(!hasloop(path, f)){
					path.add(f);
					DijkstraState newstate = new DijkstraState(f,itineraryDuration (path),path);
					if(!visited.contains(f.name())){
						unvisited.add(newstate);
					}			
					if(bestit.get(f.name()) == null){
						bestit.put(f.name(), newstate);
					}
					else{
						DijkstraState currentstate = bestit.remove(f.name());
						if(newstate.getBesttime() < currentstate.getBesttime()){
							bestit.put(f.name(), newstate);
						}
						else {
							bestit.put(f.name(), currentstate);
						}
					}
				} 			
			}
		}
		
	}








	////////////////////////////TEST/////////////////////////////////
	public static void main(String[] arg1){
		//		Graph g = new Graph();
		//		g.buildGraph(FlightExamples.deltaFlights);
		//		System.out.println("Size of Graph: " + g.getSize());
		//		System.out.println("Length of Graph: " + g.getLength());
		//		DijkstraGraph dg = new DijkstraGraph(Flights.make("Delta 0121","LGA", "MSP", UTCs.make(11, 0),UTCs.make(14, 9)), g);
		//		dg.getUnvisited().add(Flights.make("Delta 0122","LGA", "MSP", UTCs.make(11, 0),UTCs.make(13, 9)));
		//		dg.getUnvisited().add(Flights.make("Delta 0123","LGA", "MSP", UTCs.make(11, 0),UTCs.make(13, 8)));
		//		Flight shorterflight = dg.getUnvisited().remove();
		//		System.out.println(dg.getStart());
		//		System.out.print(shorterflight.name());
		//    	
		//    	Graph g2 = new Graph();
		//    	g2.buildGraph(FlightExamples.simpleTestFlights);
		//    	ArrayList<Flight> initiallist = g2.query("BOS");
		//    	DijkstraGraph dg2 = new DijkstraGraph(Flights.make("Delta 01","BOS", "ATL", UTCs.make(12, 10),UTCs.make(13, 10)), g2);
		//    	dg2.expand();  	
		//    	Graph g3 = new Graph();
		//    	g3.buildGraph(FlightExamples.deltaFlights);
		//    	ArrayList<Flight> initiallist = g3.query("PDX");
		//    	DijkstraGraph dg3 = new DijkstraGraph(Flights.make("Delta 2167","PDX", "MSP", UTCs.make(22, 0),UTCs.make(1, 20)), g3);
		//    	dg3.expand();
		//    	System.out.println("finish");
		UTC t1 = new GMT(11, 00);
		UTC t2 = new GMT(11, 50);
		UTC t3 = new GMT(12, 50);
		UTC t4 = new GMT(13, 50);
		Flight testf1 = new FlightType1("abc", "BOS","MSP",t1,t2);
		Flight testf2 = new FlightType1("fgh", "MSP","PDX",t3,t4);
		Flight testf3 = new FlightType1("zzz", "PDX", "BOS", t1, t3);
		Flight testf4 = new FlightType1("yyy", "PDX", "SEA", t1, t3);
		ArrayList<Flight> flist = new ArrayList<Flight>();
		flist.add(testf1); flist.add(testf2);

		Graph g2 = new Graph();
		g2.buildGraph(FlightExamples.simpleTestFlights);
		DijkstraGraph dg2 = new DijkstraGraph(Flights.make("Delta 01","BOS", "ATL", UTCs.make(12, 10),UTCs.make(13, 10)), g2);
		DijkstraState ds = new DijkstraState(dg2.getFastestItenerary("PDX"));
		ArrayList<Flight> resultlist = new ArrayList<Flight>();
		resultlist.add(Flights.make("Delta 01", "BOS", "ATL", UTCs.make(12, 10), UTCs.make(13, 10)));
		resultlist.add(Flights.make("Delta 06", "ATL", "MSP", UTCs.make(13, 15), UTCs.make(13, 50)));
		resultlist.add(Flights.make("Delta 07", "MSP", "PDX", UTCs.make(13, 50), UTCs.make(13, 55)));

		System.out.println(true == hasloop(flist, testf3));
		System.out.println(false == hasloop(flist, testf4));
		System.out.println(170 == itineraryDuration(flist));
		System.out.println(ds.getBestpath().equals(resultlist));


	}




}

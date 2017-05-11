import java.util.ArrayList;
// Constructor template for Graph: new Graph()
// Interp: size is how many flight in graph
//         length is how many airport in graph
//         nodelist is a list of Node, each airport will have a 
//         unique node.

public class Graph {
	private int size; 
	private int length;
	private ArrayList<Node> nodelist; // list of nodes

	// java Constructor
	public Graph (){
		length = 0;
		size = 0;
		nodelist = new ArrayList<Node>();
	}
	///////////////Getter and Setters///////////////////////////////
	//Return the length of graph
	public int getLength() {
		return length;
	}


	// RETURNS: an ArrayList of type Node. 
	public ArrayList<Node> getNodelist() {
		return nodelist;
	}

	// GIVEN: is a ArrayList of type Node.
	public void setNodelist(ArrayList<Node> nodelist) {
		this.nodelist = nodelist;
	}

	// RETURNS: an integer which represents size.
	// EXAMPLES: Graph g = new Graph();
	//           g.buildGraph(FlightExamples.simpleFlights);
	//	         g.getSize() => 3
	public int getSize() {
		return size;
	}




	/////////////// Functions ///////////////////////////////	
	// Flight -> *
	// GIVEN: a Flight
	// EXAMPLE: Graph g = new Graph();
	//          g.buildGraph(FlightExamples.simpleFlights);
	//          g.addFlight((Flights.make("Delta 0121", "PHL", "MSP", UTCs.make(10, 20), UTCs.make(19, 20))));
	// STRATEGY: Divide into cases to check if nodelist is empty.
	public void addFlight(Flight f){
		if(!nodelist.isEmpty()){
			boolean inserted = false;
			for(Node n : nodelist){   
				if (n.getAirport() == f.departs()){
					n.add(f); 
					size += 1;
					inserted = true;
					break;
				}
			}
			if(inserted == false){
				Node newnode = new Node(f.departs(),f);
				nodelist.add(newnode);
				size += 1;
				length += 1;
			}


		}		
		else{
			Node newnode = new Node(f.departs(),f);
			nodelist.add(newnode);
			size += 1;
			length += 1;
		}

	}


	// String -> ArrayList<Flight>
	// GIVEN: a airport name
	// RETURN: the array list of flight that start with airport
	// EXAMPLE: ArrayList list = new ArrayList<Flight>();
	//			list.add(f1);
	//			list.add(f2);
	//         graph.query("PDX") => list
	public ArrayList<Flight> query(String a){
		ArrayList<Flight> flst = new ArrayList<Flight>();
		if(!nodelist.isEmpty()){
			for(Node n : nodelist){   
				if (n.getAirport() == a){
					flst = n.getFlst(); 
					break;
				}   
			}   	
		}

		return flst;
	}


	// GIVEN:    a RacketList<Flight> which is a RacketList of type Flights. 
	// RETURNS:  a Graph.
	// EXAMPLES: Graph g = new Graph();
	//           g.buildGraph(FlightExamples.simpleFlights);
	// STRATEGY: divide into cases to check if flst is empty.
	public Graph buildGraph(RacketList<Flight> flst){
		if(flst.isEmpty()){
			return this;
		}
		else if(flst.rest() == null){
			this.addFlight(flst.first());
			return this;
		}
		else {
			this.addFlight(flst.first());
			return this.buildGraph(flst.rest());
		}
	}

	// GIVEN: a UTC
	// RETURNS:  true iff the given UTC is equal to this UTC.
	// EXAMPLES: GMT t1 = (GMT) UTCs.make(20, 10);
	//           GMT t2 = (GMT) UTCs.make(20, 10);
	//           t1.isEqual(t2) => true.
	// STRATEGY: combine simpler functions.
	public boolean isEqual (Graph g2) {
		return (size == g2.getSize()) && 
				(length == g2.getLength()) && 
				nodelist.equals(g2.getNodelist());
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	@Override
	// Object -> Boolean
	// GIVEN:    an object of type Object.
	// RETURNS:  true iff x is of type UTC, and
	// is equal to this UTC
	// EXAMPLES: UTC t1 = new GMT(11, 50);
	//           UTC t2 = new GMT(11, 50);
	//           t1.equals(t2) =>  true
	// STARTEGY: divide into cases to check if the type is UTC.	
	public boolean equals( Object x )
	{
		if (x instanceof Graph) {
			Graph g2 = (Graph) x;
			return isEqual (g2);
		}
		else 
			return false;
	}



	////////////////////////////TEST/////////////////////////////////
	public static void main(String[] arg1){
		Graph g = new Graph();
		Graph g2 = new Graph();
		Graph g3 = new Graph();
		//		g.buildGraph(FlightExamples.simpleFlights);
		//		System.out.println(g.getSize());
		//		g.addFlight((Flights.make("Delta 0121", "BOS", "MSP", UTCs.make(10, 20), UTCs.make(19, 20))));
		//		System.out.println(g.getSize());

		g.buildGraph(FlightExamples.simpleFlights);
		g2.buildGraph(FlightExamples.simpleFlights);
		g3.buildGraph(FlightExamples.simpleTestFlights);
		System.out.println(3 == g.getSize());
		System.out.println(2 == g.getLength());
		System.out.println(true == g.equals(g2));
		System.out.println(false == g.equals(g3));
	}


}

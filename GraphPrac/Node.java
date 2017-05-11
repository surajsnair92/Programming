import java.util.ArrayList;

// Constructor template for Node: new Node (a,f)
// Interpretation:
//       a is a String which represents the airport name. 
//       f is a Flight.
class Node{
	String airport;
	ArrayList<Flight> flst;
	int size;

	// Java constructor.
	public Node(String a, Flight f){
		this.airport = a;
		this.flst = new ArrayList<Flight>();
		this.flst.add(f);
		this.size = flst.size();

	}

	////////////////Getter and Setter and toString//////////////////
	@Override
	// RETURNS: a String.
	public String toString() {
		return "Node [airport=" + airport + ", flst=" + flst + ", size=" + size + "]";
	}

	// RETURNS: a string which represents the name of the airport.
	public String getAirport() {
		return airport;
	}

	// GIVEN: is a String which represents the name of the airport.
	public void setAirport(String airport) {
		this.airport = airport;
	}

	// RETURNS: an ArrayList of type Flight.
	public ArrayList<Flight> getFlst() {
		return flst;
	}

	// GIVEN: an ArrayList of type Flight
	public void setFlst(ArrayList<Flight> flst) {
		this.flst = flst;
	}

	// RETURNS: an integer which represents the size.
	public int getSize() {
		return size;
	}

	// GIVEN: an integer which represents the size.
	public void setSize(int size) {
		this.size = size;
	}


	////////////////////Functions ///////////////////
	// GIVEN: a Node
	// RETURNS:  true iff the given node is equal to this node.
	// EXAMPLES: Node n1 =  new Node("MSP", Flights.make("Delta 000", "MSP", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
	//           Node n2 = new Node("BOS", Flights.make("Delta 111", "BOS", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
	//           n1.isEqual(n2) => false.
	// STRATEGY: combine simpler functions.
	public boolean isEqual(Node n){
		if(this.airport == n.getAirport() && this.size == n.getSize() && 
				this.flst.equals(n.getFlst())){
			return true;
		}
		else
			return false;
	}


	@Override
	// Object -> Boolean
	// GIVEN:    an object of type Object.
	// RETURNS:  true iff x is of type Node, and
	// is equal to this Node
	// EXAMPLES: Node n1 = new Node("MSP", Flights.make("Delta 000", "MSP", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
	//           Node n2 = new Node("BOS", Flights.make("Delta 111", "BOS", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
	//           n1.equals(n2) =>  false
	// STARTEGY: divide into cases to check if the type is UTC.	
	public boolean equals( Object x )
	{
		if (x instanceof Node) {
			Node n = (Node) x;
			return isEqual (n);
		}
		else 
			return false;
	}

	// Flight -> *
	// GIVEN: a Flight.
	// add a flight to flight list in this object
	// EXAMPLE:��n1. add(Flights.make("Delta 0121", "LGA", "MSP", UTCs.make(10, 20), UTCs.make(19, 20)))
	public void add(Flight f){
		this.flst.add(f);
		this.size += 1;
	}
	////////////////////////TEST/////////////////////////		
	public static void main(String[] arg1){
		Node n1 = new Node("MSP", Flights.make("Delta 000", "MSP", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
		Node n2 = new Node("BOS", Flights.make("Delta 111", "BOS", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
		n1.add(Flights.make("Delta 0121", "LGA", "MSP", UTCs.make(10, 20), UTCs.make(19, 20)));
		Node n3 = new Node("MSP", Flights.make("Delta 000", "MSP", "SFO", UTCs.make(10, 20), UTCs.make(19, 20)));
		n3.setAirport("MSP");
		n3.setSize(2);
		ArrayList<Flight> list = new ArrayList(n1.getFlst());
		n3.setFlst(list);

		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(true == n1.equals(n3));
		System.out.println(false == n1.equals(n2));
	}
}
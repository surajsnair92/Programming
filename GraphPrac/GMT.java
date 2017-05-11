// Constructor template for GMT: new GMT (h, m)
// Interpretation:
//     h is the UTC hour (between 0 and 23, inclusive)
//     m is the UTC minute (between 0 and 59, inclusive)
// Representation:
//     Internally, the hour is represented in Eastern Standard Time,
//     which is five hours behind UTC.

class GMT implements UTC {

	int h;    // the hour, limited to [0,23]
	int m;    // the minute, limited to [0,59]
	final int minutesInADay = 1440;

	// the Java constructor
	GMT (int h, int m) {
		this.h = h;
		this.m = m;
	}

	// RETURNS: the hour, between 0 and 23 inclusive.
	public int hour () { return h; }

	// RETURNS: the minute, between 0 and 59 inclusive.
	public int minute () { return m; }

	// UTC -> Boolean
	// GIVEN: a UTC
	// RETURNS:  true iff the given UTC is equal to this UTC.
	// EXAMPLES: GMT t1 = (GMT) UTCs.make(20, 10);
	//           GMT t2 = (GMT) UTCs.make(20, 10);
	//           t1.isEqual(t2) => true.
	// STRATEGY: combine simpler functions.
	public boolean isEqual (UTC t2) {
		return (h == t2.hour()) && (m == t2.minute());
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
		if (x instanceof UTC) {
			UTC t2 = (UTC) x;
			return isEqual (t2);
		}
		else 
			return false;
	}

	///////////////////TEST ///////////////////////////
	public static void main(String...args){
		GMT t1 = (GMT) UTCs.make(20, 10);
		GMT t2 = (GMT) UTCs.make(20, 10);
		GMT t3 = (GMT) UTCs.make(15, 10);

		System.out.println(t1.h == 20);
		System.out.println(t1.m == 10);
		System.out.println(true == t1.equals(t1));
		System.out.println(true == t1.equals(t2));
		System.out.println(false == t1.equals(t3));
		System.out.println(false == t1.equals("mm"));



	}

}
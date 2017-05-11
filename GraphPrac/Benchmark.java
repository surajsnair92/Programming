import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

public class Benchmark {


	//ArrayList<String> updatel2 = new ArrayList<String>();
	//    
	//    ;;; all-airports-loop : ListOfString ListOfString -> ListOfString
	//    ;;; GIVEN: two lists of strings
	//    ;;; WHERE: the second list is a set (no element occurs twice)
	//    ;;; RETURNS: the set union of the given lists
	//    
	//    (define (all-airports-loop names airports)
	//      (if (empty? names)
	//          airports
	//          (all-airports-loop (rest names)
	//                             (if (member (first names) airports)
	//                                 airports
	//                                 (cons (first names) airports)))))
	//    

	//    ;;; make-stress-test0 : NonNegInt -> ListOfFlight
	//    ;;; GIVEN: a non-negative integer n
	//    ;;; RETURNS: a list of 2n flights connecting n+1 airports
	//    
	//    (define (make-stress-test0 n)
	//      (if (= n 0)
	//          empty
	//          (let* ((name1 (string-append "NoWays " (number->string (+ n n))))
	//                 (name2 (string-append "NoWays " (number->string (+ n n 1))))
	//                 (ap1 (string-append "AP" (number->string n)))
	//                 (ap2 (string-append "AP" (number->string (+ n 1))))
	//                 (t1 (make-UTC (remainder (* 107 n) 24)
	//                               (remainder (* 223 n) 60)))
	//                 (t2 (make-UTC (remainder (* 151 n) 24)
	//                               (remainder (* 197 n) 60)))
	//                 (t3 (make-UTC (remainder (* 163 n) 24)
	//                               (remainder (* 201 n) 60)))
	//                 (t4 (make-UTC (remainder (* 295 n) 24)
	//                               (remainder (* 183 n) 60)))
	//                 (f1 (make-flight name1 ap1 ap2 t1 t2))
	//                 (f2 (make-flight name2 ap1 ap2 t3 t4)))
	//            (cons f1 (cons f2 (make-stress-test0 (- n 1)))))))

	public ArrayList<Flight> makeStressTest0 (int n){

		if(n == 0){

			//	System.out.println(flt3.size());
			return new ArrayList<Flight>();
		}
		else{
			String name1 = "NoWays" +(n+n);
			String name2 = "NoWays" +(n+n+1);
			String ap1 = "AP"+n;
			String ap2 = "AP"+(n+1);
			UTC t1 =  UTCs.make((107*n)%24, (223*n)%60);
			UTC t2 =  UTCs.make((151*n)%24, (197*n)%60);
			UTC t3 =  UTCs.make((163*n)%24, (201*n)%60);
			UTC t4 =  UTCs.make((295*n)%24, (183*n)%60);
			Flight f1 = Flights.make(name1, ap1, ap2, t1, t2);
			Flight f2=  Flights.make(name1, ap1, ap2, t3, t4);
			ArrayList<Flight> flt1 = new ArrayList<Flight>();
			ArrayList<Flight> flt2 = new ArrayList<Flight>();
			ArrayList<Flight> flt3 = new ArrayList<Flight>();
			ArrayList<Flight> flt4 = new ArrayList<Flight>();
			flt1.add(f1);
			flt2.add(f2);
			flt3.addAll(flt1);
			flt3.addAll(flt2);
			flt3.addAll(makeStressTest0(n-1));
			return flt3;
		}

	}
	//    

	public ArrayList<String> airport(ArrayList<Flight> flt){
		ArrayList<String> depAirport = new ArrayList<String>();
		ArrayList<String> arrAirport = new ArrayList<String>();
		ArrayList<String> totalList = new ArrayList<String>();
		ArrayList<String> output = new ArrayList<>();
		for (Flight flight : flt) {
			depAirport.add(flight.departs());
			arrAirport.add(flight.arrives());
		}
		totalList.addAll(depAirport);
		totalList.addAll(arrAirport);
		output = allAiportLoop(totalList, new ArrayList<String>());
		return output;
	}
	//  (define (all-airports-loop names airports)
	//  (if (empty? names)
	//      airports
	//      (all-airports-loop (rest names)
	//                         (if (member (first names) airports)
	//                             airports
	//                             (cons (first names) airports)))))
	public ArrayList<String> rest(ArrayList<String> lst){
		ArrayList<String> restList = new ArrayList<String>();

		for (int i =0; i<lst.size();i++){
			if(i == 0){
				continue;
			}
			else{
				restList.add(lst.get(i));
			}
		}
		//	System.out.println(restList);
		return restList;
	}
	public String first(ArrayList<String> lst){
		String f;
		f = lst.get(0);
		return f;
	}
	public ArrayList<String> allAiportLoop (ArrayList<String> l1, ArrayList<String> l2){
		//ArrayList<String> updatel2 = new ArrayList<String>();
		if(l1.size() == 0){
			return l2;
		}
		else{
			if(l2.contains(first(l1))){
				//do nothing
			}
			else{
				l2.add(first(l1));
				//return l2;
			}
			allAiportLoop(rest(l1),l2);
			return l2;
		}

	}
	private static Flight flt (String s, String ap1, String ap2,
			int t1, int t2) {
		UTC lv = UTCs.make (t1 / 100, t1 % 100);
		UTC ar = UTCs.make (t2 / 100, t2 % 100);
		return Flights.make (s, ap1, ap2, lv, ar);
	}
	private static RacketList<Flight> simpleFlights() {
		RacketList<Flight> fs00 = RacketLists.empty();
		RacketList<Flight> fs01
		= fs00.cons (flt ("Delta 0105", "BOS", "ATL", 1950, 2259));
		RacketList<Flight> fs02
		= fs01.cons (flt ("Delta 1895", "BOS", "SLC", 1505, 1705));
		RacketList<Flight> fs03
		= fs02.cons (flt ("Delta 0926", "PHL", "SLC", 1059, 1615));

		return fs03;
	}
	//  ;;; visit-all-pairs-of-airports :
	//  ;;;     (String String ListOfFlight -> X) ListOfFlight -> ListOfX
	//  ;;; GIVEN: a function whose arguments are suitable for can-get-there?
	//  ;;; RETURNS: a list of the results obtained by calling that function
	//  ;;;     on all pairs of airports served by the given flights,
	//  ;;;     passing the given list of flights as a third argument.
	//  
	//  (define (visit-all-pairs-of-airports visitor flights)
	//    (let ((airports (all-airports flights)))
	//      (apply append
	//             (map (lambda (ap1)
	//                    (map (lambda (ap2)
	//                           (visitor ap1 ap2 flights))
	//                         airports))
	//                  airports))))
	//  
	public void visitAllPairsOfAirport(MyList visitor, ArrayList<Flight> flt){
		ArrayList<String> airports = airport(flt);
		ArrayList<String> ap2list;
		Schedules sch = new Schedules();
		for (String s : airports) {

		}

	}
	public MyList visitor(String src, String dest, ArrayList<Flight> alf){
		Schedules s1 = new Schedules();
		if(s1.canGetThere(src, dest, simpleFlights())){
			return MyList.make(src, dest, s1.travelTime(src, dest, simpleFlights()));
		}
		else
			return MyList.make(src, dest, -1);
	}

	public void benchmark(int n,String src, String dest){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Schedules s1 = new Schedules();
		flights = makeStressTest0(n);
		long startTime = System.currentTimeMillis();
		if(s1.canGetThere(src,dest, simpleFlights())){
			//visitAllPairsOfAirport(visitor(src, dest, flights), flights);
			s1.fastestItinerary(src, dest, simpleFlights());
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}
	public static void main(String[] args) {
		Benchmark b = new Benchmark();
		ArrayList<Flight> a1 = new ArrayList<Flight>();
		ArrayList<Flight> a2 = new ArrayList<Flight>();
		ArrayList<String> s1 = new ArrayList<String>();
		a1.add(flt ("Delta 0105", "BOS", "ATL", 1950, 2259));
		a1.add(flt ("Delta 1895", "BOS", "SLC", 1505, 1705));
		a1.add(flt ("Delta 0926", "PHL", "SLC", 1059, 1615));
		s1 = b.airport(a1);
		for (String string : s1) {
			//	System.out.println(string);
		}
		a2 = b.makeStressTest0(20);
		//System.out.println(a2.size());
		b.benchmark(30, "BOS","PDX");
	}

}
class MyList{
	String a;
	String b;
	int t;
	MyList(String a, String b, int t){
		this.a =a;
		this.b = b;
		this.t=t;
	}
	public static MyList make (String a, String b, int t) {
		return new MyList(a, b, t);
	}
}

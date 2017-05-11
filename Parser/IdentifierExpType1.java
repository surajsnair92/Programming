import java.util.HashMap;
import java.util.Map;

public class IdentifierExpType1 extends ExpType1 implements IdentifierExp {

	public String name; 

	// Constructor Template: new IdentifierExpType1(String)
	//  Interpretation:
	//			 id represents the name which is an identifier.
	IdentifierExpType1(String id){
		name = id;
	}

	@Override
	// Returns the name of this identifier.
	public String name() {
		return name;
	}

	public boolean isIdentifier(){
		return true;
	}
	 public IdentifierExp asIdentifier() {
		 return this;
	 }


	// Map<String,ExpVal> env -> ExpVal
	// GIVEN:   a map with key of String type and value of type ExpVal.
	// RETURNS: ExpVal from the value of the map by name.
	public ExpVal value(Map<String,ExpVal> env){
		return env.get(name);
	}
	////////////////////////////TEST//////////////////////////////	
	public static void main(String[] arg1){
		ExpVal v1 = Asts.expVal(30);
		Map<String,ExpVal> env = new HashMap<String,ExpVal>();
		env.put("n", v1);

		IdentifierExp id1 = Asts.identifierExp("n");
		System.out.println(id1.asLambda());

		//		System.out.println(30==id1.value(env).asInteger());
		//		
		//		System.out.println(true == id1.isExp());
		//		System.out.println(false == id1.isArithmetic());
		//		System.out.println(true == id1.isIdentifier());
		//		System.out.println(false == id1.isDef());
		//		System.out.println(false == id1.isPgm());

	}
}

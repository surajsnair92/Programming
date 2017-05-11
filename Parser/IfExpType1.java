import java.util.HashMap;
import java.util.Map;

public class IfExpType1 extends ExpType1 implements IfExp {
	Exp testpart;
	Exp thenpart;
	Exp elsepart;
//	Exp expr;

	// Constructor Template: new IfExpType1(Exp, Exp, Exp)
	//  Interpretation:
	//			 test represents the test for if clause.
	//           thenpart represents expression to be executed if testpart is true.
	//           else represents expression to be executed if testpart is false.
	IfExpType1(Exp test, Exp then, Exp e){
		testpart = test;
		thenpart = then;
		elsepart = e;
	}
//	IfExpType1(Exp b){
//		expr = b;
//	}

	@Override
	// RETURNS: testpart of the expression.
	public Exp testPart() {
		return testpart;
	}

	@Override
	// RETURNS: thenpart of the expression.
	public Exp thenPart() {
		return thenpart;
	}

	@Override
	// RETURNS: elsepart of the expression.
	public Exp elsePart() {
		return elsepart;
	}
	
	public boolean isIf(){
		return true;
	}
	public IfExp asIf() {
		return this;
	}
	
	// Map<String,ExpVal> -> ExpVal
	// GIVEN:   a map with key of String type and value of type ExpVal.
	// RETURNS: ExpVal from the value of testPart
	// STRATEGY: Divided by cases if testpart returns true or false.
	public ExpVal value(Map<String,ExpVal> env){
		if(testpart.value(env).asBoolean() == true){
			return thenpart.value(env);
		}
		else{
			return elsepart.value(env);
		}
	}
	//////////////////////////////TEST///////////////////////////////
	public static void main(String[] arg1){
		//if n == 0, then n + 1, else n-1
		//test (test) if test = 0 then test(1) else 5 fi
		IdentifierExp idex1 = Asts.identifierExp("n");
		ConstantExp constant1 = Asts.constantExp(Asts.expVal(0));
		ConstantExp constant2 = Asts.constantExp(Asts.expVal(1));
		ArithmeticExp testPart = Asts.arithmeticExp(idex1, "EQ", constant1);
		ArithmeticExp thenPart = Asts.arithmeticExp(idex1, "PLUS", constant2);
		ArithmeticExp elsePart = Asts.arithmeticExp(idex1, "MINUS", constant2);

		ExpVal v1 = Asts.expVal(30);
		ExpVal v2 = Asts.expVal(0);
		Map<String,ExpVal> env1 = new HashMap<String,ExpVal>();
		env1.put("n", v1);
		Map<String,ExpVal> env2 = new HashMap<String,ExpVal>();
		env2.put("n", v2);

		IfExp ifexp = Asts.ifExp(testPart, thenPart, elsePart);
		System.out.println(29 == ifexp.value(env1).asInteger());
		System.out.println(1 == ifexp.value(env2).asInteger());

	}


}

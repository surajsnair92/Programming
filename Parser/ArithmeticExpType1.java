import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArithmeticExpType1 extends ExpType1 implements ArithmeticExp {
	
	public boolean isArithmetic(){
		return true;
	}
	public ArithmeticExp asArithmetic() {
		return this;
	}
	

	// Java Enum.
	// NOTE: The below are the only allowed operations. 
	
	enum operatorlist {
		LT, EQ, GT, PLUS, MINUS, TIMES, LEQ
	}
	private String operator;
	private Exp leftexp;
	private Exp rightexp;
	

	// Constructor Template: new ArithmeticExpType1(Exp, String, Exp)
	//  Interpretation:
	//			 l is of type Exp which represents left of an expression.
	//			 op represents the operator as String.  
	//			 r is of type Exp which represents right of an expression.
	ArithmeticExpType1(Exp l, String op, Exp r){
		leftexp = l;
		rightexp = r;
		try{
			if(contains(op))
				operator = op;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


	@Override
	// RETURNS: expression that is left to the operator.
	public Exp leftOperand() {
		return leftexp;
	}

	@Override
	// RETURNS: expression that is right to the operator.
	public Exp rightOperand() {
		return rightexp;
	}

	@Override
	// RETURNS: the operator 
	public String operation() {
		return operator;
	}

	// String -> Boolean
	// GIVEN: a string which represents the operator.
	// RETURNS: true iff the operator is present in the enum.
	// EXAMPLE: ArithmeticExpType1.contains("PLUS") => true.
	//          ArithmeticExpType1.contains("DIVIDE") => false.
	// STRATEGY: combine simpler functions. 
	private static boolean contains(String test) {
		for (operatorlist c : operatorlist.values()) {
			if (c.name().equals(test)) 
				return true;
		}
		return false;
	}



	@Override
	// Map<String, ExpVal> -> ExpVal
	// GIVEN:    a map with key as a String and Value of type ExpVal
	// RETURNS:  an ExpVal with a suitable result.
	// EXAMPLE:  ConstantExp constant1 = Asts.constantExp(new ExpValType1(0));
	//           ConstantExp constant2 = Asts.constantExp(new ExpValType1(1)); 
	//           ArithmeticExp aexp = Asts.arithmeticExp(constant1, "EQ", constant2)
	//           aexp.value(env).asBoolean() => false
	// STRATEGY: divided into cases.
	public ExpVal value (Map<String, ExpVal> env){
		//environment = env; 
		//System.out.println(environment.equals(env));
		long leftVal = this.leftexp.value(env).asInteger();
		long rightVal = this.rightexp.value(env).asInteger();
		switch(operator){
		case "LT":
			return getLTValue(leftVal,rightVal);
		case "EQ":
			return getEQValue(leftVal,rightVal);
		case "GT":
			return getGTValue(leftVal,rightVal);
		case "PLUS":
			return getPlusValue(leftVal,rightVal);
		case "MINUS":
			return getMinusValue(leftVal,rightVal);
		case "TIMES":
			return getTimesValue(leftVal,rightVal);
		case "LEQ":
			return getLEQ(leftVal,rightVal);
		default:
			try{
				return getTimesValue(leftVal,rightVal);
			}
			catch(Exception e){
				e.printStackTrace();
				return getTimesValue(leftVal,rightVal);
			}
		}
	}
	
	
	// Long Long -> ExpVal
	// GIVEN: two long type values;
	
	//RETURNS: ExpVal with the computed result.
	private ExpVal getTimesValue(long leftVal, long rightVal) {
		return Asts.expVal(leftVal*rightVal);

	}

	//RETURNS: ExpVal with the computed result
	private ExpVal getMinusValue(long leftVal, long rightVal) {
		
		return Asts.expVal(leftVal-rightVal);
	}
private ExpVal getLEQ(long leftVal, long rightVal) {
		
		return Asts.expVal(leftVal<=rightVal);
	}
	//RETURNS: ExpVal with the computed result
	private ExpVal getPlusValue(long leftVal,long rightVal) {
//		long leftVal = leftexp.value(environment).asInteger();
//		System.out.println(leftVal);
//		long rightVal = rightexp.value(environment).asInteger();
//		System.out.println(+rightVal);
		long total = leftVal + rightVal;
		return Asts.expVal(total);
	}
	
	//RETURNS: ExpVal with the computed result
	private ExpVal getGTValue(long leftVal, long rightVal) {
		return Asts.expVal(leftVal > rightVal);
	}
	
	//RETURNS: ExpVal with the computed result
	private ExpVal getEQValue(long leftVal, long rightVal) {
		return Asts.expVal(leftVal == rightVal);
	}
	
	//RETURNS: ExpVal with the computed result
	private ExpVal getLTValue(long leftVal, long rightVal) {
		return Asts.expVal( leftVal < rightVal);
	}

	////////////////////TEST///////////////////////////////////////
	public static void main(String...arg){
		ConstantExp constant1 = Asts.constantExp(Asts.expVal(10));
		ConstantExp constant2 = Asts.constantExp(Asts.expVal(1));
		Map<String,ExpVal> env = new HashMap<String,ExpVal>();
		ArithmeticExp aexp = Asts.arithmeticExp(constant1, "EQ", constant2);
		ArithmeticExp aexp2 = Asts.arithmeticExp(constant1, "EQ", constant1);
		ArithmeticExp aexp3 = Asts.arithmeticExp(constant1, "PLUS", constant2);
		ArithmeticExp aexp4 = Asts.arithmeticExp(constant1, "MINUS", constant2);
		ArithmeticExp aexp5 = Asts.arithmeticExp(constant1, "TIMES", constant2);
		ArithmeticExp aexp6 = Asts.arithmeticExp(constant1, "LT", constant2);
		ArithmeticExp aexp7 = Asts.arithmeticExp(constant1, "GT", constant2);
		System.out.println(false == aexp.value(env).asBoolean());
		System.out.println(true == aexp2.value(env).asBoolean());
		System.out.println(1 == aexp3.value(env).asInteger());
		System.out.println(-1 == aexp4.value(env).asInteger());
		System.out.println(0 == aexp5.value(env).asInteger());
		System.out.println(true == aexp6.value(env).asBoolean());
		System.out.println(false == aexp7.value(env).asBoolean());
		System.out.println(true == ArithmeticExpType1.contains("PLUS"));
	}
}

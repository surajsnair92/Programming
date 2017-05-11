import java.util.Map;

public class ExpValType1 implements ExpVal {
//	static final long NO_VALUE = (long)Integer.MAX_VALUE;

	boolean isBoolean;
	boolean isLong;
	boolean boolvalue;
	long numberValue;
	
	// Constructor Template: new ExpValType1(long)
	//  Interpretation:
	//	         n represents the value of constant expression if integer is passed.
	public ExpValType1(){
		isLong = false;
		isBoolean = false;
	}
	public ExpValType1(long n){
		
		isLong = true;
		isBoolean = false;
		numberValue = n;
		
	}
	// Constructor Template: new ExpValType1(boolean)
	//  Interpretation:
	//			 b represents the value of constant expression if boolean constant is passed.
	public ExpValType1(boolean b){
		
		boolvalue = b;
		isLong = false;
		isBoolean = true;
	}



	@Override
	// Returns true iff boolean is passed as expression.
	public boolean isBoolean() {
		return isBoolean;
	}

	@Override
	// RETURNS: true iff integer is passed as expression.
	public boolean isInteger() {
		return isLong;
	}

	@Override
	// RETURNS: true iff function is passed as an expression.
	public boolean isFunction() {
		return false;
	}

	@Override
	// RETURNS: the value boolvalue which is of type Boolean.
	public boolean asBoolean() {
		return boolvalue;
	}

	@Override
	// RETURNS: the value numbervalue which is of type Integer.
	public long asInteger() {
		return numberValue;
	}

	@Override
	// RETURNS: the value funvalue which is of type FunValType1.
	public FunVal asFunction() {
		return null;
	}

	////////////////////////////TEST/////////////////
	public static void main(String[] arg1){
		//ExpValType1 emptyv = new ExpValType1();
		ExpValType1 v1 = new ExpValType1(20);
		ExpValType1 v2 = new ExpValType1(true);
		//		ExpValType1 v3 = new ExpValType1(new FunValType1());
		System.out.println(true == v1.isInteger());
		System.out.println(false == v1.isFunction());
		System.out.println(false == v1.isBoolean());
		System.out.println(20 == v1.asInteger());

		System.out.println(false == v2.isInteger());
		System.out.println(false == v2.isFunction());
		System.out.println(true == v2.isBoolean());
		System.out.println(true == v2.asBoolean());
		
//		System.out.println(false == emptyv.isBoolean());
//		System.out.println(false == emptyv.isInteger());
//		System.out.println(false == emptyv.isFunction());



	}

}
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConstantExpType1 extends ExpType1 implements ConstantExp{

	ExpVal value;

	// Constructor Template: new ConstantExpType1(ExpVal)
	//  Interpretation:
	//			 v represents the value.
	public ConstantExpType1(ExpVal v){
		value = v;		
	}
	
	public boolean isConstant(){
		return true;
	}
	public ConstantExp asConstant() {
		return this;
	}

	@Override
	// RETURNS: ExpVal which represents the value. 
	public ExpVal value() {
		return value;
	}

	// Map<String,ExpVal> -> ExpVal
	// GIVEN:   a map with key of type String and value of type ExpVal.
	// RETURNS: value. 
	public ExpVal value(Map<String, ExpVal> env){
		return value;
	}

	///////////////////////////TEST///////////////////////////////////////////////	
	public static void main(String[] arg1){
		Exp cexp = Asts.constantExp(Asts.expVal(10));
		System.out.println(10 == cexp.value(new HashMap()).asInteger());
	}
}
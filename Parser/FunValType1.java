import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunValType1 extends ExpValType1 implements FunVal{
	LambdaExp code;
	Map<String, ExpVal> environment;

	// Constructor Template: new FunValType1(LambdaExp, Map<String, ExpVal>)
	//  Interpretation:
	//			 exp represents the body of lambda expression
	//           env represents the key-value relationship of string and expression.
	public FunValType1(LambdaExp exp, Map<String, ExpVal> env) {
		super();
		code = exp;
		environment = env;	
	}
	

	@Override
	// Returns the lambda expression from which this function was created.
	public LambdaExp code() {
		return code;
	}

	@Override
	// Returns the environment that maps the free variables of that
	// lambda expression to their values.
	public Map<String, ExpVal> environment() {
		return environment;
	}

	public boolean isFunction() {
		return true;
	}
	
	public FunVal asFunction () {
		return this;
	}


	////////////////////////////TEST/////////////////
	public static void main(String[] arg1){
		List<String> formallist = new ArrayList<String>();
		formallist.add("n");
		IdentifierExp id1 = Asts.identifierExp("n");
		ConstantExp c1 = Asts.constantExp(Asts.expVal(25));
		//ConstantExp c1 = new ConstantExpType1(new ExpValType1(50));
		ArithmeticExp aexp1 = Asts.arithmeticExp(id1, "PLUS", c1);
		LambdaExp lambdaexp = Asts.lambdaExp(formallist, aexp1);
		Map<String,ExpVal> env = new HashMap<String,ExpVal>();
		env.put("n", Asts.expVal(11));
		System.out.println(true == lambdaexp.value(env).isFunction());
		System.out.println(false == lambdaexp.value(env).isBoolean());
		System.out.println(false == lambdaexp.value(env).isInteger());

		System.out.println(36==lambdaexp.value(env).asInteger());
	}
}

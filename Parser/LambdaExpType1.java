import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class LambdaExpType1 extends ExpType1 implements LambdaExp {
	List<String> formals;
	Exp body;

	// Constructor Template: new LambdaExpType1(List<String>, Exp)
	//  Interpretation:
	//			 f represents the list of strings which is the formal parameter.
	//           b represents the body of the lambda expression.
	LambdaExpType1(List<String> f, Exp b){
		formals = f;
		body = b;
	}

	@Override
	// Returns the formal parameters of this lambda expression.
	public List<String> formals() {
		return formals;
	}

	@Override
	// Returns the body of this lambda expression.
	public Exp body() {
		return body;
	}

	public boolean isLambda(){
		return true;
	}
	public LambdaExp asLambda() {
		return this;
	}
	// (Map<String,ExpVal> -> ExpVal
	// GIVEN:   a map with key of String type and value of type ExpVal.
	// RETURNS: ExpVal which will return the function result.
	// STRATEGY: combine simpler functions.
	public ExpVal value(Map<String,ExpVal> env){
		//		Map<String,ExpVal> newenv = new HashMap<String, ExpVal>();
		//		newenv.putAll(env);

		return Asts.expVal(this, env);
	}

	////////////////////////////TEST////////////////////////////
	public static void main(String[] arg1){
		// lambda(x)(x + 1)
		//(lamda (m) m + ((lamda (k) k * k) (7))) (1) = 50


//		ArrayList<String> forParam = new ArrayList<String>();
//		forParam.add("k");
//		//ConstantExp cons = Asts.constantExp(Asts.ExpVal(7));
//		//ConstantExp cons1 = Asts.constantExp(Asts.ExpVal(1));
//		IdentifierExp id1 = Asts.identifierExp("k");
//		IdentifierExp id2 = Asts.identifierExp("m");
//		ArithmeticExp aexp = Asts.arithmeticExp(id1, "TIMES", id1);
//
//		Map<String,ExpVal> env = new HashMap<String,ExpVal>();
//		env.put("k",Asts.ExpVal(7));
//		env.put("m",Asts.ExpVal(1));
//		LambdaExp lamdaFunc = Asts.lambdaExp(forParam, aexp);
//		ArithmeticExp aexp1 = Asts.arithmeticExp(id2, "PLUS", lamdaFunc.body());
//		LambdaExp lamdaFunc1 = Asts.lambdaExp(forParam, aexp1);
//		System.out.println(50 == lamdaFunc1.value(env).asFunction().asInteger());

//		Exp exp1
//		= Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"MINUS",
//				Asts.constantExp (Asts.expVal (1)));
//
//		Exp call1
//		= Asts.callExp (Asts.identifierExp ("fact"),
//				Asts.list (exp1));
//		Exp testPart
//		= Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"EQ",
//				Asts.constantExp (Asts.expVal (0)));
//		Exp thenPart
//		= Asts.constantExp (Asts.expVal (1));
//		Exp elsePart
//		= Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"TIMES",
//				call1);
//		Def def1
//		= Asts.def ("fact",
//				Asts.lambdaExp (Asts.list ("n"),
//						Asts.ifExp (testPart,
//								thenPart,
//								elsePart)));
//
//		ExpVal result = Programs.run (Asts.list (def1),
//				Asts.list (Asts.expVal (5)));
//		System.out.println (result.asInteger());




	}
}

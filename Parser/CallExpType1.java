import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallExpType1 extends ExpType1 implements CallExp {
	private Exp operator;
	private List<Exp> arguments;


	// Constructor Template: new CallExpType1(Exp, List<Exp>)
	//  Interpretation:
	//			 op represents the operator as Exp.
	//			 op could be any exp that evaluates to FunVal
	//           ar represents the List of expressions.
	CallExpType1(Exp op, List<Exp> ar){
		operator = op;
		arguments = ar;
	}

	@Override
	// RETURNS: operator of type Exp
	public Exp operator() {
		return operator;
	}

	@Override
	// RETURNS: List<Exp> which represent the arguments.
	public List<Exp> arguments() {
		// TODO Auto-generated method stub
		return arguments;
	}

	public boolean isCall(){
		return true;
	}
	public CallExp asCall() {
		return this;
	}
	// Map<String, ExpVal> -> ExpVal
	// GIVEN:    a map with key as a String and Value of type ExpVal
	// RETURNS:  an ExpVal with a suitable result.
	// STRATEGY: divided into cases.
	public ExpVal value(Map<String, ExpVal> env) {

		ExpVal v0 = operator.value(env);           //line 1
		List<ExpVal> resultlist = new ArrayList<ExpVal>(); //line 2
		for(Exp e: arguments){
			resultlist.add(e.value(env));
		}

		Map<String,ExpVal> newenv = new HashMap<String, ExpVal>(env); //line3
		
		newenv.putAll(v0.asFunction().environment());
		
		for(int i = 0; i < resultlist.size(); i++){
			String name = v0.asFunction().code().formals().get(i);
			ExpVal v = resultlist.get(i);
			newenv.put(name, v);
		}

		return v0.asFunction().code().body().value(newenv); //line 4
	}
	///////////////////////TEST/////////////////////////////////////
	public static void main(String[] arg1){

		// Example on instruction
		ArithmeticExp exp1
		= Asts.arithmeticExp (Asts.identifierExp ("n"),
				"MINUS",
				Asts.constantExp (Asts.expVal (1)));

		CallExp call1
		= Asts.callExp (Asts.identifierExp ("fact"),
				Asts.list (exp1));
		ArithmeticExp testPart
		= Asts.arithmeticExp (Asts.identifierExp ("n"),
				"EQ",
				Asts.constantExp (Asts.expVal (0)));

		ConstantExp thenPart
		= Asts.constantExp (Asts.expVal (1));
		ArithmeticExp elsePart
		= Asts.arithmeticExp (Asts.identifierExp ("n"),
				"TIMES",
				call1);

		Def def1
		= Asts.def ("fact",
				Asts.lambdaExp (Asts.list ("n"),
						Asts.ifExp (testPart,
								thenPart,
								elsePart)));

		Def def2
		= Asts.def ("main",
				Asts.lambdaExp (Asts.list ("n"),
						call1));


		//		Map<String,ExpVal> env = new HashMap<String, ExpVal>();
		//		env.put("n", new ExpValType1(5));
		//		env.put("fact", def1.rhs().value(env));
		//		System.out.println(def1.rhs().value(env).asInteger());
		Programs.run(Asts.list(def2,def1), Asts.list(Asts.expVal(5)));




	}


}

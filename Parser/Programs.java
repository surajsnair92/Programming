
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Programs extends AstType1 {
	
	// List<Def> List<ExpVal> -> ExpVal
	// GIVEN: list of definitions and list of inputs
	// RETURNS: ExpVal
	// WHERE: the entry point is always lod.get(0)
	// STRATEGY: break into cases based on type of Entry point. 
	public static ExpVal run (List<Def> lod, List<ExpVal> inputs) {
		try{
			Program program = new ProgramType1(lod, inputs);
			Map<String, ExpVal> env = new HashMap<String, ExpVal>();
			for(Def f: lod){
				env.put(f.lhs(), f.rhs().value(env));
			}
			// lambda entry point
			if(program.getDefinitionlist().get(0).rhs().isLambda()){
				List<String> formallst = program.getDefinitionlist().get(0).rhs().asLambda().formals();
				for(int i = 0; i < formallst.size(); i++){
					env.put(formallst.get(i), inputs.get(i));
				}
				return program.getDefinitionlist().get(0).rhs().asLambda().body().value(env);
			} 

			// constant entry point
			else{
				return program.getDefinitionlist().get(0).rhs().asConstant().value(env);
			}

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	// String -> Set
	// GIVEN: a file path, the file contain a program
	// RETURN: the free veriables of that program
	// Reads the ps11 program found in the file named by the given string
	// and returns the set of all variable names that occur free within
	// the program.
	//
	// Examples:
	//     Programs.undefined ("church.ps11")    // returns an empty set
	//     Programs.undefined ("bad.ps11")       // returns { "x", "z" }
	//
	//   where bad.ps11 is a file containing:
	// 
	//     f (x, y) g (x, y) (y, z);
	//     g (z, y) if 3 > 4 then x else f

	public static Set<String> undefined (String filename) { 
		List<Def> deflst = null;
		deflst = Scanner.parsePgm(Scanner.readPgm(filename));
		
		//empty set for result
		Set<String> resultSet = new HashSet<String>();

		List<String> env = new ArrayList<String>();
		for(Def d: deflst){
			env.add(d.lhs());
		}
		for(Def d: deflst){
			List<String> envclone = new ArrayList<String>(env);
			resultSet = union(resultSet, getUndefined(d.rhs(), new HashSet<String>(), envclone));
		}
		System.out.println(resultSet);
		return resultSet;
	}



	// Runs the ps11 program found in the file named on the command line
	// on the integer inputs that follow its name on the command line,
	// printing the result computed by the program.
	//
	// Example:
	//
	//     % java Programs sieve.ps11 2 100
	//     25

	//    public static void main (String[] args) { 
	//    	String path = args[0];
	//    	List<ExpVal> parameterlist = new ArrayList<ExpVal>();
	//    	for(int i = 1; i< args.length; i++){
	//    		int p =Integer.parseInt(args[i]);
	//    		parameterlist.add(Asts.expVal(p));
	//    	}
	//    	ExpVal result = Programs.run(Scanner.parsePgm(Scanner.readPgm(path)), initialParameters(parameterlist));
	//    	
	//    	if(result.isInteger()){
	//    		System.out.println(result.asInteger());
	//    	}else
	//    		System.out.println(result.asBoolean());
	//    }
	public static void main (String[] args) {
		String path = args[0];
		List<ExpVal> parameterlist = new ArrayList<ExpVal>();
		for(int i = 1; i< args.length; i++){
			int p =Integer.parseInt(args[i]);
			parameterlist.add(Asts.expVal(p));
		}
		ExpVal result = Programs.run(Scanner.parsePgm(Scanner.readPgm(path)), initialParameters(parameterlist));

		if(result.isInteger())
			System.out.println(result.asInteger());
		else
			System.out.println(result.asBoolean());

//		undefined("bad.ps11");
//		undefined("sieve.ps11");
	}
	
	///////////////////////HELP FUNCTIONS//////////////////////////////////////
	//Set Set -> Set
	//GIVEN: two sets
	//RETURN: the union set of given two sets
	//EXAMPE: Set<String> s1 = new HashSet<String>();
	//		Set<String> s2 = new HashSet<String>();
	//		s1.add("x");
	//		s1.add("z");
	//		s2.add("z");
	//		System.out.println(union(s1,s2));} => [x,z]

	private static Set<String> union(Set<String> s1, Set<String> s2){
		Set<String> unionSet = new HashSet<String>();
		for(String str: s1){
			unionSet.add(str);
		}
		for(String str:s2){
			if(!unionSet.contains(str)){
				unionSet.add(str);
			}
		}

		return unionSet;

	}
	// List -> List
		// GIVEN: a list of object of type X
		// RETURN: a list of object of type X
		// EXAMPLE:
		// List<ExpVal> parameterlist = new ArrayList<ExpVal>();
		// parameterlist.add(Asts.expVal(10));
		// initialParameters(parameterlist);     => List(Asts.expVal(10))
		private static <X> List<X> initialParameters(List<X> lst){
			if(lst.size()==1){
				return Asts.list(lst.get(0));
			}else if(lst.size()==2){
				return Asts.list(lst.get(0),lst.get(1));
			}else if(lst.size()==3){
				return Asts.list(lst.get(0),lst.get(1),lst.get(2));
			}else{
				return Asts.list(lst.get(0),lst.get(1),lst.get(2),lst.get(4));
			}
		}
		

		// (IfExp,CallExp,ConstantExp,ArithmeticExp,LambdaExp,IdentifierExp) Set List -> Set
		// GIVEN: a expression that one of the given type, a set contains free variable and a environment
		// RETURN: free variables in given context
		// EXAMPLE: undefinedInConstant(e, fv, env) => fv

		private static Set<String> undefinedInLambda(LambdaExp le, Set<String> fv, List<String> env){
			for(String f : le.formals()){
				env.add(f);
			}
			return getUndefined(le.body(), fv, env);
		}

		private static Set<String> undefinedInIf(IfExp le, Set<String> fv,List<String> env){
			Set<String> fvtest = getUndefined(le.testPart(), fv, env);
			Set<String> fvthen = getUndefined(le.thenPart(), fv, env);
			Set<String> fvelse = getUndefined(le.elsePart(), fv, env);


			return union(fvtest, union(fvthen,fvelse));
		}

		private static Set<String> undefinedInCall(CallExp ce, Set<String> fv,List<String> env){
			Set<String> newfv = new HashSet<String>(fv);
			for(Exp e: ce.arguments()){
				union(newfv, getUndefined(e, newfv,env));
			} 	
			return newfv;
		}


		private static Set<String> undefinedInArithmetic(ArithmeticExp ae, Set<String> fv,List<String> env){
			Set<String> fvleft = getUndefined(ae.leftOperand(), fv, env);
			Set<String> fvright = getUndefined(ae.rightOperand(), fv, env);

			return union(fvleft,fvright);
		}

		private static Set<String> undefinedInIdentifier(IdentifierExp ie, Set<String> fv,List<String> env){
			if(!env.contains(ie.name())){
				fv.add(ie.name()); 
			}

			return fv;
		}

		private static Set<String> undefinedInConstant(ConstantExp ce, Set<String> fv,List<String> env){
			return fv;
		}


		// Exp Set List -> Set
		// GIVEN: an expression , a set of free variable and current environment
		// RETURN: the free variables in given context
		// Interp: select correct function based on type of expression
		private static Set<String> getUndefined(Exp e, Set<String> fv, List<String> env){
			if(e.isArithmetic())
				return undefinedInArithmetic(e.asArithmetic(),fv,env);
			if(e.isLambda())
				return undefinedInLambda(e.asLambda(),fv, env);
			if(e.isIf())
				return undefinedInIf(e.asIf(),fv, env);
			if(e.isCall())
				return undefinedInCall(e.asCall(),fv, env);
			if(e.isConstant())
				return undefinedInConstant(e.asConstant(),fv, env);
			else
				return undefinedInIdentifier(e.asIdentifier(),fv,env);
		}
}

// test for ps10//////////////////////////////////////////////////////////////////////////////
//public static void main(String[] args) {
//
//////TEST CASE 1///////
//////EXAMPLE ON INSTRUCTION//////
//				Exp exp1
//				= Asts.arithmeticExp (Asts.identifierExp ("n"),
//						"MINUS",
//						Asts.constantExp (Asts.expVal (1)));
//		
//				Exp call1
//				= Asts.callExp (Asts.identifierExp ("fact"),
//						Asts.list (exp1));
//				Exp testPart
//				= Asts.arithmeticExp (Asts.identifierExp ("n"),
//						"EQ",
//						Asts.constantExp (Asts.expVal (0)));
//				Exp thenPart
//				= Asts.constantExp (Asts.expVal (1));
//				Exp elsePart
//				= Asts.arithmeticExp (Asts.identifierExp ("n"),
//						"TIMES",
//						call1);
//				Def def1
//				= Asts.def ("fact",
//						Asts.lambdaExp (Asts.list ("n"),
//								Asts.ifExp (testPart,
//										thenPart,
//										elsePart)));
//		
//				ExpVal result = Programs.run (Asts.list (def1),
//						Asts.list (Asts.expVal (5)));
//				System.out.println (result.asInteger());

//		//////TEST CASE2///////
//		/////////min//////////
//				Exp testPart
//				= Asts.arithmeticExp (Asts.identifierExp ("x"),
//						"LT",
//						Asts.identifierExp ("y"));	
//			Exp thenPart
//				= (Asts.identifierExp ("x"));
//		
//		
//				Exp elsepart
//				= (Asts.identifierExp ("y"));
//		
//		
//				Def def1
//				= Asts.def ("min",
//						Asts.lambdaExp (Asts.list ("x","y"),
//								Asts.ifExp (testPart,
//										thenPart,
//										elsepart)));
//		
//				ExpVal result = Programs.run (Asts.list (def1),
//						Asts.list (Asts.expVal (50), Asts.expVal (10)));	
//		
//				System.out.println (result.asInteger());  
//
//		///////////TEST CASE 3/////////////////
//		///////////CONSTANT ENTRY////////////
//				Exp consexp = Asts.constantExp(Asts.expVal(10));
//				Def def1=Asts.def("constant", consexp);
//				ExpVal result = Programs.run(Asts.list(def1), new ArrayList<ExpVal>());
//				System.out.println(10 == result.asInteger());
//
//		////////////TEST CASE5 4//////////////////
//		////////////multiple definition////////
//		Exp consexp = Asts.constantExp(Asts.expVal(1));
//		Def defcons = Asts.def("global", consexp);
//
//		Exp airith1 = Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"PLUS",
//				Asts.identifierExp("global"));
//
//		Exp airith2 = Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"MINUS",
//				Asts.identifierExp("global"));
//
//		Exp airith3 = Asts.arithmeticExp (Asts.identifierExp ("a"),
//				"TIMES",
//				Asts.identifierExp ("b"));		
//		Def def1
//		= Asts.def ("multiple",
//				Asts.lambdaExp (Asts.list ("a","b"),
//						airith3));
//
//		Exp call1  = Asts.callExp (Asts.identifierExp ("multiple"),
//				Asts.list (airith1,airith2));		
//		Def entrydef = Asts.def("entrydef", Asts.lambdaExp(Asts.list ("n"), call1));
//
//		ExpVal result = Programs.run (Asts.list (entrydef,defcons,def1),
//				Asts.list (Asts.expVal (5)));
//		System.out.println (result.asInteger());
//
//		////////////TEST CASES 5//////////////////
//		////////////Fibonacci////////
//				Exp exp1 = Asts.arithmeticExp (Asts.identifierExp ("n"), 
//		                                                   "MINUS", Asts.constantExp (Asts.expVal (1)));
//						Exp call1 = Asts.callExp (Asts.identifierExp ("fib"), Asts.list (exp1));
//				
//						Exp exp2 = Asts.arithmeticExp (Asts.identifierExp ("n"), 
//		                                                  "MINUS", Asts.constantExp (Asts.expVal (2)));
//						Exp call2 = Asts.callExp (Asts.identifierExp ("fib"), Asts.list (exp2));
//				
//				
//						Exp exp3 = Asts.arithmeticExp (call1,"PLUS",call2);
//				
//						Exp testPart1 = Asts.arithmeticExp (Asts.identifierExp ("n"), 
//		                                                  "LEQ", Asts.constantExp (Asts.expVal (1)));
//				
//						Exp thenPart1 = Asts.identifierExp("n");
//				
//						Exp elsepart1= exp3;
//				
//				
//						Def def1 = Asts.def ("fib", Asts.lambdaExp 
//		                        (Asts.list ("n"), Asts.ifExp (testPart1, thenPart1, elsepart1)));
//				
//						ExpVal result = Programs.run (Asts.list (def1), Asts.list (Asts.expVal (6)));
//						System.out.println (result.asInteger());

/////////call entry///////
//		Exp consexp = Asts.constantExp(Asts.expVal(1));
//		Def defcons = Asts.def("global", consexp);
//		Exp airith11 = Asts.arithmeticExp (Asts.identifierExp ("n"),
//				"PLUS",
//				Asts.constantExp(new ExpValType1(1)));
//		
//		Def def11 =  Asts.def ("plus", Asts.lambdaExp 
//				                        (Asts.list ("n"), airith11));
//		
//		Exp call11 = Asts.callExp(Asts.identifierExp("plus"), Asts.list(Asts.identifierExp("global")));
//		
//		Def def22 = Asts.def("main", call11);
//		ExpVal result2 = Programs.run (Asts.list (def22,def11,defcons), new ArrayList<ExpVal>());
//		System.out.println (result2.asInteger());


//	}
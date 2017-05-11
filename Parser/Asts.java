import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Static Factory
public class Asts {
	// Static factory methods for Def
    
    // Returns a Def with the given left and right hand sides.
    
    public static Def def (String id1, Exp rhs) { 
    	return new DefType1(id1, rhs);
    }
    
    // Static factory methods for Exp
    
    // Returns an ArithmeticExp representing e1 op e2.
    
    public static ArithmeticExp arithmeticExp (Exp e1, String op, Exp e2) {
    	return new ArithmeticExpType1(e1,op,e2);
    	}
    
    // Returns a CallExp with the given operator and operand expressions.
    
    public static CallExp callExp (Exp operator, List<Exp> operands) { 
    	return new CallExpType1(operator, operands); 
    	}
    
    // Returns a ConstantExp with the given value.
    
    public static ConstantExp constantExp (ExpVal value) { 
    	return new ConstantExpType1(value); 
    	}

    // Returns an IdentifierExp with the given identifier name.
    
    public static IdentifierExp identifierExp (String id) { 
    	
    	return new IdentifierExpType1(id);
    }
    
    // Returns an IfExp with the given components.
    
    public static IfExp ifExp (Exp testPart, Exp thenPart, Exp elsePart) { 
    	return new IfExpType1(testPart,thenPart,elsePart);
    }
    
    // Returns a LambdaExp with the given formals and body.
    
    public static LambdaExp lambdaExp (List<String> formals, Exp body) { 
    	return new LambdaExpType1(formals, body);
    }
    
    // Static factory methods for ExpVal
    
    // Returns a value encapsulating the given boolean.
    
    public static ExpVal expVal (boolean b) { 
    	return new ExpValType1(b);
    }
    
    // Returns a value encapsulating the given (long) integer.
    
    public static ExpVal expVal (long n) { 
    	return new ExpValType1(n); 
    	}
    
    // Returns a value encapsulating the given lambda expression
    // and environment.
    
    public static FunVal expVal (LambdaExp exp, Map<String,ExpVal> env) { 
    	return new FunValType1(exp, env);
    }

    // Static methods for creating short lists
    // one parameter
    public static <X> List<X> list (X x1) { 
    	List<X> lst = new ArrayList<X>();
    	lst.add(x1);
    	return lst;
    }
    // two parameter
    public static <X> List<X> list (X x1, X x2) { 
    	List<X> lst = new ArrayList<X>();
    	lst.add(x1);
    	lst.add(x2);
    	
    	return lst;
    }
    // three parameter
    public static <X> List<X> list (X x1, X x2, X x3) {
    	List<X> lst = new ArrayList<X>();
    	lst.add(x1);
    	lst.add(x2);
    	lst.add(x3);
    	return lst;
    }
    // four parameter
    public static <X> List<X> list (X x1, X x2, X x3, X x4) { 
    	List<X> lst = new ArrayList<X>();
    	lst.add(x1);
    	lst.add(x2);
    	lst.add(x3);
    	lst.add(x4);
    	return lst;
    }

}

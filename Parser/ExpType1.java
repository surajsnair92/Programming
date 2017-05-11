import java.util.List;
import java.util.Map;

public class ExpType1 extends AstType1 implements Exp {
	
	// Default Constructor for super class
	ExpType1(){}
	
	public boolean isExp(){return true;}
	
	// * -> Boolean
	// Returns true iff this Exp is a constant, identifier,
	// lambda, arithmetic, call, or if expression (respectively).
	// Precondition: the corresponding predicate above is true.
	// Returns this.
	// EXAMPLE:  consexp.isConstant()  -> true

	@Override
	public  boolean isArithmetic(){return false;}

	@Override
	public  boolean isIdentifier(){return false;};
	
	@Override
	public  boolean isConstant(){return false;};
	
	@Override
	public  boolean isIf(){return false;};
	
	@Override
	public  boolean isLambda(){return false;};
	
	@Override
	public  boolean isCall(){return false;};

	

	
	// Precondition: the corresponding predicate above is true.
    // Returns this.
    // (These methods amount should eliminate most casts.)
	// EXAMPLE:  consexp.asConstant()
	
	//*-> ConstantExp
	@Override
	public ConstantExp asConstant() {
		try{
			return (ConstantExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
	}

	//*-> IdentifierExp
	@Override
	public IdentifierExp asIdentifier() {
		try{
			return (IdentifierExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
		
	}

	//*-> LambdaExp
	@Override
	public LambdaExp asLambda() {
		try{
			return (LambdaExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
	}

	//*-> ArithmeticExp
	@Override
	public ArithmeticExp asArithmetic() {
		try{
			return (ArithmeticExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
	}

	//*-> CallExp
	@Override
	public CallExp asCall() {
		try{
			return (CallExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
	}

	//*-> IfExp
	@Override
	public IfExp asIf() {
		try{
			return (IfExp)this;
		}catch(Exception u){
			 throw new UnsupportedOperationException();
		}
		
	}

	@Override
	// Returns the value of this expression when its free variables
	//     have the values associated with them in the given Map.
	// May run forever if this expression has no value.
	// May throw a RuntimeException if some free variable of this
	//     expression is not a key of the given Map or if a type
	//     error is encountered during computation of the value.
	public ExpVal value(Map<String, ExpVal> env){
		return this.value(env);
		
	};
	
	public static void main(String[] arg1){
		ConstantExpType1 cexp = new ConstantExpType1(new ExpValType1(10));
		System.out.println(true == cexp.isExp());
		System.out.println(false == cexp.isPgm());
		System.out.println(false == cexp.isDef());
		System.out.println(cexp.asExp());
		
		
		Exp ae = Asts.arithmeticExp(Asts.constantExp(Asts.expVal(10)), "TIMES", Asts.constantExp(Asts.expVal(10)));
		System.out.println(ae.isArithmetic());
		System.out.println(ae.isCall());
		
//		Exp emptyexp = new ExpType1();
//		System.out.println(false == emptyexp.isArithmetic());
//		System.out.println(false == emptyexp.isIf());
//		System.out.println(false == emptyexp.isCall());
//		System.out.println(false == emptyexp.isIdentifier());
//		System.out.println(false == emptyexp.isConstant());
	}

}

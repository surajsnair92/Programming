import java.util.ArrayList;
import java.util.List;

public class DefType1 extends AstType1 implements Def{
	String lhs;
	Exp rhs;
	// Constructor Template: new DefType1(String, Exp)
	//  Interpretation:
	//			 lhs represents the definition on the left.
	//	         which will be an identifier represented as a String.
	//           e1 represents the definitions on the right.
	//           which will be a ConstantExp or a LambdaExp.
	public DefType1(String lhs, Exp e1) {
		this.lhs = lhs;
		this.rhs = e1;
	}
	// RETURNS: a string which is the left of definition.
	public String lhs() {
		return this.lhs;
	}
	@Override
	//RETURNS: an Exp which is the right of definition.
	public Exp rhs() {
		return this.rhs;
	}
	////////////////TEST///////////////////////////////////	
	public static void main(String[] args) {
		ConstantExp cons1 = Asts.constantExp(Asts.expVal(10));
		ArrayList<String> forParam = new ArrayList<String>();
		forParam.add("k");
		IdentifierExp id1 = Asts.identifierExp("k");
		IdentifierExp id2 = Asts.identifierExp("m");
		ArithmeticExp aexp = new ArithmeticExpType1(id1, "TIMES", id1);

		LambdaExp lamdaFunc = Asts.lambdaExp(forParam, aexp);
		Asts.def("test", cons1);
		Asts.def("test2", lamdaFunc);

	}
}

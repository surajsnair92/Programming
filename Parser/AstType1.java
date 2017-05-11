import java.util.List;

public class AstType1 implements Ast {

	//RETURNS: true iff the object type is pgm
	public boolean isPgm() {
		return false;
	}

	@Override
	// RETURNS: true iff the object type is Def
	public boolean isDef() {
		return false;
	}
	
	@Override
	// RETURNS: true iff the object type is Exp
	public boolean isExp() {
		return false;
	}

	@Override
	public List<Def> asPgm() {
		Program p = (Program) this;
		return p.getDefinitionlist();
	}

	@Override
	public Def asDef() {
		return (Def) this;
	}

	@Override
	public Exp asExp() {
		return (Exp) this;
	}
	
	// RETURNS: true iff the object is List
	public boolean isList(){
		return this instanceof List;
	}
/////////////////////TEST//////////////////////////////////	
	public static void main(String[] arg1){
		Ast astcexp = Asts.constantExp(Asts.expVal(10));
		System.out.println(true == astcexp.isExp());
		System.out.println(false == astcexp.isPgm());
		System.out.println(false == astcexp.isDef());
		System.out.println(astcexp.asExp());
		
		Ast astdef1 = (Ast) Asts.def("test", (Exp)astcexp);
		System.out.println(false == astdef1.isExp());
		System.out.println(false == astdef1.isPgm());
		System.out.println(true == astdef1.isDef());
		System.out.println(astdef1.asDef());
		
		Ast astpgm = (Ast)new ProgramType1(Asts.list((Def)astdef1));
		System.out.println(false == astpgm.isExp());
		System.out.println(true == astpgm.isPgm());
		System.out.println(false == astpgm.isDef());
		
		System.out.println(true == astpgm.isPgm());
		System.out.println(false == astpgm.isDef());
		System.out.println(false == astpgm.isExp());
		
		System.out.println(astpgm.asPgm());
		
		Ast emptyast = new AstType1();
		System.out.println(false == emptyast.isDef());
		System.out.println(false == emptyast.isExp());
		System.out.println(false == emptyast.isPgm());
		
//		Programs.undefined("/Users/dyr429/Documents/workfolder/set11/src/bad.ps11");


	}
}

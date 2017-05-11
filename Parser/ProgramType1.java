import java.util.ArrayList;
import java.util.List;
//
public class ProgramType1 extends AstType1 implements Program {
	private List<Def> definitionlist;
	private List<ExpVal> inputs;
	
	// Constructor Template: new Program((List<Def>)
	//  Interpretation:
   //	p represents the list of definitions
   //   i represents the list of ExpVal
	ProgramType1 (List<Def> lst){
		definitionlist = new ArrayList<Def>(lst);
		inputs = null;
	}
	
	ProgramType1 (List<Def> lst, List<ExpVal> i){
		definitionlist = new ArrayList<Def>(lst);
		inputs = i;
	}
	
	public boolean isPgm(){return true;}
	
	
	
	// Getters
	public List<Def> getDefinitionlist(){
		return definitionlist;
	}
	
	public List<ExpVal> getInputs(){
		return inputs;
	}

}

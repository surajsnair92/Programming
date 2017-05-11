import java.util.List;

public interface Program extends Ast {
	//return the Definitionlist
	List<Def> getDefinitionlist();
	
	public List<ExpVal> getInputs();

}

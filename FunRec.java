import java.util.*;

// Functions
public class FunRec extends SymTabRec
{
	private List<SymTabRec> params;

	public FunRec(String name, int scope, int type, List<SymTabRec> params)
	{
		super(name, scope, type);
		this.params = params;
	}

	public void setParams(List<SymTabRec> params)
	{
		this.params = params;
	}

	public List<SymTabRec> getParams()
	{
		return this.params;
	}

	public int getNumParams()
	{
		// number of params
		return ((this.params == null) ? 0 : this.params.size());
	}

	public boolean isVar()
	{
		return false;
	}

	public boolean isArr()
	{
		return false;
	}

	public boolean isFun()
	{
		return true;
	}

	public String toString()
	{
		return "kind: function " + super.toString() + "\n\nparams: "
			+ getNumParams() + "\n";
	}

}

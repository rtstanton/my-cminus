// Local and global scalar variables
public class VarRec extends SymTabRec
{
	// invalid jvm num as default
	public final int jvmnum;

	public VarRec(String name, int scope, int type)
	{
		super(name, scope, type);
		if (scope != 0)
		{
			// local variable. set jvm num.
			this.jvmnum = getJVMNum();
		}
		else
		{
			// global variable. set jvm num to be an invalid num.
			this.jvmnum = -1;
		}
	}

	public boolean isVar()
	{
		return true;
	}

	public boolean isArr()
	{
		return false;
	}

	public boolean isFun()
	{
		return false;
	}

	public String toString()
	{
		return "kind: variable " + super.toString()
			+ (scope > 0 ? "\t\tJVM #: " + jvmnum : "") + "\n";
	}
}

// Local and global array variables
public class ArrRec extends SymTabRec
{
	// invalid jvm num as default
	public final int jvmnum;
	public final int size;

	public ArrRec(String name, int scope, int type, int size)
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
		// set size for array params as -1 in the call to this constructor.
		this.size = size;
	}

	public boolean isVar()
	{
		return false;
	}

	public boolean isArr()
	{
		return true;
	}

	public boolean isFun()
	{
		return false;
	}

	public String toString()
	{
		return "kind: array " + super.toString()
			+ (scope > 0 ? "\t\tJVM #: " + jvmnum : "")
			+ (size < 0 ? "" : "\t\tsize: " + size) + "\n";
	}
}

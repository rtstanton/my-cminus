// Base class for symbol table recs
public abstract class SymTabRec
{
	public final String name;
	public final int scope;

	// type of var, base type of arr, ret type of fun
	public final int type;

	// for keeping track of JVM num
	private static int nextNum;

	public SymTabRec(String name, int scope, int type)
	{
		this.name = name;
		this.scope = scope;
		this.type = type;
	}

	protected static int getJVMNum()
	{
		// return and increment
		return nextNum++;
	}

	public static void setJVMNum(int i)
	{
		nextNum = i;
	}

	public boolean isGlobal()
	{
		return scope == 0;
	}

	public boolean isLocal()
	{
		return scope > 0;
	}

	public abstract boolean isVar();

	public abstract boolean isArr();

	public abstract boolean isFun();

	public String toString()
	{
		return "\t\tname: " + name + "\t\tscope: " + scope + "\t\ttype: "
			+ type;
	}
}

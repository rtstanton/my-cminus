// A generic class that implements symtab as a stack of has tables
import java.util.*;

public class SymTab<E>
{
	// current scope
	private int scope;

	// stack of symbol tables
	private List<HashMap<String, E>> symTabs;

	// current symbol table
	private HashMap<String, E> currSymTab;

	// deleted symbol tables (when a scope is exited
	private List<HashMap<String, E>> deletedSymTabs;

	public SymTab()
	{
		// initialize things
		symTabs = new ArrayList<HashMap<String, E>>();
		deletedSymTabs = new ArrayList<HashMap<String, E>>();
		scope = -1;
		currSymTab = null;
	}

	public void enterScope()
	{
		// allocate a new symtab, incrementing scope first
		scope++;
		currSymTab = new HashMap<String, E>();
		symTabs.add(currSymTab);
	}

	public void exitScope()
	{
		// deallocate a new symtab, decrementing scope first
		scope--;
		deletedSymTabs.add(symTabs.remove(symTabs.size() - 1));
		if (scope >= 0)
		{
			currSymTab = symTabs.get(symTabs.size() - 1);
		}
	}

	public int getScope()
	{
		return scope;
	}

	public void incScope()
	{
		scope++;
	}

	public void decScope()
	{
		scope--;
	}

	public HashMap<String, E> getScopeTable(int scope)
	{
		return symTabs.get(scope);
	}

	public void insert(String s, E e)
	{
		currSymTab.put(s, e);
	}

	public boolean lookup(String s)
	{
		// looks up name in the current scope table
		return currSymTab.containsKey(s);
	}

	public E get(String s)
	{
		// looks up and returns
		for (int i = symTabs.size() - 1; i >= 0; i--)
		{
			// search each symtab and return the first found
			if (symTabs.get(i).containsKey(s))
			{
				return symTabs.get(i).get(s);
			}
		}
		// otherwise
		return null;
	}

	public String toString()
	{
		// the active symbol table
		String s = "";
		for (int i = symTabs.size() - 1; i >= 0; i--)
		{
			for (E e : symTabs.get(i).values())
				s += e.toString();
		}
		// the deleted symbol table
		for (int i = deletedSymTabs.size() - 1; i >= 0; i--)
		{
			for (E e : deletedSymTabs.get(i).values())
				s += e.toString();
		}
		return s;
	}
}

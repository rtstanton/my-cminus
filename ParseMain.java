import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

//
// Main compiler driver program
//

public class ParseMain
{
	// Set these to produce various output. For code generation,
	// only set CODE_GEN_OUTPUT to true, everything else to false.
	public static boolean PARSE_OUTPUT = true; // true for Part 1 evaluation, false for Part 2
	public static boolean SYMBOL_TABLE_OUTPUT = true; // true for Part 1 evaluation, false for Part 2
	public static boolean CODE_GEN_OUTPUT = false; // false for Part 1 evaluation, true for Part 2
	
	public ParseMain(String[] args) throws IOException
	{
		scan(args);
	}

	public void scan(String[] args) throws IOException
	{
		int count = 0;
		int success = 0;
		int failure = 0;
		
		if (args.length == 0)
		{
			BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));
						
			if (PARSE_OUTPUT || SYMBOL_TABLE_OUTPUT)
			{
				System.out.println("\nParsing: STDIN");
			}
			
			String program = lineReader.readLine();			
			Parser parser = new Parser("string", program);
			boolean result = parser.parse();
			
			if (PARSE_OUTPUT)
			{
				System.out.println("Parse " + (result ? "successful" : "errors"));
				System.out.println("------------------------------------------------");
			}
			count++;
			success += result ? 1 : 0;
			failure += result ? 0 : 1;
			
			// Output for symbol table
			if (SYMBOL_TABLE_OUTPUT)
			{
				System.out.println("\nSymbol Table Contents\n");
				System.out.println("===============================\n");
				System.out.println(parser.symtab);
			}
		}
		else
		{
			for (String arg : args)
			{
				Path filePath = Path.of(arg);
				String name = filePath.toFile().getName();
				
				if (PARSE_OUTPUT || SYMBOL_TABLE_OUTPUT)
				{
					System.out.println("\nParsing: " + name);
				}

				String program = Files.readString(filePath);
				Parser parser = new Parser(name, program);
				boolean result = parser.parse();
				
				if (PARSE_OUTPUT)
				{
					System.out.println("Parse " + (result ? "successful" : "errors"));
					System.out.println("------------------------------------------------");
				}
				count++;
				success += result ? 1 : 0;
				failure += result ? 0 : 1;
				
				// Output for symbol table
				if (SYMBOL_TABLE_OUTPUT)
				{
					System.out.println("\nSymbol Table Contents\n");
					System.out.println("===============================\n");
					System.out.println(parser.symtab);
				}
			}
		}
		
		if (PARSE_OUTPUT)
		{
			System.out.println("total: " + count + ", successes: " + success + ", failures: " + failure);
		}
	}

	public static void main(String[] args) throws IOException
	{
		new ParseMain(args);
	}
}

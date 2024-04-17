// Output created by jacc on Thu Apr 11 11:40:17 EDT 2024


import java.io.*;
import java.nio.charset.*;
import java.util.*;

class Parser implements ParserTokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private ParserVal[] yysv;
    private ParserVal yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yysv = new ParserVal[yyss];
        yytok = (token
                 );
    loop:
        for (;;) {
            switch (yyn) {
                case 0:
                    yyst[yysp] = 0;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 4:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 11;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 5:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 8;
                            continue;
                    }
                    yyn = 11;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 6:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr52();
                            continue;
                    }
                    yyn = 11;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 7:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 11;
                    continue;

                case 8:
                    return true;
                case 9:
                    yyerror("stack overflow");
                case 10:
                    return false;
                case 11:
                    yyerror("syntax error");
                    return false;
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        ParserVal[] newyysv = new ParserVal[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    private int yyr1() { // program : program_start declaration_list
        {
                        // CODEGEN if input instruction, generate read code
                        // CODEGEN generate the class constructor
                        // CODEGEN generate the epilog
                        // SYMTAB exit scope
                        // SEMANTIC if no main function, report semantic error 
                }
        yysv[yysp-=2] = yyrv;
        return 1;
    }

    private int yyr52() { // declaration_list : /* empty */
        return 3;
    }

    private int yyr2() { // program_start : /* empty */
        {
                        // SYMTAB enter scope
                        // CODEGEN generate prolog
                }
        yysv[yysp-=0] = yyrv;
        return 2;
    }

    protected String[] yyerrmsgs = {
    };


private String program;
private String name;

Stack<String> firstSelectionLabels = new Stack<String>();
Stack<String> lastSelectionLabels = new Stack<String>();
Stack<String> firstIterationLabels = new Stack<String>();
Stack<String> lastIterationLabels = new Stack<String>();

Stack<String> functionNames = new Stack<String>();
Stack<Integer> returnTypes = new Stack<Integer>();

/* reference to a temporary SymTabRec */
Stack<SymTabRec> symTabRecs = new Stack<SymTabRec>();

/* reference to the lexer object */
private static Yylex lexer;

ParserVal yylval; // current token value
int token;  // current token

/* The symbol table */
public final SymTab<SymTabRec> symtab = new SymTab<SymTabRec>();

/* To check if main has been encountered and is the last declaration */
private boolean seenMain = false;

/* To take care of nuance associated with params and decls in compound stmt */
private boolean firstTime = true;

/* To gen boilerplate code for read only if input was encountered  */
private boolean usesRead = false;

/* Interface to the lexer */
private int yylex()
{
    int retVal = -1;
    try
        {
                retVal = lexer.yylex();
    }
        catch (IOException e)
        {
                System.err.println("IO Error:" + e);
    }
    return retVal;
}
        
/* syntax errors */
public void yyerror (String error)
{
    System.err.println("Parse Error : " + error);
}

/* semantic errors */
public void semerror (String error)
{
        if (ParseMain.SYMBOL_TABLE_OUTPUT)
        {
        System.err.println("Semantic Error : " + error);
        }
}

/* constructor taking a Reader object */
public Parser (Reader r)
{
        lexer = new Yylex(r, this);
}

/* constructor taking a String */
public Parser(String name, String program)
{
        this.name = name;
        this.program = program;
        InputStream lineStream = new ByteArrayInputStream(program.getBytes((Charset.forName("UTF-8"))));
        InputStreamReader r = new InputStreamReader(lineStream);
        lexer = new Yylex(r, this);
        token = yylex(); // prime the pump
}

}

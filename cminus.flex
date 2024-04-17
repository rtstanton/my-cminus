/* cminus.flex - scanner for C-Minus programming language. */
/* Author: Your Name */
/* Date: the date */

%%

%unicode
%line
%column
%standalone
%implements ParserTokens

/* uncomment %debug to see verbose scanner output */
//%debug

%{
	// Store a reference to the parser object. Needed to reference yylval.
	private Parser yyparser;

	// constructor taking an additional parser
	public Yylex(java.io.Reader r, Parser yyparser)
	{
		this(r);	
		this.yyparser = yyparser;
	}
	
	// Returns current line number
	public int getLine()
	{
		return yyline;
	}

	// Returns current column number
	public int getCol()
	{
		return yycolumn;
	}
	
	public int value;

%}

digit = [0-9]
letter = [A-Za-z]

// integer is either 0 or any number
// of digits starting with 1-9
integer = "0"|[1-9]{digit}*

// identifier sequence of letters, digits, 
// and underscores, starting with a letter
identifier = ({letter})({letter}|{digit}|"_")*

newline = \r|\n|\r\n
whitespace     = [\s]+

%%

"else"				{ return ELSE; }
"void"				{ yyparser.yylval = new ParserVal(VOID); return VOID; }

"<="				{ yyparser.yylval = new ParserVal(LTE); return LTE; }

"+"					{ yyparser.yylval = new ParserVal(ADDOP); return ADDOP; }

"="					{ return ASSIGN; }

"("					{ return LPAREN; }
")"					{ return RPAREN; }

{identifier}		{ String identifier = yytext();
					  yyparser.yylval = new ParserVal(identifier);
					  return IDENTIFIER; }
					  
{integer}			{ int value = Integer.parseInt(yytext());
					  yyparser.yylval = new ParserVal(value);
					  return NUMBER; }

{whitespace}		{/* ignore */}

<<EOF>>				{ return ENDINPUT; }
.					{ return UNKNOWN; }

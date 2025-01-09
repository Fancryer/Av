parser grammar AvParser;

options
{
	tokenVocab=AvLexer;
}

chunk: map EOF;
exps: exp*;

exp: atom | list | map | binary;

atom: int | Float | string | True | False | Nil | Id;
list: '[' (exp (','? exp)*)? ']';
map: '{' (map_entry (','? map_entry)*)? '}';

map_entry: atom ':'? exp;
binary: '(' HexInt+ ')';
int: Int | HexInt;

string: DQUOTE string_content* DQUOTE;
string_content: '\\(' exp ')' | TEXT;
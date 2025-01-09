package org.fancryer.ast

interface AvNode
{
	companion object
	{
		val pos:Position?=null
	}
}

class Position

/*
chunk: (map | map_entries)? EOF;

borrow:
	Overlay
	| Unite
	| Default
	| Intersect
	| Differ
	| Either
	| Push
  ;

exp:
	atom                  #atom_exp
	| list                #list_exp
	| map                 #map_exp
	| bytes               #bytes_exp
	| '\'' (Id | string)  #var_exp
	| exp '.' atom        #ref_exp
	| exp borrow exp      #borrow_exp
	;

atom: int | Float | string | True | False | Nil | Id;
list_entry: decl_entry | exp;
list: '[' list_entries? ']';

list_entries: list_entry (','? list_entry)* ','?;

map_entries: map_entry (','? map_entry)* ','?;

map: '{' map_entries? '}';

map_entry: plain_map_entry | decl_entry;

plain_map_entry: atom ':'? exp;
decl_entry: (Id | string) (':=' | '::=') exp #exp_decl;

bytes: '(' HexInt+ ')';
int: Int | HexInt;

string: DQUOTE string_content* DQUOTE;
string_content: '\\(' exp ')' | TEXT+;
*/


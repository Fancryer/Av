parser grammar AvParser;

options
{
	tokenVocab=AvLexer;
}

chunk: (map | map_entries)? EOF;

borrow:
	Overlay
	| Unite
	| Default
	| Intersect
	| Differ
	| Either
	| Push
	| '`' Id '`'
  ;

exp:
	exp '.' atom        #ref_exp
	| atom                #atom_exp
	| list                #list_exp
	| map                 #map_exp
	| bytes               #bytes_exp
	| '\'' (Id | string)  #var_exp
	| exp '$' exp           #call_exp
	| exp borrow exp      #borrow_exp
	| lambda              #lambda_exp
	| '`(' exp ')`'       #paren_exp
	| match               #match_exp
	;

atom: int | Float | string | True | False | Nil | Id;
list_entry: decl_entry | exp;
list: '[' list_entries? ']';

list_entries: list_entry (','? list_entry)* ','?;

map_entries: map_entry (','? map_entry)* ','?;

map: '{' map_entries? '}';

map_entry: plain_map_entry | decl_entry | borrow_decl_entry;

borrow_decl_entry: Id ('`:=' | '`::=') exp;

plain_map_entry: atom ':'? exp;
decl_entry: (Id | string) (':=' | '::=') exp #exp_decl;

lambda: '`{' Id+ (match_clause+ | '->' block) '}`';
block_stat: decl_entry | match;

match: '`match' exp match_clause+;

match_clause: '`|' pattern ('`when' exp)? '->' block;

pattern:
	atom                      #atom_pattern
	| '\'' Id                 #var_pattern
	| pattern '`|->' pattern  #str_starts_with_pattern
	| arr_pattern             #array_pattern
	| '_'                     #wildcard
	| '`(' pattern ')`'       #paren_pattern
	;

arr_pattern:
	'[' ']'                                                   #empty_array
  | '[' head=pattern body=pattern* (',' tail=pattern)? ']'  #non_empty_array
  ;

block: block_stat* exp;

bytes: '(' HexInt+ ')';
int: Int | HexInt;

string: DQUOTE string_content* DQUOTE;
string_content: '\\(' exp ')' | TEXT;
lexer grammar AvLexer;

Int: '-'? Digit+;
Float: '-'? Digit+ '.' Digit+ ExponentPart?;

True: 'true';
False: 'false';
Nil: 'nil' | 'null';
HexInt: '#' HexDigit+;
Id: [a-zA-Z_0-9+/%|&^~<=>.*!?\u0391-\u03A9\u03B1-\u03C9\-]+;
Brace_Paren_Left: '(' -> pushMode(DEFAULT_MODE);
Brace_Paren_Right: ')' -> popMode;
Brace_Square_Left: '[';
Brace_Square_Right: ']';
Brace_Curly_Left: '{';
Brace_Curly_Right: '}';
Comma: ',';
Colon: ':';

fragment Char: ~["];
fragment Digit: [0-9];
fragment ExponentPart: [eE] [+-]? Digit+;
fragment HexDigit: [0-9a-fA-F];
EscapeSequence: '\\' (["\\bfnrt] | 'u' HexDigit HexDigit HexDigit HexDigit);
WS: [ \t\u000C\r\n]+ -> skip;
Comment: ';' ~[\n]* -> skip;

DQUOTE: '"' -> pushMode(IN_STRING);

mode IN_STRING;
TEXT: EscapeSequence | ~'"';
BACKSLASH_PAREN: '\\(' -> pushMode(DEFAULT_MODE);
DQUOTE_IN_STRING: '"' -> type(DQUOTE), popMode;
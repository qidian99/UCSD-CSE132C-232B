grammar XPath;

ap : DOC '("' NAME '")' '/' rp    #directChild
   | DOC '("' NAME '")' '//' rp   #indirectChild
   ;

rp : NAME                     #tagName
   | '*'                      #all
   | '.'                      #current
   | '..'                     #parent
   | 'text()'                 #text
   | '@' NAME                 #attr
   | '(' rp ')'               #parenRP
   | rp ',' rp                #concat
   | rp '/' rp                #directChildRP
   | rp '//' rp               #indirectChildRP
   | rp '[' f ']'             #filter
   ;

f : rp                        #rpFt
  | rp '=' rp                 #eq1
  | rp '=' STR                #str
  | rp 'eq' rp                #eq2
  | rp IS rp                  #is
  | '(' f ')'                 #parenFt
  | f 'and' f                 #and
  | f 'or' f                  #or
  | 'not' f                   #not
  ;

DOC: 'document' | 'doc';
IS : '==' | 'is';
NAME: [a-zA-Z0-9._]+;
//FILENAME : [a-zA-Z0-9._]+;
STR: '"' [a-zA-Z0-9._!,':? -]+ '"'; //[a-zA-Z0-9._!,':?]+;
WS : [ \t\r\n]+ -> skip;

grammar XQuery;
import XPath;

xq : var                                                         #xqVar
   | STR                                                         #xqStr
   | ap                                                          #xqAp
   | '(' xq ')'                                                  #xqParen
   | '<'NAME'>' '{' xq '}' '<' '/' NAME'>'                       #xqTag
   | xq '/' rp                                                   #xqDirectChild
   | xq '//' rp                                                  #xqIndirectChild
   | forclause letclause? whereclause? returnclause              #xqClause
   | letclause xq                                                #xqLet
   | joinclause                                                  #xqJoin
   | xq ',' xq                                                   #xqConcat
   ;

forclause: 'for' var 'in' xq (',' var 'in' xq)*;
letclause: 'let' var ':=' xq (',' var ':=' xq)*;
whereclause: 'where' cond;
returnclause: 'return' xq;
joinclause: 'join' '(' xq ',' xq ',' varArr ',' varArr')';

cond : xq '=' xq                                                 #condEq1
     | xq 'eq' xq                                                #condEq2
     | xq IS xq                                                  #condIs
     | 'empty' '(' xq ')'                                        #condEmpty
     | 'some' var 'in' xq (',' var 'in' xq)* 'satisfies' cond    #condSome
     | '(' cond ')'                                              #condParen
     | cond 'and' cond                                           #condAnd
     | cond 'or' cond                                            #condOr
     | 'not' cond                                                #condNot
     ;

var: '$' NAME;
varArr: '[' NAME (',' NAME)* ']';
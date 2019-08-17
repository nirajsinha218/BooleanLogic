Problem : BooleanLogic

Parse a prefix logic expression and evaluate it against variable bindings.

 

SYNTAX:

 

Expression -> '&' Expression Expression          logical AND

Expression -> '|' Expression Expression           logical OR

Expression -> '~' Expression                             logical NOT

Expression -> Variable

Expression -> Literal

Variable     ->  {a-z,A-Z}                                    variable names are one-letter

Literal        ->  '0'                                              FALSE

Literal        ->  '1'                                              TRUE

 

e.g. The string "~x" means (not x)

 

e.g. The string "&a|b1" means (a and (b or true))

 

e.g. The string "|X&&a&b0Y" means (X or ((a and (b and false)) and Y))

 

 

DEFINITION:

 

You will make a class called "Expression".  An Expression is constructed from a String representation of a boolean expression.

 

Expression has a method eval() that evaluates the expression against a list of variable bindings and returns whether the expression is true or false.  The list of variable bindings is given as a java.util.Map, mapping from variable names as java.lang.Char to true/false as java.lang.Boolean.  It is an error if the expression uses a variable not in the mapping.

 

Your code must be robust to all error conditions and gracefully fail by throwing meaningful exceptions.

 

You may not use any existing parser-generator code.

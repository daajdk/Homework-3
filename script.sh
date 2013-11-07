#!/bin/tcsh -f
echo
echo $1 Output
javac test.java SATSudoku.java untransform.java ScanBoard.java
java test $1 > cnf
./solver cnf > solution
java untransform solution

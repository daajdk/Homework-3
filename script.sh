#!/bin/tcsh -f
javac test.java SATSudoku.java untransform.java ScanBoard.java
java test $1 > cnf
./solver cnf > solution
java untransform solution

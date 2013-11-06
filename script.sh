#!/bin/tcsh -f
javac test.java SATSudoku.java untransform.java
java test > cnf
./solver cnf > unt
java untransform unt

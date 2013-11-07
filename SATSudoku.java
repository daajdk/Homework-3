//Dan Wegmann, Kevin McCarthy, Jen Senior
//DAA - Homework 3
//Solving Sudoku using a SAT solver
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class SATSudoku {
	final private static int SIZE = 9;
	final private static int EMPTY_SPACE = 0;
	final private static int MIN = 1;
	private String clauses = new String();
	public int varNum = 0;
	public int clauseNum = 0;
	
	
	public String buildClauses(int[][] puzzle){
		for (int i = 1; i <= SIZE; i++){
			String strI = Integer.toString(i);
			
			for (int j = 1; j <= SIZE; j++){
				String strJ = Integer.toString(j);
				
				//this builds a singleton clause because of a prefilled space
				if (!(puzzle[i][j] == EMPTY_SPACE)){
					clauses += strI + strJ + Integer.toString (puzzle[i][j]) + " 0\n";
					clauseNum++;
					varNum++;
				}
				
				//this builds the 'a space needs 1-9' miniclause that's to be added to the main clause list
				int x = 1;
				String miniClause = new String();
				while (x <= SIZE){
				       miniClause += strI + strJ + Integer.toString(x) + " ";
				       x++;
				       varNum++; 
				}
				
				miniClause += " 0\n";
				clauses += miniClause;
				clauseNum++;
				
				//at most clauses
				atMostForCells(strI, strJ, MIN, SIZE);
				
			}
			
			//this builds the 'every row contains 1-9' clause
            rowContainsOneThroughNine(i);	
			//this builds the 'at most' for the rows clauses
            atMostForRows(i);
			//this builds the 'every column contains 1-9' clause
            colContainsOneThroughNine(i); 
			//this builds the 'at most' for the columns clauses
            atMostForCols(i);
            // at most box
            atMostBox(i);
            // box contains 1-9
            boxContainsOneThroughNine(i);
		}
		return this.clauses;
	}
        
        private void atMostBox(int row){
//            int col1 = 1;
//
            int col2 = 2;
//            int row1 = row;
//            int row2 = row;
//            int saveCol1 = 1;
//            int saveCol2 = 2;
//            int saveRow1 = row;
//            int saveRow2 = 2;
                int col = 1;
                int save = col;
            if (row == 1 || row == 4 || row == 7){
                for(int val = 1; val <= SIZE; val++){
                    col = save;
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    col = 4;
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    col = 7;
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " 0\n";
                    this.clauseNum += (36*3);
                    this.varNum += 2*(36*3);
                }
            }
        }
        
        private void boxContainsOneThroughNine(int row){
            int col = 1;
            int save = col;
            if (row == 1 || row == 4 || row == 7){
                for(int val = 1; val <= SIZE; val++){
                    col = save;
                    this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += "0\n";
                    col = 4;
                    this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += "0\n";
                    col = 7;
                    this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+2) + Integer.toString(col+2) + Integer.toString(val) + " ";
                    this.clauses += "0\n";
                    this.clauseNum += 3;
                    this.varNum += 27;
                    
                }
                
            }
        }
        
	private void colContainsOneThroughNine(int i){
        for(int val=1; val <= SIZE; val++){
                    for(int j = 1; j <= SIZE; j++){
                        this.clauses += Integer.toString(j) + Integer.toString(i) + Integer.toString(val) + " ";
                        this.varNum++;
                    }
                    this.clauses += " 0\n";
                    this.clauseNum++;                
                }
	}
        
	private void rowContainsOneThroughNine(int i){
        for(int val=1; val <= SIZE; val++){
                    for(int j = 1; j <= SIZE; j++){
                        this.clauses += Integer.toString(i) + Integer.toString(j) + Integer.toString(val) + " ";
                        this.varNum++;
                    }
                    this.clauses += " 0\n";
                    this.clauseNum += 1;
                }
	}
    
	private void atMostForCols(int col){
                //int row = 1;
                //int col1 = col;
		int row2 = 2;
                //int val;
		//int row = 1;
		boolean flag = true;
        for(int val=1; val <= SIZE; val++){
            for(int row=1; row<SIZE; row++){
                        while(row2<=SIZE){
                            this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                            this.clauseNum++;
                            this.varNum += 2;
                            row2++;
                        }
                    }

                }
		}
        
	private void atMostForRows(int row){
		int col2 = 2;
//		int val = 1;
//		int col = 1;
		for(int val=1; val <= SIZE; val++){
                    for(int col=1; col<SIZE; col++){
                        while(col2<=SIZE){
                            this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n";
                            this.clauseNum++;
                            this.varNum += 2;
                            col2++;
                        }
                    }

			}
	}
    
	private void atMostForCells(String strI, String strJ, int min, int max){
		//this builds the 'at most' clauses that ensures 1 number per cell
		if (min == max){
			return;
		}
		if (strI != null && strJ != null){
			for (int k = min; k < max; k++){
				clauses += "-" + strI + strJ + Integer.toString(min) + " " + "-" + strI + strJ + Integer.toString(k + 1) + " 0\n";
				this.varNum += 2;
				this.clauseNum++;
			}
		}
		atMostForCells(strI, strJ, min+1, max); 
	}
	private String getPuzzleString(File file){
		String line = null;
		try{
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			while ((line = bufferReader.readLine()) != null)   {
				break;
			}
			bufferReader.close();
		}catch(Exception e){}
		return line;
	}
	
	private int[][] buildPuzzle(String input){
		ArrayList<Integer> Puzzle = new ArrayList<Integer>();
		for(int i=0; i<input.length(); i++)
			Puzzle.add(input.charAt(i)-'0');
		
		int[][] Matrix = new int[SIZE][SIZE];
		int i=0;
		int j=0;
		int k=0;
		for(i=0; i < SIZE; i++){
			for(j=0; j < SIZE; j++){
				Matrix[i][j] = (int)Puzzle.get(k);
				k++;}}
		for(i=0; i < SIZE; i++){
			for(j=0; j < SIZE; j++){
				System.out.print(Matrix[i][j]);
				if(j == SIZE-1)
					System.out.print("\n");
			}
		}
		return Matrix;
	}
}

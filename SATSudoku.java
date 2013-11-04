//Dan Wegmann, Kevin McCarthy, Jen Senior
//DAA - Homework 3
//Solving Sudoku using a SAT solver
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class SATSudoku {
	final private static int SIZE = 4;
	final private static int EMPTY_SPACE = 0;
	final private static int MIN = 1;
	private String clauses = new String();
	private int varNum = 0;
	private int clauseNum = 0;
	
	
	public String buildClauses(int[][] puzzle){
		for (int i = 1; i <= SIZE; i++){
			String strI = Integer.toString(i);
			
			for (int j = 1; j <= SIZE; j++){
				String strJ = Integer.toString(j);
				
//				//this builds a singleton clause because of a prefilled space
//				if (!(puzzle[i][j] == EMPTY_SPACE)){
//					clauses += strI + strJ + Integer.toString (puzzle[i][j]) + " 0\n";
//					clauseNum++;
//					varNum++;
//				}
//				
//				//this builds the 'a space needs 1-9' miniclause that's to be added to the main clause list
//				int x = 1;
//				String miniClause = new String();
//				while (x <= SIZE){
//				       miniClause += strI + strJ + Integer.toString(x) + " ";
//				       x++;
//				       varNum++; 
//				}
//				
//				miniClause += " 0\n";
//				clauses += miniClause;
//				clauseNum++;
				
				//at most clauses
				//atMostForCells(strI, strJ, MIN, SIZE);
				
			}
			
			//this builds the 'every row contains 1-9' clause
				//rowContainsOneThroughNine(i);	//DOESN'T WORK
			//this builds the 'at most' for the rows clauses
				atMostForRows(i);
			//this builds the 'every column contains 1-9' clause
				//colContainsOneThroughNine(i); //DOESNT WORK
			//this builds the 'at most' for the columns clauses
				//atMostForCols(i);
			
			
		}
		
		return this.clauses;
	}
	private void colContainsOneThroughNine(int i){
		String miniClause = new String();
		for (int k = 1; k <= SIZE; k++){
			int y = 1;
			int count = 0;
			while (y <= SIZE) {
				miniClause += Integer.toString(i + count) + Integer.toString(i) + Integer.toString(k) + " ";
				varNum++;
				y++;
			}
			count++;
			miniClause += " 0\n";
			clauses += miniClause;
			clauseNum++;
		}
	}
	private void rowContainsOneThroughNine(int i){
		String miniClause = new String();
		for (int k = 1; k <= SIZE; k++){
			int y = 1;
			int count = 0;
			while (y <= SIZE) {
				miniClause += Integer.toString(i) + Integer.toString(i + count) + Integer.toString(k) + " ";
				varNum++;
				y++;
			}
			count++;
			miniClause += " 0\n";
			clauses += miniClause;
			clauseNum++;
		}
	}
	private void atMostForCols(int col){
		int row2 = 2;
		int val = 1;
		int row = 1;
		boolean flag = true;
		while (flag){
			this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
							+ Integer.toString(row2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
			this.clauseNum++;
			this.varNum = this.varNum + 2;
			if (row2 == SIZE){
				row++;
				row2 = row;
			}
			row2++;
			if (row2 == SIZE && row == SIZE - 1){
				val++;
			}
			if (row == SIZE - 1 && row2 == SIZE && val == SIZE){
				flag = false;
			}
		}
	}
	private void atMostForRows(int row){
		int col2 = 2;
		int val = 1;
		int col = 1;
		while (true){
		//	this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
		//					+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n";
			System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
							+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n");
			this.clauseNum++;
			this.varNum = this.varNum + 2;
			if (col2 == SIZE && col == SIZE - 1){
				val++;
			}
			if (col2 == SIZE){
				col++;
				col2 = col;
			}
			col2++;
			
			if ((col == SIZE - 1) && (col2 == SIZE) && (val == SIZE)){
				return;
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
				this.varNum = this.varNum + 2;
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

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
	private String clauses = null;
	private int varNum = 0;
	private int clauseNum = 0;
	
	
	private String buildClauses(int[][] puzzle){
		for (int i = 1; i <= SIZE; i++){
			String strI = Integer.toString(i);
			
			for (int j = 1; j <= SIZE; j++){
				String strJ = Integer.toString(j);
				
				//this builds a singleton clause because of a prefilled space
				if (puzzle[i][j] != EMPTY_SPACE){
					clauses += strI + strJ + Integer.toString (puzzle[i][j]) + "0\n";
					clauseNum++;
					varNum++;
				}
				
				//this builds the 'a space needs 1-9' miniclause that's to be added to the main clause list
				int x = 1;
				String miniClause = null;
				while (x <= SIZE){
				       miniClause += strI + strJ + Integer.toString(x) + " ";
				       x++;
				       varNum++; 
				}
				
				miniClause += "0\n";
				clauses += miniClause;
				clauseNum++;
				
				//at most clauses
				atMostForCells(strI, strJ, MIN, SIZE);
				
			}
			
			//this builds the 'every row contains 1-9' 
			int y= 1;
			int count = 0;
			String miniClause = null;
			while (y <= SIZE){
				miniClause += strI + Integer.toString(i + count) + " ";
				count++;
				varNum++;
			}
			miniClause += "0\n";
			clauses += miniClause;
			clauseNum++;
				
			//this builds the 'at most' for the rows
			atMostForRows
		}
		
		return clauses;
	}
	private void atMostForRows(String strI){
		~
		
	}
	private void atMostForCells(String strI, String strJ, int min, int max){
		//this builds the 'at most' clauses that ensures 1 number per cell
		if (min == max){
			return;
		}
		if (strI != null && strJ != null){
			for (int k = min; k < max; k++){
				clauses += "-" + strI + strJ + Integer.toString(min) + " " + "-" + strI + strJ + Integer.toString(k + 1) + "0\n";
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

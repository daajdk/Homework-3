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
                        //System.out.println("rowcontainsthru9");
				rowContainsOneThroughNine(i);	//DOESN'T WORK
			//this builds the 'at most' for the rows clauses
                        //System.out.println("@mostRows");
                                atMostForRows(i);
			//this builds the 'every column contains 1-9' clause
                        //System.out.println("colcontainsthru9");
				colContainsOneThroughNine(i); //DOESNT WORK
			//this builds the 'at most' for the columns clauses
                        //System.out.println("@mostCols");
				atMostForCols(i);
                        // at most box
                        // for 4x4, it prints boxes 1 and 2 at the same time
                        //System.out.println("atmostbox");
                            atMostBox(i);
                        // box contains 1-9
                        //System.out.println("boxContains");
                            boxContainsOneThroughNine(i);
			
			
		}
		return this.clauses;
	}
        
        private void atMostBox(int row){
            int col = 1;
            int save = col;
//            if (row == 1 || row == 3){
//                for(int val = 1; val <= SIZE; val++){
//                    col = save;
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    col = 3;
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
//                    + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n");
//                }
//            }
            if (row == 1 || row == 3){
                for(int val = 1; val <= SIZE; val++){
                    col = save;
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    col = 3;
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
                    + "-" + Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " 0\n";
                    this.clauseNum += 12;
                    this.varNum += 24;
                }
            }
        }
        
        private void boxContainsOneThroughNine(int row){
            int col = 1;
            int save = col;
//            if (row == 1 || row == 3){
//                for(int val = 1; val <= SIZE; val++){
//                    col = save;
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ");
//                    System.out.print("0\n");
//                    col = 3;
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ");
//                    System.out.print("0\n");
//                    
//                }
//                
//            }
            if (row == 1 || row == 3){
                for(int val = 1; val <= SIZE; val++){
                    col = save;
                    this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += "0\n";
                    col = 3;
                    this.clauses += Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " ";
                    this.clauses += Integer.toString(row+1) + Integer.toString(col+1) + Integer.toString(val) + " ";
                    this.clauses += "0\n";
                    this.clauseNum += 2;
                    this.varNum += 8;
                    
                }
                
            }
        }
	private void colContainsOneThroughNine(int i){
//                for(int val=1; val <= SIZE; val++){
//                    for(int j = 1; j <= SIZE; j++)
//                        System.out.print(Integer.toString(j) + Integer.toString(i) + Integer.toString(val) + " ");
//                    System.out.print(" 0\n");
//                }
                for(int val=1; val <= SIZE; val++){
                    for(int j = 1; j <= SIZE; j++){
                        this.clauses += Integer.toString(j) + Integer.toString(i) + Integer.toString(val) + " ";
                        this.varNum++;
                    }
                    this.clauses += " 0\n";
                    this.clauseNum++;                
                }
		/*String miniClause = new String();
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
		}*/
	}
	private void rowContainsOneThroughNine(int i){
//                for(int val=1; val <= SIZE; val++){
//                    for(int j = 1; j <= SIZE; j++)
//                        System.out.print(Integer.toString(i) + Integer.toString(j) + Integer.toString(val) + " ");
//                    System.out.print(" 0\n");
//                }
                for(int val=1; val <= SIZE; val++){
                    for(int j = 1; j <= SIZE; j++){
                        this.clauses += Integer.toString(i) + Integer.toString(j) + Integer.toString(val) + " ";
                        this.varNum++;
                    }
                    this.clauses += " 0\n";
                    this.clauseNum += 1;
                }
		/*String miniClause = new String();
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
		}*/
	}
	private void atMostForCols(int col){
		int row2 = 2;
		int val;
		int row = 1;
		boolean flag = true;
//                for(val=1; val <= SIZE; val++){
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2+1) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                    System.out.print(Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
//				+ Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n");
//                }
                for(val=1; val <= SIZE; val++){
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2+1) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+1) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauses += "-" + Integer.toString(row+2) + Integer.toString(col) + Integer.toString(val) + " " 
				+ "-" + Integer.toString(row2+2) + Integer.toString(col) + Integer.toString(val) + " 0\n";
                    this.clauseNum += 6;
                    this.varNum += 12;
                }
		/*while (flag){
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
		}*/
	}
	private void atMostForRows(int row){
		int col2 = 2;
		int val = 1;
		int col = 1;
		/*while (true){
			
			if ((col == SIZE - 1) && (col2 == SIZE) && (val == SIZE)){
				return;
			}*/
//			for(val=1; val <= SIZE; val++){
//				System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n");
//				System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2+1) + Integer.toString(val) + " 0\n");
//				System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n");
//				System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2+1) + Integer.toString(val) + " 0\n");
//				System.out.print(Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n");
//				System.out.print(Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
//						+ Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n");
//			}
                        for(val=1; val <= SIZE; val++){
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n";
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2+1) + Integer.toString(val) + " 0\n";
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n";
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2+1) + Integer.toString(val) + " 0\n";
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col+1) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n";
				this.clauses += "-" + Integer.toString(row) + Integer.toString(col+2) + Integer.toString(val) + " " 
						+ "-" + Integer.toString(row) + Integer.toString(col2+2) + Integer.toString(val) + " 0\n";
                                this.clauseNum += 6;
                                this.varNum += 12;
			}
                        
		//	this.clauses += "-" + Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
		//					+ "-" + Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n";
			/*System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
							+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n");
			this.clauseNum++;
			this.varNum = this.varNum + 2;
			if( col2 < SIZE && col < SIZE-1 ){
			col2++;
			}
			if (col2 == SIZE && col < SIZE -1 && val < SIZE){
				col += 1;
				col2 = col +1;
				val += 1;
			}
			if(col2 == SIZE && col == SIZE-1 && val < SIZE){
				val += 1;
				col += 1;
				col2 = col + 1;
			}
			if(col2 == SIZE && col == SIZE-1 && val == SIZE){
				return;
			}
			/*if (col >= SIZE -1 && col2 >= SIZE){
				val++;
				col = 1;
				col2 = 2;
			}
	

		}
		
		if (col >= SIZE-1 & col2 >= SIZE && val >= SIZE){
			return;
		}
		System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " "
				+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n");
		if (col2 == SIZE && col < SIZE -1 && col+1 == SIZE-1){
			atMostForRows(row, val, col+1, col2);
		}
		if (col2 < SIZE && col < SIZE -1){
			atMostForRows(row, val, col+1, col2-1);
		}*/
		
//		for (int y = 1; y <= SIZE * SIZE; y++){
//			System.out.print(Integer.toString(row) + Integer.toString(col) + Integer.toString(val) + " " 
//								+ Integer.toString(row) + Integer.toString(col2) + Integer.toString(val) + " 0\n");
//			if (col2 < SIZE){
//				col2++;
//			}
//			if (col2 == SIZE && col < SIZE -1){
//				col++;
//			}
//			
//		}
//		atMostForRows(row, val+1, col, col2);
		//}
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

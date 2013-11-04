
public class test {

	public static void main(String[] args) {
		SATSudoku sat = new SATSudoku();

		int [] [] sudoku = new int[] [] {
				  { 0, 0, 0, 0, 0},
				  { 0, 0, 8, 0, 4},
			      { 0, 0, 3, 4, 0},
			      { 0, 9, 6, 0, 0},
			      { 0, 0, 0, 0, 2}
			    } ;
		String str = sat.buildClauses(sudoku);
		
		System.out.println(str);

	}

}

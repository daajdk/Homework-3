
public class test {

	public static void main(String[] args) {
		SATSudoku sat = new SATSudoku();

		int [] [] sudoku = new int[] [] {
				  { 0, 0, 0, 0, 0},
				  { 0, 1, 2, 3, 4},
			      { 0, 3, 4, 2, 1},
			      { 0, 4, 3, 1, 2},
			      { 0, 2, 1, 4, 0}
			    } ;
		String str = sat.buildClauses(sudoku);
                //System.out.println("TEST");
		System.out.println("p cnf "+sat.varNum+" "+sat.clauseNum);
		System.out.println(str);

	}

}

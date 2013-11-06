
public class test {

	public static void main(String[] args) {
		SATSudoku sat = new SATSudoku();

		int [] [] sudoku = new int[] [] {
                    {0,0,0,0,0,0,0,0,0,0},
				  { 0,8,1,2,7,5,3,6,4,9},
				  { 0,9,4,3,6,8,2,1,7,5},
			      { 0,6,7,5,4,9,1,2,8,3},
			      { 0,1,5,4,2,3,7,8,9,6},
			      { 0,3,6,9,8,4,5,7,2,1},
                              {0,2,8,7,1,6,9,5,3,4},
                              {0,5,2,1,9,7,4,3,6,8},
                              {0,4,3,8,5,2,6,9,1,7},
                              {0,7,9,6,3,1,8,4,5,2}
			    } ;
		String str = sat.buildClauses(sudoku);
                //System.out.println("TEST");
		System.out.println("p cnf "+sat.varNum+" "+sat.clauseNum);
		System.out.println(str);

	}

}

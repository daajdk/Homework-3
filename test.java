
public class test {

	public static void main(String[] args) {
		SATSudoku sat = new SATSudoku();

		int [] [] sudoku = new int[] [] {
                    {0,0,0,0,0,0,0,0,0,0},
				  { 0,0,0,7,0,0,0,1,0,0},
				  { 0,0,0,0,5,8,0,9,4,0},
			      { 0,9,0,8,0,0,0,0,3,0},
			      { 0,0,0,4,0,5,6,0,0,1},
			      { 0,0,0,0,0,0,0,0,0,0},
                              {0,6,0,0,7,1,0,4,0,0},
                              {0,0,3,0,0,0,0,7,0,6},
                              {0,0,4,6,0,2,1,0,0,0},
                              {0,0,0,5,0,0,0,8,0,0}
			    } ;
		String str = sat.buildClauses(sudoku);
                //System.out.println("TEST");
		System.out.println("p cnf "+sat.varNum+" "+sat.clauseNum);
		System.out.println(str);

	}

}

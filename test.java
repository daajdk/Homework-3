
public class test {

	public static void main(String[] args) {
		SATSudoku sat = new SATSudoku();
        ScanBoard board = new ScanBoard(10);
        int[][] sudoku = board.getBoard(args[0]);
		String str = sat.buildClauses(sudoku);
        System.out.println("p cnf "+sat.varNum+" "+sat.clauseNum);
		System.out.println(str);

	}

}

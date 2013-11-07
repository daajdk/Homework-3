/*
 * Clss which scans a file containing one line of digits for an NxN
 * Sudoku Board.
 */
import java.io.*;
import java.util.*;

/**
 *
 * @author J. Senior 11/6/13
 */
public class ScanBoard {

    private int size;

    /**
     * Constructor for class ScanBoard
     * @param num is n+1 for a n x n board
     */
    public ScanBoard(int num) {
        size = num;
    }

    /**
     * Reads a file containing a n x n size binary image.
     *
     * @param file the file with the binary image to be scanned for holes
     * @return the puzzle's board stored as a 2D array with zeros in the first
     * column and first row.
     * @exception IOException when there is a problem reading the file
     */
    public int[][] getBoard(String file) {

        int[][] board = new int[size][size];      //NxN mini-sudoku board
        String input;
        try {
            Scanner scanner = new Scanner(new File(file));
            input = scanner.nextLine();
            input = input.trim();

            int position = 0;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {

                    if (row == 0) {
                        board[row][col] = 0;
                    } else if (col == 0) {
                        board[row][col] = 0;
                    } else {
                        char next = input.charAt(position);
                        for (int iDigit = 1; iDigit <= size; iDigit++) {
                            char cDigit = '1';
                            for (int counter = 1; counter <= size; counter++) {
                                if (next == cDigit) {
                                    board[row][col] = counter;
                                }
                                cDigit++;
                            }
                        }
                        position++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IO error with reading " + file);
            System.exit(1);
        }
        return board;
    }
}

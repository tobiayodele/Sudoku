package sudoku;

import org.junit.Test;



import static org.junit.Assert.*;

public class SudokuSolverTests {
    @Test
    public void returnsCompletedSudoku(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        // At 50 missing cells, will always result in unique Sudoku
        // see experiments branch & report
        SudokuBoard puzzle = generator.iterativeRemoveCells(board, 50);
        SudokuSolver solver = new SudokuSolver();
        SudokuBoard solution = solver.solve(puzzle);
        // puzzle generated from board so solution should be the same as board
        assertArrayEquals(solution.getBoard(),board.getBoard());
    }

}

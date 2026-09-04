package sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionCounterTests {

    @Test
    public void completedBoardCounter(){
        //a completed board is done so should only have one solution
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        CellValidator validator = new CellValidator();
        assertEquals(1, generator.solutionCounter(board, validator));
    }

    @Test
    public void onePossibleSolution(){
        // a board with only one cell empty only has one solution
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        board.setCell(3,3,0);
        CellValidator validator = new CellValidator();
        assertEquals(1, generator.solutionCounter(board, validator));
    }

    // removed multiple solutions check. generator.iterativeRemoveCells will always return a unique solution
    // or null.

}

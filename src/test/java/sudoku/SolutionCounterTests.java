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

    @Test
    public void multiplePossibleSolutions(){
        // A puzzle needs at least 17 filled in cells to have a unique solution
        // (McGuire, Tugemann & Civario, 2014)
        // 81-16 = 65 hence taking out 65 cells results in a guaranteed puzzle
        // that does not have a unique solution.
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        board = generator.randomRemoveCells(board, 65);
        CellValidator validator = new CellValidator();
        assertEquals(2, generator.solutionCounter(board,validator) );
    }

}

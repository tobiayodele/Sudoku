package sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuGameTests {
    @Test
    public void getPuzzleGeneratesUniquePuzzle(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        CellValidator validator = new CellValidator();
        SudokuBoard puzzle = game.getPuzzle(45,board);
        assertEquals(1,generator.solutionCounter(puzzle,validator));
    }

}

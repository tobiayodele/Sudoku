package sudoku;


import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateGuessTests {
    @Test
    public void invalidRow(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        assertFalse(game.validateGuess(10, 5 , 4, board));
    }

    @Test
    public void invalidColumn(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        assertFalse(game.validateGuess(3, 10 , 4, board));
    }

    @Test
    public void invalidGuess(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        assertFalse(game.validateGuess(3,3 , 10, board));
    }

    @Test
    public void puzzleNotEmpty(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        assertFalse(game.validateGuess(3,3 , 4, board));
    }

    @Test
    public void validGuess(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuGame game = new SudokuGame();
        //accounting for 0 index 2d array but 1-indexed user entry
        board.setCell(2,2,0);
        assertTrue(game.validateGuess(3, 3, 4, board));

    }

}

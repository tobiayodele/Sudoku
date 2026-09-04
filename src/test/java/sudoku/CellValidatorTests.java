package sudoku;

import org.junit.Test;


import static org.junit.Assert.*;

public class CellValidatorTests {

    CellValidator validator = new CellValidator();

    @Test
    public void testInvalidRow() {
        // try to place duplicate 3 in the 3rd row
        SudokuBoard board = new SudokuBoard();
        board.setCell(3, 3, 3);
        assertFalse(validator.isValidCell(board.getBoard(), 3, 5, 3));
    }
    @Test
    public void testValidRow() {
        SudokuBoard board = new SudokuBoard();
        // no violation as there is no duplicate
        board.setCell(3, 3, 3);
        assertTrue(validator.isValidCell(board.getBoard(), 3, 5, 5));
    }

    @Test
    public void testInvalidColumn() {
        SudokuBoard board = new SudokuBoard();
        board.setCell(3, 3, 3);
        assertFalse(validator.isValidCell(board.getBoard(), 5, 3, 3));
    }
    @Test
    public void testValidColumn() {
        SudokuBoard board = new SudokuBoard();
        board.setCell(3, 3, 3);
        assertTrue(validator.isValidCell(board.getBoard(), 5, 3, 5));
    }

    @Test
    public void testInvalidBox() {
        // coordinates (6,8) has a 3 and (7,7) is in the same box so a new 3 cannot go in.
        SudokuBoard board = new SudokuBoard();
        board.setCell(8, 6, 3);
        assertFalse(validator.isValidCell(board.getBoard(),7 , 7, 3));
    }
    @Test
    public void testValidBox() {
        // coordinates (6,8) has a three but (4,4) is not in the same box so it is valid based just this box.
        SudokuBoard board = new SudokuBoard();
        board.setCell(8, 6, 3);
        assertTrue(validator.isValidCell(board.getBoard(), 4, 4, 3));
    }

    @Test
    public void testInvalidCell(){
        // Not allowed to overwrite a filled cell, if different number should not get flagged by row, column or box.
        SudokuBoard board = new SudokuBoard();
        board.setCell(3,3,3);
        assertFalse(validator.isValidCell(board.getBoard(),3,3, 9));
    }
}

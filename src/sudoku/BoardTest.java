package sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

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
    public void testValidCell(){
        // Not allowed to overwrite a filled cell, if different number should not get flagged by row, column or box.
        SudokuBoard board = new SudokuBoard();
        board.setCell(3,3,3);
        assertFalse(validator.isValidCell(board.getBoard(),3,3, 9));
    }

    @Test
    public void testValidBoard() {
        BoardValidator validator = new BoardValidator();
        int[][] validBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertTrue(validator.isValidBoard(validBoard));
    }

    @Test
    public void testNotCompleteBoard() {
        BoardValidator validator = new BoardValidator();
        //board has empty spaces at row 4, column 3 and row 7 column 5 so not in the same row, column or box
        // so should fail solely because it has 0s / empty spots
        int[][] NotCompleteBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 0, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 0, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 0, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertFalse(validator.isValidBoard(NotCompleteBoard));
    }

    @Test
    public void testInvalidBoardRow() {
        BoardValidator validator = new BoardValidator();
        int[][] invalidBoardRow = {
                // row one has duplicate 5
                {5, 5, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertFalse(validator.isValidBoard(invalidBoardRow));
    }

    @Test
    public void testInvalidBoardColumn() {
        BoardValidator validator = new BoardValidator();
        int[][] testInvalidBoardColumn = {
                //column 1 has duplicate 5
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {5, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertFalse(validator.isValidBoard(testInvalidBoardColumn));
    }

    @Test
    public void correctNumberOfRemovedCells(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.removeCells(board, 20);
        // check only 20 cells have been removed
        int count = 0;
        for(int [] row : removedCellsBoard.getBoard()){
            for (int cell : row){
                if(cell == 0){
                    count ++;
                }
            }
        }
        assertEquals(20, count);
    }

    @Test
    public void noOtherCellsAffected(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.removeCells(board, 20);
        boolean flag = true;
        for (int i = 0; i < 9; i++){
            for (int j=0; j<9; j++){
                if (removedCellsBoard.getBoard()[i][j]==0){
                    continue;
                }
                else{
                    if(!(removedCellsBoard.getBoard()[i][j] == board.getBoard()[i][j])){
                        flag = false;
                    }
                }
            }
        }
        assertTrue(flag);
    }

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
        board = generator.removeCells(board, 65);
        CellValidator validator = new CellValidator();
        assertEquals(2, generator.solutionCounter(board,validator) );
    }





}
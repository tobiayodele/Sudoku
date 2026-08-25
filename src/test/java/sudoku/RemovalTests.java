package sudoku;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemovalTests {
    @Test
    public void correctNumberOfRandomRemovedCells(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.randomRemoveCells(board, 20);
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
    public void noOtherRandomCellsAffected(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.randomRemoveCells(board, 20);
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
    public void correctNumberOfRecursiveRemovedCells(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.iterativeRemoveCells(board, 50);
        // check only 50 cells have been removed
        int count = 0;
        for(int [] row : removedCellsBoard.getBoard()){
            for (int cell : row){
                if(cell == 0){
                    count ++;
                }
            }
        }
        assertEquals(50, count);
    }

    @Test
    public void noOtherRecursiveCellsAffected(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard removedCellsBoard = generator.iterativeRemoveCells(board, 20);
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
        board.print();
        removedCellsBoard.print();
        assertTrue(flag);
    }

    @Test
    public void recursiveRemovalUniqueSolution(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        CellValidator validator = new CellValidator();
        SudokuBoard removedCellsBoard = generator.iterativeRemoveCells(board, 50);
        assertEquals(1, generator.solutionCounter(removedCellsBoard, validator));
    }

}

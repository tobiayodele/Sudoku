package sudoku;

import org.junit.Test;


import static org.junit.Assert.*;

public class RemovalTests {

    @Test
    public void correctNumberOfIterativeRemovedCells(){
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
    public void noOtherIterativeCellsAffected(){
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
    public void iterativeRemovalUniqueSolution(){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        CellValidator validator = new CellValidator();
        SudokuBoard removedCellsBoard = generator.iterativeRemoveCells(board, 50);
        assertEquals(1, generator.solutionCounter(removedCellsBoard, validator));
    }

}

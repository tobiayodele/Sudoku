package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private SudokuBoard board;
    private CellValidator validator;
    private List<Integer> cells;

    public SudokuGenerator(){
        this.board = new SudokuBoard();
        this.validator = new CellValidator();
        this.cells = new ArrayList<>();
        for (int i = 0; i < 81; i++){
            this.cells.add(i);
        }
    }

    SudokuBoard generate(){
        fillBoard(board, validator);

        return board;
    }

    private boolean fillBoard(SudokuBoard board, CellValidator validator) {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board.isEmpty(row, column)) {
                    Collections.shuffle(numbers);
                    for (int guess : numbers) {
                        if (validator.isValidCell(board.getBoard(), row, column, guess)) {
                            board.setCell(row, column, guess);
                            if (fillBoard(board, validator)) {
                                return true;
                            }
                            board.setCell(row, column, 0);
                        }

                    }
                    return false;
                }
            }

        }
        return true;
    }

     SudokuBoard removeCells(SudokuBoard board, int numberOfMissingCells){
        SudokuBoard puzzle = new SudokuBoard();
        puzzle.setBoard(board.getBoard());
        List<Integer> shuffle = new ArrayList<>(this.cells);
        Collections.shuffle(shuffle);

        for (int i = 0; i < numberOfMissingCells; i++){
            int value = shuffle.get(i);
            puzzle.setCell(value/9,value%9, 0);
        }
        return puzzle;
    }

     int solutionCounter(SudokuBoard puzzle, CellValidator validator){
        for (int row =0; row <9 ; row ++){
            for (int column = 0; column<9; column ++){
                if (puzzle.isEmpty(row,column)){
                    int solutions = 0;
                    for (int guess =1; guess <= 9; guess ++){
                        if (validator.isValidCell(puzzle.getBoard(), row ,column ,guess)){
                            puzzle.setCell(row,column,guess);
                            solutions += solutionCounter(puzzle,validator);
                            puzzle .setCell(row,column,0); // reset temp guess

                            if (solutions >= 2){ // actual figure does not matter, as long as its greater than 1 it's not unique.
                                return 2;
                            }
                        }
                    }
                    return solutions;
                }
            }
        }
        //by this point we have reached a complete valid sudoku
        return 1;
    }
}

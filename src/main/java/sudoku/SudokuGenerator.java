package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private final SudokuBoard board;
    private final CellValidator validator;
    private final List<Integer> cells;

    public SudokuGenerator(){
        this.board = new SudokuBoard();
        this.validator = new CellValidator();
        this.cells = new ArrayList<>(); // create list of numbers 0 - 80 representing every cell on the board
        for (int i = 0; i < 81; i++){
            this.cells.add(i);
        }
    }

    SudokuBoard generate(){
        fillBoard(board, validator);
        return board;
    }

    private boolean fillBoard(SudokuBoard board, CellValidator validator) {
        // find empty cell and recursively try valid numbers until a complete board is generated.
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

    SudokuBoard iterativeRemoveCells(SudokuBoard board, int numberOfMissingCells){
        // Iterative method, only passes through a max 81 times (while index is < cells.size)
        // randomly gets a cell and checks whether it still results in a unique solution.
        // If it doesn't reset and try the next cell - no backtracking
        SudokuBoard puzzle = new SudokuBoard();
        puzzle.setBoard(board.getBoard());
        List<Integer> shuffle = new ArrayList<>(this.cells);
        Collections.shuffle(shuffle);
        int removed = 0;
        int index =0;

        while(removed < numberOfMissingCells && index<shuffle.size()){
            int cell = shuffle.get(index);
            index++;
            int row = cell / 9;
            int column = cell % 9;
            int value = puzzle.getCell(row,column);

            if(value ==0){
                continue;
            }
            puzzle.setCell(row,column,0);
            if(solutionCounter(puzzle,validator) >=2){
                puzzle.setCell(row,column,value);
            }
            else{
                removed++;
            }
        }

        if(removed == numberOfMissingCells){
            return puzzle;
        }
        else{
            //generates a puzzle with not enough missing cells so null to represent failure.
            return null;
        }
    }

     int solutionCounter(SudokuBoard puzzle, CellValidator validator){
        //find the next empty cell
        for (int row =0; row <9 ; row ++){
            for (int column = 0; column<9; column ++){
                if (puzzle.isEmpty(row,column)){
                    int solutions = 0;
                    // try every possible value
                    for (int guess =1; guess <= 9; guess ++){
                        // filter for only valid numbers at this cell
                        if (validator.isValidCell(puzzle.getBoard(), row ,column ,guess)){
                            puzzle.setCell(row,column,guess);
                            // recursively check for other solutions based on this temporary guess.
                            solutions += solutionCounter(puzzle,validator);
                            puzzle .setCell(row,column,0); // reset temp guess

                            if (solutions >= 2){ // Stop at 2 solutions as any value greater than one is not unique.
                                return 2;
                            }
                        }
                    }
                    return solutions;
                }
            }
        }
        //by this point we have reached a complete valid main.java.sudoku
        return 1;
    }
}

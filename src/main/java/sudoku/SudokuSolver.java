package sudoku;

public class SudokuSolver {
     SudokuBoard solve(SudokuBoard puzzle){
        CellValidator validator = new CellValidator();
        //find the next empty cell
        for (int row =0; row <9 ; row ++){
            for (int column = 0; column<9; column ++){
                if (puzzle.isEmpty(row,column)){
                    // try every possible value
                    for (int guess =1; guess <= 9; guess ++){
                        // filter for only valid numbers at this cell
                        if (validator.isValidCell(puzzle.getBoard(), row ,column ,guess)){
                            // create a new sudoku board to not overwrite puzzle
                            SudokuBoard attempt = new SudokuBoard();
                            attempt.setBoard(puzzle.getBoard());
                            attempt.setCell(row,column, guess);

                            SudokuBoard solution= solve(attempt);

                            if (solution != null){ // filled board
                                return solution;
                            }
                        }
                    }
                    return null; // dead end so backtrack
                }
            }
        }
        //completed sudoku by this point
        return puzzle;
    }
}

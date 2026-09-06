package sudoku;

import java.util.Scanner;

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

    SudokuBoard enterBoard(){
         Scanner scanner = new Scanner(System.in);
         SudokuBoard board = new SudokuBoard();
         for (int i =0; i <81; i++){
             int row = i / 9;
             int column = i % 9;
             System.out.println("Enter number for box (" + row + "," + column + "): (Enter 0 for empty)");
             int value = scanner.nextInt();
             board.setCell(row,column,value);

         }
         return board;
    }


    void solveSudoku(){
         SudokuBoard board = enterBoard();
         SudokuGenerator generator = new SudokuGenerator();
         CellValidator validator = new CellValidator();
         int solutions = generator.solutionCounter(board, validator);
         if (solutions > 1){
             System.out.println("Not uniquely solvable");
         }
         else{
             SudokuBoard solution = solve(board);
             System.out.println("Success!");
             solution.print();
         }
    }
}

package sudoku;

import java.util.InputMismatchException;
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
             System.out.println("Enter number for box (" + (row +1) + "," + (column +1) + "): (Enter 0 for empty)");
             int value = getInt(scanner,row,column);
             board.setCell(row,column,value);

         }
         return board;
    }

    private int getInt(Scanner scanner, int row, int column) {
        while (true) {
            try {
                int value = scanner.nextInt();

                if (value >=0 && value <=9){
                    return value;
                }
                System.out.println("Invalid Number");
                System.out.println("Enter number for box (" + (row +1) + "," + (column +1) + "): (Enter 0 for empty)");

            } catch (InputMismatchException e) {
                System.out.println("Invalid Number");
                System.out.println("Enter number for box (" + (row +1) + "," + (column +1) + "): (Enter 0 for empty)");
                scanner.nextLine();
            }
        }
    }


    void solveSudoku(){
         while (true) {
             SudokuBoard board = enterBoard();
             SudokuGenerator generator = new SudokuGenerator();
             CellValidator cellValidator = new CellValidator();
             BoardValidator boardValidator = new BoardValidator();
             int solutions = generator.solutionCounter(board, cellValidator);
             if (solutions > 1) {
                 System.out.println("Not uniquely solvable");
                 continue;
             }
             if (!boardValidator.isValidPartialBoard(board.getBoard())) {
                 System.out.println("Board is unsolvable");
                 continue;
             }
             if (boardValidator.isValidBoard(board.getBoard())) {
                 System.out.println("Already Solved.");
                 break;
             }
             SudokuBoard solution = solve(board);
             System.out.println("Success!");
             solution.print();
             break;
         }
         }
    }


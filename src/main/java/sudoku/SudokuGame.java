package sudoku;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGame {
    private final HashMap<String, Integer> difficulties = new HashMap<>();
    private final SudokuGenerator generator = new SudokuGenerator();
    private final Scanner scanner = new Scanner(System.in);


    public SudokuGame() {
        // difficulty levels based on number of missing cells
        difficulties.put("easy", 40);
        difficulties.put("medium", 46);
        difficulties.put("hard", 55);

    }

    public void startGame (){
        int numberOfMissingCells = difficultySelect();
        SudokuBoard board = generator.generate();

        SudokuBoard puzzle = getPuzzle(numberOfMissingCells, board);
        playGame(board, puzzle, numberOfMissingCells);
    }

    private int difficultySelect() {
        String difficulty;
        while (true) {
            System.out.println("Enter difficulty easy/medium/hard");
            difficulty = scanner.nextLine();
            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                return (difficulties.get(difficulty));
            } else {
                System.out.println("Invalid Input");
            }
        }
    }


    private SudokuBoard getPuzzle(int numberOfMissingCells, SudokuBoard board) {
        SudokuBoard puzzle;
        while (true) { // generate new puzzles until a valid puzzle is generated
            puzzle = generator.iterativeRemoveCells(board, numberOfMissingCells);
            if (puzzle != null) {
                return puzzle;
            }
        }
    }


    boolean validateGuess(int row, int column, int guess, SudokuBoard puzzle) {
        if (column < 1 || column > 9) {
            return false;
        }  if (row < 1 || row > 9) {
            return false;
        }  if (!puzzle.isEmpty(row -1 , column -1)) { // account for 0-indexed array.
            return false;
        }
        return guess >= 1 && guess <= 9;
    }

    private int getInt(String field){
        while (true){
            try {
               return scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid Input");
                System.out.println("Enter " + field);
                scanner.nextLine();
            }
        }
    }


    private void playGame(SudokuBoard board, SudokuBoard puzzle, int numberOfMissingCells) {
        int numberOfRemainingCells = numberOfMissingCells;
        int lives = 3;

        while (numberOfRemainingCells > 0 && lives > 0) {
            boolean flag = false;
            int column = 0;
            int row = 0;
            int guess = 0;
            puzzle.print();
            while (!flag) {
                System.out.println("Enter column");
                column = getInt("column");
                System.out.println("Enter row");
                row = getInt("row");
                System.out.println("Enter guess");
                guess = getInt("guess");
                flag = validateGuess(row, column, guess, puzzle);
                if (!flag) {
                    System.out.println("Invalid Input! Try Again.");
                }
            }
            //by here you have a valid guess
            if (board.getBoard()[row - 1][column - 1] == guess) {
                System.out.println("Correct! You have " + lives + " live(s).");
                numberOfRemainingCells--;
                puzzle.setCell(row - 1, column - 1, guess);
            } else {
                lives--;
                System.out.println("Incorrect! You now have " + lives + " live(s).");
            }

        }

        if (lives == 0) {
            System.out.println("You failed...");
        }
        if (numberOfRemainingCells == 0) {
            System.out.println("You win!");
        }

    }
}











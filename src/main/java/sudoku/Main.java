package sudoku;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Sudoku =====\n" +
                "1. Play Sudoku\n" +
                "2. Solve Sudoku\n" +
                "3. Exit\n" + "\n" +
                "Choose an option:");
        String mode;
        while (true){

            mode = scanner.nextLine();
            if (!(mode.equals("1") || mode.equals("2") || mode.equals("3"))){
                System.out.println("Invalid Input");
                System.out.println("Enter 1, 2 or 3: ");
            }
            else{break;}
        }

        switch (mode){
            case "1":
                SudokuGame game = new SudokuGame();
                game.startGame();
                break;
            case "2":
                SudokuSolver solver = new SudokuSolver();
                solver.solveSudoku();
                break;
            case "3":
                System.exit(0);
        }

    }
}

package sudoku;

import java.io.IOException;

import java.util.Scanner;


public class Main {

    private static int getInt(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.isEmpty()){
                return 1000;
                }
            try{
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid Number, Try again");
                System.out.println("How many trials per number of missing cells? (Press Enter for default = 1000: ");
                scanner.nextLine();
            }
        }
    }
    public static void main (String[] args) throws IOException {

        System.out.println("How many trials per number of missing cells? (Press Enter for default = 1000: ");
        int numberOfTrials = getInt();

        System.out.println("Running experiments using " + numberOfTrials + " number of trials.");
        System.out.println("This may take a long time and may look stuck (especially at >500 trials) - grab a coffee.");
        System.out.println("--- Running Iterative experiment ---");
        IterativeSudokuExperiment.runExperiment(numberOfTrials);
        System.out.println("Success!");
        System.out.println("--- Running Random experiment ---");
        RandomSudokuExperiment.runExperiment(numberOfTrials);
        System.out.println("Success!");
        System.out.println("--- Running Recursive experiment ---");
        RecursiveSudokuExperiment.runExperiment(numberOfTrials);
        System.out.println("All experiments complete!");


    }
}

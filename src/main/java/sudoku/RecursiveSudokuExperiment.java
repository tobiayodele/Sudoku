package sudoku;

import java.nio.file.Files;
import java.nio.file.Path;

import java.io.FileWriter;
import java.io.IOException;

public class RecursiveSudokuExperiment {
    private String runTrial(int numberOfMissingCells, int trial){
        long trialStart = System.nanoTime();

        SudokuGenerator generator = new SudokuGenerator();
        long generationStart = System.nanoTime();
        SudokuBoard board = generator.generate();
        long generationEnd = System.nanoTime();
        long generationTime = generationEnd - generationStart;

        long removalStart = System.nanoTime();
        SudokuBoard puzzle = generator.recursiveRemoveCells(board,numberOfMissingCells);
        long removalEnd = System.nanoTime();
        long removalTime = removalEnd- removalStart;

        long trialEnd = System.nanoTime();
        long totalTime = trialEnd - trialStart;

        return (numberOfMissingCells + "," + trial + "," + generationTime +
                "," + removalTime + "," +  totalTime +  "\n");
    }

    public static void runExperiment(int numberOfTrials) throws IOException {
        long experimentStart = System.nanoTime();
        RecursiveSudokuExperiment experiment = new RecursiveSudokuExperiment();
        Path outputPath = Path.of("data", "Recursive", "recursive_sudoku_experiment.csv");
        Files.createDirectories(outputPath.getParent());
        FileWriter writer = new FileWriter(outputPath.toFile());
        writer.write("missing_cells,trial,generation_time_ns,removal_time_ns,total_time_ns\n");
        //becomes computationally infeasible beyond this point due to recursive blowup
        //range capped between 0-55 to keep it bounded.
        for (int i =0; i < 56; i++){
            for (int trial =1; trial <= numberOfTrials; trial ++){ // change for variable number of trials
                writer.write(experiment.runTrial(i,trial));

            }
            System.out.println("Number: " + i + " trial completed.");
        }
        writer.close();
        long experimentEnd = System.nanoTime();
        long experimentTIme = experimentEnd - experimentStart;
        double experimentTimeSeconds = experimentTIme / 1_000_000_000.0;
        System.out.println(experimentTimeSeconds + " Seconds");
    }
}




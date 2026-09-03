package sudoku;


import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;


public class IterativeSudokuExperiment {
    private String runTrial(int numberOfMissingCells, int trial){
        long trialStart = System.nanoTime();

        SudokuGenerator generator = new SudokuGenerator();
        long generationStart = System.nanoTime();
        SudokuBoard board = generator.generate();
        long generationEnd = System.nanoTime();
        long generationTime = generationEnd - generationStart;

        long removalStart = System.nanoTime();
        SudokuBoard puzzle = generator.iterativeRemoveCells(board,numberOfMissingCells);
        long removalEnd = System.nanoTime();
        long removalTime = removalEnd- removalStart;

        boolean successful = true;
        if (puzzle == null){
            successful = false;
        }

        long trialEnd = System.nanoTime();
        long totalTime = trialEnd - trialStart;


        return (numberOfMissingCells + "," + trial + "," + successful + "," + generationTime +
                "," + removalTime + "," +  totalTime +  "\n");
    }

    public static void runExperiment(int numberOfTrials) throws IOException {
        long experimentStart = System.nanoTime();
        IterativeSudokuExperiment experiment = new IterativeSudokuExperiment();
        Path outputPath = Path.of("data", "Iterative", "iterative_sudoku_experiment.csv");
        Files.createDirectories(outputPath.getParent());
        FileWriter writer = new FileWriter(outputPath.toFile());
        writer.write("missing_cells,trial,success,generation_time_ns,removal_time_ns,total_time_ns\n");
        for (int i =0; i < 65; i++){
            for (int trial =1; trial <= numberOfTrials; trial ++){
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




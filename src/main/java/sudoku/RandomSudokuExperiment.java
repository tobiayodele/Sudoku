package sudoku;

import java.nio.file.Files;
import java.nio.file.Path;

import java.io.FileWriter;
import java.io.IOException;

public class RandomSudokuExperiment {
    private String runTrial(int numberOfMissingCells, int trial){
        long trialStart = System.nanoTime();

        SudokuGenerator generator = new SudokuGenerator();
        long generationStart = System.nanoTime();
        SudokuBoard board = generator.generate();
        long generationEnd = System.nanoTime();
        long generationTime = generationEnd - generationStart;

        long removalStart = System.nanoTime();
        SudokuBoard puzzle = generator.randomRemoveCells(board,numberOfMissingCells);
        long removalEnd = System.nanoTime();
        long removalTime = removalEnd- removalStart;

        CellValidator validator = new CellValidator();
        long solutionStart = System.nanoTime();
        int solutionCount = generator.solutionCounter(puzzle,validator);
        long solutionEnd = System.nanoTime();
        long solutionTime = solutionEnd - solutionStart;

        long trialEnd = System.nanoTime();
        long totalTime = trialEnd - trialStart;

        return (numberOfMissingCells + "," + trial + "," + solutionCount + "," + generationTime +
                "," + removalTime + "," + solutionTime + "," + totalTime +  "\n");
    }

    public static void main(String[] args) throws  IOException{
        long experimentStart = System.nanoTime();
        RandomSudokuExperiment experiment = new RandomSudokuExperiment();
        Path outputPath = Path.of("data", "Random", "random_sudoku_experiment_1000.csv");
        Files.createDirectories(outputPath.getParent());
        FileWriter writer = new FileWriter(outputPath.toFile());
        writer.write("missing_cells,trial,solution_count,generation_time_ns,removal_time_ns,solution_time_ns,total_time_ns\n");
        for (int i =0; i < 65; i++){
            for (int trial =1; trial <= 1000; trial ++){
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

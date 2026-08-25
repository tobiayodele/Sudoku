package sudoku;



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

    static void main(String[] args) throws IOException {
        long experimentStart = System.nanoTime();
        RecursiveSudokuExperiment experiment = new RecursiveSudokuExperiment();
        FileWriter writer = new FileWriter("recursive_sudoku_experiment_1.csv");
        writer.write("missing_cells,trial,generation_time_ns,removal_time_ns,total_time_ns\n");
        for (int i =0; i < 65; i++){
            for (int trial =1; trial <= 1; trial ++){
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




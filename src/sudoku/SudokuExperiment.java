package sudoku;

import java.io.FileWriter;
import java.io.IOException;

public class SudokuExperiment {
    private String runTrial(int numberOfMissingCells, int trial){
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard board = generator.generate();
        SudokuBoard puzzle = generator.removeCells(board,numberOfMissingCells);
        CellValidator validator = new CellValidator();
        long solutionStart = System.nanoTime();
        int solutionCount = generator.solutionCounter(puzzle,validator);
        long solutionEnd = System.nanoTime();
        long solutionTime = solutionEnd - solutionStart;

        return (numberOfMissingCells + "," + trial + "," + solutionCount + "," + solutionTime + "\n");
    }

    static void main(String[] args) throws  IOException{
        SudokuExperiment experiment = new SudokuExperiment();
        FileWriter writer = new FileWriter("sudoku_experiment_1000.csv");
        writer.write("missing_cells,trial,solution_count,solution_time_ns\n");
        for (int i =0; i < 65; i++){
            for (int trial =1; trial <= 1000; trial ++){
                writer.write(experiment.runTrial(i,trial));
            }
        }
        writer.close();
    }

}

package sudoku;

public class Main {
    public static void main(String[] args){
        CellValidator validator = new CellValidator();
        SudokuGenerator generate = new SudokuGenerator();
        SudokuBoard board = generate.generate();
        boolean flag = false;
        SudokuBoard puzzle = new SudokuBoard();
        int count = 0;
        while (!flag){
            puzzle.setBoard(board.getBoard());
            count ++;
            puzzle = generate.randomRemoveCells(puzzle,50);
            if(generate.solutionCounter(puzzle,validator)==1){
                flag = true;
            }

        }

        puzzle.print();
        System.out.println(count);

    }

}

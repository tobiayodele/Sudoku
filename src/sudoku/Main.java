package sudoku;

public class Main {
    public static void main(String[] args){
        SudokuBoard board = new SudokuBoard();
        CellValidator validator = new CellValidator();
        SudokuGenerator generate = new SudokuGenerator();
        generate.generate();

    }

}

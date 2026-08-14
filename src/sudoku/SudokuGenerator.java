package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private SudokuBoard board;
    private CellValidator validator;

    public SudokuGenerator(){
         this.board = new SudokuBoard();
        this.validator = new CellValidator();
    }

    SudokuBoard generate(){
        fillBoard(board, validator);
        board.print();
        return board;
    }

    private boolean fillBoard(SudokuBoard board, CellValidator validator) {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));
        for (int row =0; row < 9; row ++){
            for (int column = 0; column < 9; column ++){
                if (board.isEmpty(row, column)){
                    Collections.shuffle(numbers);
                    for (int guess:numbers) {
                        if (validator.isValidCell(board.getBoard(), row, column, guess)){
                            board.setCell(row,column,guess);
                            if (fillBoard(board,validator)){
                                return true;
                            }
                            board.setCell(row, column, 0);
                        }

                    }
                    return false;
                }
            }

        }
        return true;
    }
}

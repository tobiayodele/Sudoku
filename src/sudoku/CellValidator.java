package sudoku;

public class CellValidator {
    public boolean isValidCell(int[][]board, int row, int column,  int guess){
        return isValidRow(board, row, guess)
                && (isValidColumn(board, column, guess))
                && (isValidBox(board, row, column, guess))
                && (isValidCell(board, row, column));
    }

    private boolean isValidRow(int[][]board, int row, int guess){
        for (int i= 0; i < 9 ; i++){
            if (board[row][i] == guess){
                return false;
            }
        }
        return true;
    }

    private boolean isValidColumn(int[][]board, int column, int guess){
        for (int i= 0; i < 9 ; i++){
            if (board[i][column] == guess){
                return false;
            }
        }
        return true;
    }
    private boolean isValidBox(int[][]board, int row, int column, int guess){
        // finds top left cell in a box
        int boxStartRow = (row /3) * 3;
        int boxStartColumn =(column /3) * 3;
        for (int i = 0; i <=2; i ++){
            for (int j =0; j <=2 ; j++){
                if (board[boxStartRow+i][boxStartColumn+j]== guess){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidCell (int[][] board, int row, int column){
        return board[row][column] == 0;
    }
}

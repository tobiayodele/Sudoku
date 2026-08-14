package sudoku;

import java.util.HashSet;

public class BoardValidator {
    public boolean isValidBoard(int[][]board){
        if (!isFullSudoku(board)){
            return false;
        }

        for(int i = 0; i <9; i++){
            if (!isValidRow(board[i])){
                return false;
           }
            if (!isValidColumn(board, i)){
                return false;
            }
        }

        return true;
    }

    private boolean isFullSudoku(int[][]board){
        for (int [] row : board){
            for (int cell : row){
                if( cell == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidRow(int[] row){
        HashSet<Integer> seen = new HashSet<Integer>();
        for (int i : row){
            if (seen.contains(i)){
                return false;
            }
            seen.add(i);
        }
        return true;
    }

    private boolean isValidColumn(int[][] board, int  column){
        HashSet<Integer> seen = new HashSet<Integer>();
        for (int i=0; i < 9; i++){
            int value = board[i][column];
            if (seen.contains(value)){
                return false;
            }
            seen.add(value);
        }
        return true;
    }



}

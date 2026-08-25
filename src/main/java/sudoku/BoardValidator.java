package sudoku;

import java.util.HashSet;

public class BoardValidator {
    // test class honestly why is this even here? pls delete tobi
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

        for (int row = 0; row <9; row += 3){
            for (int column =0; column < 9; column += 3){
                if(!isValidBox(board, row, column)){
                    return false;
                }
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
        // add each number in the row to a hashset and if there is a duplicate return false
        HashSet<Integer> seen = new HashSet<>();
        for (int i : row){
            if (seen.contains(i)){
                return false;
            }
            seen.add(i);
        }
        return true;
    }

    private boolean isValidColumn(int[][] board, int  column){
        HashSet<Integer> seen = new HashSet<>();
        for (int i=0; i < 9; i++){
            int value = board[i][column];
            if (seen.contains(value)){
                return false;
            }
            seen.add(value);
        }
        return true;
    }

    private boolean isValidBox(int[][] board, int row , int column){
        HashSet<Integer> seen = new HashSet<>();
        // given the top left cell in a box check for no duplicates
        for (int i =0; i <3 ; i ++){
            for (int j=0; j<3; j++){
                int value = board[row+i][column+j];
                if(seen.contains(value)){
                    return false;
                }
                seen.add(value);
            }
        }
        return true;
    }


}

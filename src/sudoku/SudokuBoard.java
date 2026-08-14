package sudoku;

public class SudokuBoard {
    private int [][] board;

    public SudokuBoard(){
        board =  new int [9][9];
    }

     void print (){
        for (int [] row : board){
            for (int cell : row){
                if (cell == 0){
                    System.out.print("[" + " " + "]"+ " ");
                }
                else{
                    System.out.print("[" + cell + "]"+ " ");
                }

            }
            System.out.println();
        }
    }

    public boolean isEmpty( int row,int  column){
        return board[row][column] == 0;
    }

    public int getCell(int row, int column){
        return board[row][column];
    }

    public void setCell(int row, int column, int input){
        board[row][column] = input;
    }

    public int[][] getBoard(){
        return board;
    }

}

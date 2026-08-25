package sudoku;

public class SudokuBoard {
    private int [][] board;

    public SudokuBoard(){
        board =  new int [9][9];
    }

    public void print () {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";

        System.out.println("    1   2   3  " + red + "|" + reset
                + "  4   5   6  " + red + "|" + reset + "  7   8   9");

        System.out.println("   " + red + "____________|_____________|____________" + reset);
        for (int row = 0; row < 9; row++) {
            System.out.print((row + 1) + "  ");
            for (int column = 0; column < 9; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print(red + "| " + reset);
                }
                if (this.board[row][column] == 0) {
                    System.out.print("[ ] ");
                }
                else {
                    System.out.print("[" + this.board[row][column] + "] ");
                }
            }
            System.out.println();
            if (row % 3 == 2 && row != 8) {
                System.out.println("   " + red + "____________|_____________|____________" + reset);
            }
        }
        System.out.println("   " + red + "____________|_____________|____________" + reset);
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
        return this.board;
    }

    public void setBoard(int [][] board){
        // construct a new board and fill in the values from the old board.
        this.board = new int[9][9];
        for(int row =0; row <9; row ++){
            for (int column = 0; column < 9; column ++){
                setCell(row,column,board[row][column]);
            }
        }
    }
}

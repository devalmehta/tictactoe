import java.util.Scanner;

public class TicTacToe {


    // Tic Tac Toe board
    private int[][] ticTacToe = new int[3][3];

    public boolean isGameFinish() {
        return gameFinish;
    }

    private void setGameFinish(boolean gameFinish) {
        this.gameFinish = gameFinish;
    }

    private boolean gameFinish = false;

    private void printBoard() {
        System.out.println();
        for(int i =0; i<ticTacToe.length; i++) {
            for (int j =0; j < ticTacToe.length; j++) {
                System.out.print(" ");
                System.out.print(ticTacToe[i][j]);

            }
            System.out.println();
        }
    }

    public void playTicTacToe() {
        System.out.println("==========================================================");
        System.out.println("Instructions : Board rows should be considered as 0, 1, 2");
        System.out.println("and columns should be considered as 0 ,1 ,2");
        System.out.println("when asked indicate your move as 11, 22, 33 etc where first");
        System.out.println("number indicates the row and second number indicates column.");
        System.out.println("Program will print board after every move. Player will use 1");
        System.out.println("while application will use 2 to indicate the move.");
        System.out.println("==========================================================");
        System.out.println("Would you like to go first: Y/N?");
        Scanner in = new Scanner(System.in);
        String yn = in.nextLine();
        if("Y".equalsIgnoreCase(yn)) {
            while(!gameFinish)  {
                int playersMove = move("Player");
                setTicTacToe(playersMove, 1);
                printBoard();
                int applicationMove = move("Application");
                if(applicationMove != -11)
                    setTicTacToe(applicationMove, 2);
                printBoard();
                isGameOver();
            }

        } else {
            while(!gameFinish){
                int playersMove = move("Application");
                setTicTacToe(playersMove, 2);
                printBoard();
                int applicationMove = move("Player");
                if(applicationMove != -11)
                    setTicTacToe(applicationMove, 1);
                printBoard();
                isGameOver();
            }
        }
    }

    private int move(String player) {
        if(player == "Player") {
            System.out.println("Enter your move :");
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        } else {
            return determineNextMoveForApplication();
        }

    }

    private int determineNextMoveForApplication() {
        int move =0;
        move = checkRows();
        if(move != -11)
            move = theRowMove(move);
        if(move == -11){
            move = checkColumns();
            if(move != -11) {
                move = theColumnMove(move);
            }
        }

        if (move == -11) {
            move = checkDiagonal();
//            if(move == -11) {
//                move = theDiagonalMove(move);
//            }
        }
        if(move == -11) {
            for(int i =0; i<this.ticTacToe.length;i++){
                for(int j=0; j<this.ticTacToe.length;j++) {
                    if(this.ticTacToe[i][j] == 0){
                        return Integer.valueOf(Integer.toString(i) + Integer.toString(j));
                    }
                }
            }
        }
        return move;
    }

    private int theColumnMove(int move) {
        int column = getColumn(move);
        int i = 0;
        while(i< this.ticTacToe.length){
            if(this.ticTacToe[i][column] ==0) {
                return Integer.valueOf(Integer.toString(i) + Integer.toString(column));
            } else {
                i++;
            }
        }
        return -11;
    }

    private int theRowMove(int move) {
        int row = getRowFromZero(move);
        int i = 0;
        while(i< this.ticTacToe.length) {
            if(this.ticTacToe[row][i] == 0) {
                return Integer.valueOf(Integer.toString(row) + Integer.toString(i));
            }else{
                i++;
            }
        }
        return -11;
    }

    private int getRowFromZero(int move) {
        int row = 0;
        if(String.valueOf(move).length() == 1) {
            row = 0;
        } else {
            row = move/10;
        }
        return row;
    }

    private int checkDiagonal() {
        for(int i=0, j=0; i<this.ticTacToe.length; i++,j++) {
            int count = 0;
            if(this.ticTacToe[i][j] == 1) {
                count++;
                if(count == 2)
                    return Integer.valueOf(Integer.toString(i) + Integer.toString(j));
            }
        }
        for(int i=this.ticTacToe.length-1, j= this.ticTacToe.length-1;i>=0; i--,j--) {
            int count = 0;
            if(this.ticTacToe[i][j] ==1) {
                count++;
                if(count == 2)
                    return Integer.valueOf(Integer.toString(i) + Integer.toString(j));
            }
        }
        return -11;
    }

    private int checkColumns() {
        if(gameJustStarted()) {
            return 22;
        }
        for(int i=0; i<this.ticTacToe.length; i++){
            int count = 0;
            for(int j =0 ; j<this.ticTacToe.length; j++) {
                if(this.ticTacToe[j][i] == 1) {
                    count++;
                }
                if (count == 2) {
                    return Integer.valueOf(Integer.toString(i) + Integer.toString(j));
                }
            }
        }
        return -11;
    }

    private int checkRows() {
        if(gameJustStarted()) {
            return 22;
        }
        for(int i=0; i<this.ticTacToe.length; i++){
            int count = 0;
            for(int j =0 ; j<this.ticTacToe.length; j++) {
                if(this.ticTacToe[i][j] == 1) {
                    count++;
                }
                if (count == 2) {
                    return Integer.valueOf(Integer.toString(i) + Integer.toString(j));
                }
            }
        }
        return -11;
    }

    private boolean gameJustStarted() {
        for (int[] ints : ticTacToe) {
            for (int j = 0; j < ticTacToe.length; j++) {
                if (ints[j] != 0)
                    return false;
            }
        }
        return true;

    }

    private void isGameOver() {
        if(isTicTacToeFull()) {
            this.setGameFinish(true);
        }
    }

    /**
     * Check to see if board is full
     * @return return the result in boolean
     */
    private boolean isTicTacToeFull() {
        for (int[] ints : ticTacToe) {
            for (int j = 0; j < ticTacToe.length; j++) {
                if (ints[j] == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Sets the board array with move
     * @param playersMove playersMove row column combination
     * @param move it could be either 1 or 2
     */
    private void setTicTacToe(int playersMove, int move) {
        if(isValidMove(playersMove)) {
            int row = getRow(playersMove);
            int column = getColumn(playersMove);
            this.ticTacToe[row][column] = move;
        }
    }

    private boolean isValidMove(int playersMove) {

        int row = getRow(playersMove);
        int column = getColumn(playersMove);
        if(row>3 || column > 3)
            return false;
        return this.ticTacToe[row][column] == 0;
    }

    private int getColumn(int playersMove) {
        if(String.valueOf(playersMove).length() == 1) {
            return playersMove;
        }
        return playersMove % 10;
    }

    private int getRow(int playersMove) {
        return this.getRowFromZero(playersMove);
    }
}

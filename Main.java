//Jehad Hamayel 1200348
//Katya Kobari  1201478

package projectAI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    //define game players.
    static char playerNow = '-';
    static char player1 = '■';
    static char player2 = '□';
    public static void main(String[] args) {
        char[][] MagneticCaveBoard = new char[8][8];
        initializeBoard(MagneticCaveBoard);
        printBoard(MagneticCaveBoard);

        int option=0;
        do { //Menu options chosen by the user
            System.out.println("*** Magnetic Cave Game Choices ***");
            System.out.println("1. Manual entry for both players");
            System.out.println("2. Manual entry for " + player1 + "'s moves & automatic moves for " + player2);
            System.out.println("3. Manual entry for " + player2 + "'s moves & automatic moves for " + player1);
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    bothManual(player1, player2);
                    break;
                case 2:
                    manualAndAutomatic(player1,player2);
                    break;
                case 3:
                    manualAndAutomatic(player2,player1);
                    break;
                case 4:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (option != 4);
    }
//-------------------Game Method's----------------------------------------------
    public static void printBoard(char[][] MagneticCaveBoard) { // Print the game board
        System.out.println("  A B C D E F G H");
        for (int row = 0; row < 8; row++) {
            System.out.print((row + 1) + " ");
            for (int column = 0; column < 8; column++) {
                System.out.print(MagneticCaveBoard[row][column] + " ");
            }
            System.out.println(row + 1);
        }
        System.out.println("  A B C D E F G H");
    }

    public static void initializeBoard(char[][] MagneticCaveBoard) { // Initialize the game board with empty cells[-].
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                MagneticCaveBoard[row][column] = '-'; // Initialize all cells as empty
            }
        }
    }

    public static int evaluationFunction(char[][] MagneticCaveBoard, char player) {
        // Determine the opposite player
        char TheOppositePlayer = '-';
        if (player == '□')
            TheOppositePlayer = '■';
        else if (player == '■')
            TheOppositePlayer = '□';
        // Calculate evaluation scores for the current player and the opposite player
        int evaluationFunctionForP = pointsCalculation(MagneticCaveBoard, player);
        int evaluationFunctionForO = pointsCalculation(MagneticCaveBoard, TheOppositePlayer);
        // Calculate the final evaluation score which is equal --> the difference between the player score and the opposite score
        int evaluationFunctionRes = evaluationFunctionForP - evaluationFunctionForO;
        return evaluationFunctionRes;
    }
    //This method calculates the points for a given player in the game-->it takes the current state of the game board
    // and the player as parameters and returns the total points of this player.
    public static int pointsCalculation(char[][] MagneticCaveBoard, char player) {
        int points = 0;
        // Points Calculation for rows
        int expectedChoice = 0;
        int RelayGamePieces = 0;
        for (int row = 0; row < 8; row++) {
            for (int firstcolumn = 0, secondcolumn = 1, thirdcolumn = 2, forthcolumn = 3, lastcolumn = 4; firstcolumn <= 3; firstcolumn++, secondcolumn++, thirdcolumn++, forthcolumn++, lastcolumn++) {//c=0
                RelayGamePieces = 0;
                expectedChoice = 0;
                if (MagneticCaveBoard[row][firstcolumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[row][firstcolumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[row][secondcolumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[row][secondcolumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[row][thirdcolumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[row][thirdcolumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[row][forthcolumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[row][forthcolumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[row][lastcolumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[row][lastcolumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                // System.out.println("player: " + player + " rows:" + row + " consecutiveCount: " + RelayGamePieces + ",   emptyCount: " + expectedChoice);
                points += HouristicAccount(RelayGamePieces, expectedChoice);

            }

        }
        // // Points Calculation for columns
        for (int col = 0; col < 8; col++) {
            for (int firstRow = 0, secondRow = 1, thirdRow = 2, forthRow = 3, lastRow = 4; firstRow <= 3; firstRow++, secondRow++, thirdRow++, forthRow++, lastRow++) {
                RelayGamePieces = 0;
                expectedChoice = 0;
                if (MagneticCaveBoard[firstRow][col] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[firstRow][col] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[secondRow][col] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[secondRow][col] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[thirdRow][col] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[thirdRow][col] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[forthRow][col] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[forthRow][col] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[lastRow][col] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[lastRow][col] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }

                //  System.out.println("player: " + player + " columns:" + col + " consecutiveCount: " + RelayGamePieces + ",   emptyCount: " + expectedChoice);
                points += HouristicAccount(RelayGamePieces, expectedChoice);
            }
        }
        // Points Calculation for diagonals from the top right to the bottom left
        int firstcaveRow = 0;
        int secondcaveRow = 0;
        int thirdcaveRow = 0;
        int forthcaveRow = 0;
        int lastcaveRow = 0;
        int firstcaveColumn = 0;
        int secondcaveColumn = 0;
        int thirdcaveColumn = 0;
        int forthcaveColumn = 0;
        int lastcaveColumn = 0;
        for (int row = 0; row <= 3; row++) {
            firstcaveRow = row;
            secondcaveRow = row + 1;
            thirdcaveRow = row + 2;
            forthcaveRow = row + 3;
            lastcaveRow = row + 4;
            for (int column = 4; column <= 7; column++) {

                firstcaveColumn = column;
                secondcaveColumn = column - 1;
                thirdcaveColumn = column - 2;
                forthcaveColumn = column - 3;
                lastcaveColumn = column - 4;

                RelayGamePieces = 0;
                expectedChoice = 0;
                if (MagneticCaveBoard[firstcaveRow][firstcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[firstcaveRow][firstcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[secondcaveRow][secondcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[secondcaveRow][secondcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[thirdcaveRow][thirdcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[thirdcaveRow][thirdcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[forthcaveRow][forthcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[forthcaveRow][forthcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[lastcaveRow][lastcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[lastcaveRow][lastcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }

                // System.out.println("player: " + player + " diagonals (top-right to bottom-left): consecutiveCount: " + RelayGamePieces + ",   emptyCount: " + expectedChoice);
                points += HouristicAccount(RelayGamePieces, expectedChoice);
            }
        }
        // Points Calculation for diagonals from the top left to the bottom right
        firstcaveRow = 0;
        secondcaveRow = 0;
        thirdcaveRow = 0;
        forthcaveRow = 0;
        lastcaveRow = 0;
        firstcaveColumn = 0;
        secondcaveColumn = 0;
        thirdcaveColumn = 0;
        forthcaveColumn = 0;
        lastcaveColumn = 0;
        for (int row = 0; row <= 3; row++) {
            firstcaveRow = row;
            secondcaveRow = row + 1;
            thirdcaveRow = row + 2;
            forthcaveRow = row + 3;
            lastcaveRow = row + 4;
            for (int column = 0; column <= 3; column++) {
                firstcaveColumn = column;
                secondcaveColumn = column + 1;
                thirdcaveColumn = column + 2;
                forthcaveColumn = column + 3;
                lastcaveColumn = column + 4;

                RelayGamePieces = 0;
                expectedChoice = 0;
                if (MagneticCaveBoard[firstcaveRow][firstcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[firstcaveRow][firstcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[secondcaveRow][secondcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[secondcaveRow][secondcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[thirdcaveRow][thirdcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[thirdcaveRow][thirdcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[forthcaveRow][forthcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[forthcaveRow][forthcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }
                if (MagneticCaveBoard[lastcaveRow][lastcaveColumn] == player) {
                    RelayGamePieces++;
                } else if (MagneticCaveBoard[lastcaveRow][lastcaveColumn] == '-') {
                    expectedChoice++;
                } else {
                    continue;
                }

                // System.out.println("player: " + player + " diagonals (top-left to bottom-right): consecutiveCount: " + RelayGamePieces + ",   emptyCount: " + expectedChoice);
                points += HouristicAccount(RelayGamePieces, expectedChoice);
            }
        }


        return points;
    }

    public static double HouristicAccount(int RelayGamePieces, int expectedChoice) { //Calculates the heuristic score based on the number of relay game pieces and expected choice.
        double score = 0;
           // If there are 5 relay game pieces--> win
        if (RelayGamePieces == 5) {
            score = Math.pow(10,RelayGamePieces+2);
        } else if (RelayGamePieces + expectedChoice > 4) {
            // indicating a potential win or close to a win
            if (RelayGamePieces == 4) {
                score =Math.pow(10, RelayGamePieces+1); //score indicates a winning position.
            } else if (RelayGamePieces == 3) {
                score = Math.pow(10, RelayGamePieces+1 ); //score indicates a favorable position.
            } else if (RelayGamePieces == 2) {
                score = Math.pow(10, RelayGamePieces +1); //score indicates a decent position.
            }
        }
        return score;
    }


    public static List<char[][]> generateLegalMoves(char[][] magneticCaveBoard, char player) { //Generates a list of legal moves for the given player.
        List<char[][]> possibleMoves = new ArrayList<>(); //list to store the legal moves.
        for (int i = 0; i < magneticCaveBoard.length; i++) {
            for (int j = 0; j < magneticCaveBoard[0].length; j++) {
                if (legalMove(magneticCaveBoard,i, j)) { // If the move is a legal move for the player so create a new game board.
                    char[][] newBoard = copyBoard(magneticCaveBoard);
                    newBoard[i][j] = player;
                    possibleMoves.add(newBoard); //add it to the list.
                }
            }
        }
        return possibleMoves;
    }


    public static char[][] copyBoard(char[][] board) { // Creates a copy of the game board passed.
        char[][] copyBoard = new char[8][8];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copyBoard[i], 0, board[0].length);
        }
        return copyBoard;

    }

    public static int magneticCaveTree(char[][] magneticCaveBoard, int depth, int alpha, int beta, int maxPlayer, char player) { //build alpha_beta tree for the game.
        if (depth == 0) {
            //depth=0-->Reached the max depth return the evaluation score
            return evaluationFunction(magneticCaveBoard, playerNow);
        }
        if (maxPlayer == 1) {
            // Max player turn
            if(player==player1)
                player=player2; // Switch to the opponent's player
            else if(player==player2)
                player=player1;
            int max =Integer.MIN_VALUE;
            List<char[][]> legalMoves = generateLegalMoves(magneticCaveBoard, player);
            if (legalMoves.isEmpty()) {
                return -1; //No legal moves available.
            }
            for (int i = 0; i < legalMoves.size(); i++) {
                char[][] move = legalMoves.get(i);
                int cost = magneticCaveTree(move, (depth - 1), alpha, beta, 0, player); // Recursive call for the second player turn
                max = Math.max(max, cost);
                alpha = Math.max(alpha, cost);
                if (beta <= alpha) { // Beta cutoff
                    break;
                }
            }
            return max;
        } else if (maxPlayer == 0){ //Min player turn
            if(player==player1)
                player=player2;
            else if(player==player2)
                player=player1;
            int min = Integer.MAX_VALUE;
            List<char[][]> legalMoves = generateLegalMoves(magneticCaveBoard, player);
            if (legalMoves.isEmpty()) {
                return -1;
            }
            for (int i = 0; i < legalMoves.size(); i++) {
                char[][] move = legalMoves.get(i);
                int cost = magneticCaveTree(move, (depth - 1), alpha, beta, 1, player);
                min = Math.min(min, cost);
                beta = Math.min(beta, cost);
                if (beta <= alpha) { // Alpha cutoff
                    break;
                }
            }
            return min;
        }
        return 0;
    }

    public static boolean legalMove(char[][] magneticCaveBoard, int row, int column) { //Checks if a move is legal on the magneticCaveBoard.
        int boardSize = magneticCaveBoard.length;
        // Check if the row or column is out of the board.
        if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
            return false;
        }
        // Check if the position is not empty
        if (magneticCaveBoard[row][column] != '-') {
            return false;
        }
        // Check if the position is at column 0 or 7
        if ((column == 0 || column == 7) && magneticCaveBoard[row][column] == '-') {
            return true;
        } else { // there is an adjacent occupied position
            return (magneticCaveBoard[row][column - 1] != '-' || magneticCaveBoard[row][column + 1] != '-');
        }
    }

    public static boolean checkWin(char[][] magneticCaveBoard, char player) { //Check if the player win the game.
        int rows = magneticCaveBoard.length;
        int columns = magneticCaveBoard[0].length;

        // Check rows
        for (int row = 0; row < rows; row++) {
            int count = 0;
            for (int column = 0; column < columns; column++) {
                if (magneticCaveBoard[row][column] == player) {
                    count++;
                    if (count == 5) {
                        return true; // Player has won
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check columns
        for (int column = 0; column < columns; column++) {
            int count = 0;
            for (int row = 0; row < rows; row++) {
                if (magneticCaveBoard[row][column] == player) {
                    count++;
                    if (count == 5) {
                        return true; // Player has won
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonals (left to right)
        for (int row = 0; row < rows - 4; row++) {
            for (int column = 0; column < columns - 4; column++) {
                if (magneticCaveBoard[row][column] == player &&
                        magneticCaveBoard[row + 1][column + 1] == player &&
                        magneticCaveBoard[row + 2][column + 2] == player &&
                        magneticCaveBoard[row + 3][column + 3] == player &&
                        magneticCaveBoard[row + 4][column + 4] == player) {
                    return true; // Player has won
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < rows - 4; row++) {
            for (int column = 4; column < columns; column++) {
                if (magneticCaveBoard[row][column] == player &&
                        magneticCaveBoard[row + 1][column - 1] == player &&
                        magneticCaveBoard[row + 2][column - 2] == player &&
                        magneticCaveBoard[row + 3][column - 3] == player &&
                        magneticCaveBoard[row + 4][column - 4] == player) {
                    return true; // Player has won
                }
            }
        }

        return false;
    }

    public static boolean FullBored(char[][] magneticCaveBoard) { //Checks if the magneticCaveBoard is full
        for (int i = 0; i < magneticCaveBoard.length; i++) {
            for (int j = 0; j < magneticCaveBoard[0].length; j++) {
                if (magneticCaveBoard[i][j] == '-') {
                    return false; //empty position is found
                }
            }
        }
        return true;
    }
    public static char[][] generateNextMove(char[][] magneticCaveBoard, char player) { //Generates the next move for a given player on the magneticCaveBoard.
        int bestScore = Integer.MIN_VALUE;
        char[][] bestMove = null;
        // Generate all possible legal moves.
        List<char[][]> possibleMoves = generateLegalMoves(magneticCaveBoard, player);
        // Evaluate each move and choose one with the max score.
        for (char[][] move : possibleMoves) {
            playerNow=player;
            int score = magneticCaveTree(move,4, Integer.MIN_VALUE, Integer.MAX_VALUE, 0 , player);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        System.out.println(bestScore); //print best score.
        return bestMove;
    }
//----------------Game options---------------------------------------------------------------------------------------------------

    public static void bothManual(char player, char player2) { // Method for manual entry by both players
        char[][] magneticCaveBoard = new char[8][8];
        initializeBoard(magneticCaveBoard);
        printBoard(magneticCaveBoard);
        boolean flag = true;
        while (flag) {
            System.out.println("Player " + player + "'s turn. Enter your next move [Example--> B2]: ");
            String nextMove = scanner.next();
            nextMove=nextMove.toUpperCase();
            int row = Character.getNumericValue(nextMove.charAt(1)) - 1;
            int column = nextMove.charAt(0) - 'A';
            if (legalMove(magneticCaveBoard, row, column)) { //Check if the move is a legal move for Player 1
                magneticCaveBoard[row][column] = player;
                printBoard(magneticCaveBoard);
                if (checkWin(magneticCaveBoard, player)) { //check win state for player 1.
                    System.out.println("Player " + player + " wins!");
                    flag = false;
                    break;
                }
                if (FullBored(magneticCaveBoard)) { //check if the board is full.
                    System.out.println("The game ends...!");
                    flag = false;
                    break;
                }
                boolean validMove = false; //Player 2 turn--> manual
                while (!validMove) {
                    System.out.println("Player " + player2 + "'s turn. Enter your next move [Ex-->B2]: ");
                    nextMove = scanner.next();
                    nextMove=nextMove.toUpperCase();
                    row = Character.getNumericValue(nextMove.charAt(1)) - 1;
                    column = nextMove.charAt(0) - 'A';
                    if (legalMove(magneticCaveBoard, row, column)) {
                        magneticCaveBoard[row][column] = player2;
                        printBoard(magneticCaveBoard);
                        if (checkWin(magneticCaveBoard, player2)) {
                            System.out.println("Player " + player2 + " wins!");
                            flag = false;
                            break;
                        }
                        if (FullBored(magneticCaveBoard)) {
                            System.out.println("The game ends...!");
                            flag = false;
                            break;
                        }
                        validMove = true; // Break out of the loop if the move is valid
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
    public static void manualAndAutomatic(char player1, char player2) { // Method for manual entry by one player and automatic moves by the other player
        // Create a new Magnetic Cave board and initialize it
        char[][] magneticCaveBoard = new char[8][8];
        initializeBoard(magneticCaveBoard);
        printBoard(magneticCaveBoard);
        boolean flag = true; //flag to know if the game is ended

        while (flag) {
        	 System.out.println("Player " + player1 + "'s turn. Enter your next move [Example--> B2]: ");
             String nextMove = scanner.next();
             nextMove=nextMove.toUpperCase();
             int row = Character.getNumericValue(nextMove.charAt(1)) - 1;
             int column = nextMove.charAt(0) - 'A';
             // Check if the move is a legal move
             if (legalMove(magneticCaveBoard, row, column)) {
                 magneticCaveBoard[row][column] = player1;
                 printBoard(magneticCaveBoard);
                 // Check if player 1 wins
                 if (checkWin(magneticCaveBoard, player1)) {
                     System.out.println("Player " + player1 + " wins!");
                     flag = false;
                     break;
                 }
                 //Check if the board is full
                 if (FullBored(magneticCaveBoard)) {
                     System.out.println("The game ends...!");
                     flag = false;
                     break;
                 }
                 // Player 2 turn-->automatic move
                    System.out.println("Player " + player2 + "'s turn. Automatic move:");
                    double startTime = System.currentTimeMillis();
                    //Generate the next move for player 2.
                    magneticCaveBoard = generateNextMove(magneticCaveBoard, player2);
                    printBoard(magneticCaveBoard);
                    double endTime = System.currentTimeMillis();
                    double elapsedTimeMillis = endTime - startTime;
                    double elapsedTimeSeconds = elapsedTimeMillis / 1000;
                    System.out.println("The time was taken: " + elapsedTimeSeconds+" Seconds" );
                    if (checkWin(magneticCaveBoard, player2)) {
                        System.out.println("Player " + player2 + " wins!");
                        flag = false;
                        break;
                    }

                    if (FullBored(magneticCaveBoard)) {
                        System.out.println("The game ends...!");
                        flag = false;
                        break;
                    }
                   
                
            } else {
                System.out.println("Invalid move. Try again.");
            }
            
        }
    }
}
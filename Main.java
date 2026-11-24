/**
 * @author me
 */
 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void printBoard(char[] board){
	    /**
	     * prints the board
	     * @array for printing the board
	     * @return void
	     */
	    String ansiReset = "\u001B[0m";
	    String stringOne;
	    String stringTwo;
	    String stringThree;
	    System.out.println("   a  b  c");
	    for(int row = 0; row < 3; row++){
	        if(board[row*3] == 'x'){
	            stringOne = "\u001B[32m";
	        }
	        else if(board[row*3] == 'o'){
	            stringOne = "\u001B[31m";
	        }
	        else{
	            stringOne = "";
	        }
	        if(board[row*3 + 1] == 'x'){
	            stringTwo = "\u001B[32m";
	        }
	        else if(board[row*3+1] == 'o'){
	            stringTwo = "\u001B[31m";
	        }
	        else{
	            stringTwo = "";
	        }
	        if(board[row*3 + 2] == 'x'){
	            stringThree = "\u001B[32m";
	        }
	        else if(board[row*3+2] == 'o'){
	            stringThree = "\u001B[31m";
	        }
	        else{
	            stringThree = "";
	        }
	        System.out.printf("%d [%s%c%s][%s%c%s][%s%c%s]\n", row+1,stringOne,board[row * 3],ansiReset,stringTwo,board[row * 3 + 1],ansiReset,stringThree,board[row * 3 + 2],ansiReset);
	    }
	}
	public static boolean[] checkWin(char[] board){
	    /**
	     * decides weather or not the game is over
	     * @array of boolean values that decide who won or a tie
	     * @return boolean[]
	     */
	    boolean gameOver[] = {false, false}; //the first value is for if the player wins, the second for if the bot wins
	    int winLines[][] = {
	        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //rows
	        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //columns
	        {0, 4, 8}, {2, 4, 6} //diagnal
	    };
	    for(int line[] : winLines){
	        if(board[line[0]] == 'x' && board[line[1]] == 'x' && board[line[2]] == 'x'){
	            gameOver[0] = true;
	        }
	        else if(board[line[0]] == 'o' && board[line[1]] == 'o' && board[line[2]] == 'o'){
	            gameOver[1] = true;
	        }
	        else if(board[0] != ' ' && board[1] != ' ' && 
	        board[2] != ' ' && board[3] != ' ' && board[4] != ' ' && 
	        board[5] != ' ' && board[6] != ' ' && board[7] != ' ' && 
	        board[8] != ' '){
	            gameOver[0] = true;
	            gameOver[1] = true;
	        }
	    }
	    
	    return gameOver;
	}
	public static int botMove(char[] board){
	    /**
	     * decides the bots move
	     * @array so the bot can read the board
	     * @return int
	     */
	     boolean moveValid = false;
	     Random random = new Random();
	     int botIndex;
	     do{
	         botIndex = random.nextInt(8);
	         if(board[botIndex] == ' '){
	             moveValid = true;
	         }
	     }while(!moveValid);
	     return botIndex;
	}
    public static void main(String[] args) {
    /**
     * A simple game of tic tac toe
     *
     * @param args the command line arguments
     * @return void
     */
        Scanner scanner = new Scanner(System.in);
        
        String userInput;
        boolean gameOver[] = {false, false};
        char[] board = new char[9];
        Arrays.fill(board, ' ');
        int row;
        int column;
        int index;
        boolean stopPlaying = false;
        char stopPlayingInput;
        int points = 0;
        int gamesPlayed = 0;
        do{
            printBoard(board);
            System.out.println("To make your first move, enter the column letter followed by the row number number");
            do{
                System.out.print("Enter your move: ");
                userInput = scanner.nextLine().toLowerCase();
                    if(userInput.length() == 2 && userInput.charAt(0) >= 'a' && userInput.charAt(0) <= 'c' && userInput.charAt(1) >= '1' && userInput.charAt(1) <= '3'){
                        row = userInput.charAt(0) - 'a';
                        column = userInput.charAt(1) - '1';
                        index = column*3 + row;
                        if(board[index] == ' '){
                            board[index] = 'x';
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            gameOver = checkWin(board);
                            if(gameOver[0] == false && gameOver[1] == false){
                                board[botMove(board)] = 'o';
                                gameOver = checkWin(board);
                            }
                            printBoard(board);
                        }
                        else{
                            System.out.println("That spot is taken");
                        }
                    }
                else{
                    System.out.println("that's not a vaild input");
                }
            }while(gameOver[0] == false && gameOver[1] == false);
            if(gameOver[0] == true && gameOver[1] == false){
                System.out.println("Congratulations player, you won!!");
                gamesPlayed++;
                points++;
                System.out.printf("You have won %d out of %d games", points, gamesPlayed);
            }
            else if(gameOver[0] == false && gameOver[1] == true){
                System.out.println("Sorry player, you didn't win this one");
                gamePlayed++;
            }
            else{
                System.out.println("It's a tie!!");
            }
            System.out.print("\nIf you would like to keep playing type \"y\": ");
            stopPlayingInput = scanner.next().charAt(0);
            if(stopPlayingInput != 'y'){
                stopPlaying = true;
            }
            else{
                Arrays.fill(board, ' ');
                gameOver[0] = false;
                gameOver[1] = false;
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }while(!stopPlaying);
    }
}

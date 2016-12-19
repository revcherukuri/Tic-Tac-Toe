package TicTacToe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	
	private char[][] board;
	private char player; //'X'

	/* 
	 * Step 6: Main Method for Final Step - Delete your main method 
	 * and uncomment this one. 
	 * Runs the game by getting input from the user, making the 
	 * appropriate moves, and prints who won or if it was a stalemate. 
	 */ 
	static int level = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		System.out.println("Welcome to tic-tac-toe");
		System.out.println("Do you want to play the easy level (0) or the difficult level (1) ?");
		level = in.nextInt();
		System.out.println("Enter coordinates for your move following the X and O prompts");

		boolean ret = false;
		game.player = 'X';
		while(!game.stalemate()) {
			//Print the game
			game.print();

			String s = "";
			//Detect if it is computer's play.  If so, add the AI to find the right square
			
			if(game.player == 'X') {
				//Prompt player for their move
				s = in.next();
			}
			else {
				//Detect if it is computer's play.  If so, add the AI to find the right square
				s = game.computerPlay();
			}
			
			ret = game.play(s);
			
			if (ret == false) {
				//prompt the user to enter between 0, 0 and 2, 2 and loop
				continue;
			}
			
			if (game.won() == true) {
				break;
			}
			
			//
			//Loop while the method play does not return true when given their move.
			//Body of loop should ask for a different move


			//If the game is won, call break; 
			

			//Switch the turn
			game.switchTurn();
		}
		
		game.print();
		game.stalemate();
		if(game.won()==true){
			System.out.println("Player "+game.getPlayer()+" Wins!!!!");
		} else if(game.stalemate() == true){
			System.out.println("Stalemate");
		}
	} 
	
	/* 
	 * Instantiate board to be a 3 by 3 char array of spaces.
	 * Set player to be 'X'.
	 */
	public TicTacToe() {
		/*
		 * Step 1: create an empty board, with an initial value
		 * of a space (' ')
		 */
		board = new char[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public String computerPlay() {
		
		String winningPlace = findWinningSquare(0, 0, 0, 1, 0, 2, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(1,0,1,1,1,2, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(2,0,2,1,2,2, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(0,0,1,0,2,0, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(0,1,1,1,2,1, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(0,2,1,2,2,2, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(0,0,1,1,2,2, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		winningPlace = findWinningSquare(0,2,1,1,2,0, 'O');
		if(winningPlace != null)
		{
			return winningPlace;
		}
		String losingPlace = findWinningSquare(0, 0, 0, 1, 0, 2, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(1,0,1,1,1,2, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(2,0,2,1,2,2, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(0,0,1,0,2,0, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(0,1,1,1,2,1, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(0,2,1,2,2,2, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(0,0,1,1,2,2, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		losingPlace = findWinningSquare(0,2,1,1,2,0, 'X');
		if(losingPlace != null)
		{
			return losingPlace;
		}
		
		if(level == 0)
		{
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ')
				{
					return translateToString(i,j);
				}
			}
		}
	}
		else if(level == 1)
		{
		//find a random enpty square
			Random rand = new Random();
			String ret = null;
			while(ret == null)
			{
				int randomrow = rand.nextInt(3);
				int randomcol = rand.nextInt(3);
				if(board[randomrow][randomcol] == ' ')
				{
					ret = translateToString(randomrow,randomcol);
				}
			}
		
		return ret;
		
	}
		return "";
	}
	public String translateToString(int row, int col) {
		String ret = "";
		if (row == 0) {
			ret = "A";
		}
		else if (row == 1)
		{
			ret = "B";
		}
		else if (row == 2)
		{
			ret = "C";
		}
		else {
			return null;
		}
		
		ret = ret + (col + 1);
		return ret;	
	}
	
	public String findWinningSquare(int row1, int col1, int row2, int col2, int row3, int col3, char player) {
		String retString = null;
		if((board[row1][col1] == player) && (board[row2][col2] == player) && (board[row3][col3] == ' '))
		{
			return translateToString(row3, col3);
		}
		if((board[row1][col1] == player) && (board[row2][col2] == ' ') && (board[row3][col3] == player))
		{
			return translateToString(row2, col2);
		}
		if((board[row1][col1] == ' ') && (board[row2][col2] == player) && (board[row3][col3] == player))
		{
			return translateToString(row1, col1);
		}
		return null;
	}
	
	/* 
	 * If s represents a valid move, add the current player's symbol to the board and return true.
	 * Otherwise return false.
	 */
	public boolean play(String s) {
		/* Step 2: Fill in here with your own
		 * play logic, and replace the return with you
		 * 		* own.
		 */ 
		
		char rowChar = s.charAt(0);
		int col = Integer.parseInt(""+s.charAt(1))-1;
		
		int row = -1;
		if (rowChar == 'A')
			row = 0;
		else if (rowChar == 'B')
			row = 1;
		else if (rowChar == 'C')
			row = 2;
			
		// validate the value
		if(row < 0 | row > 2 || col < 0 || col > 2) {
			System.out.print("Invalid entry. Try Again!");
			return false;
		}
			 
		if (board[row][col] != ' ') {
			System.out.print("Invalid entry. Try Again!");
			return false;
		}
		
		board[row][col] = player;
		
		// add the logic for the computer to play
		
		return true; 
	}

	/*
	 * Switches the current player from X to O, or O to X.
	 */
	public void switchTurn() {
		// Step 3: Fill in with your code to toggle between
		if (player == 'O') {
			player = 'X';
			return;
		}
		if (player == 'X') {
			player = 'O';
		}
	}
	
	/*
	 * Returns true if the current player has won the game.
	 * Three in a row, column or either diagonal.
	 * Otherwise, return false.
	 */
	public boolean won() {
		int A = 0;
		int B = 1;
		int C = 2;
		
		if ((board[0][0]=='O')&&(board[0][1]=='O')&&(board[0][2]=='O')){
			return true;
		}
		else if ((board[1][0]=='O')&&(board[1][1]=='O')&&(board[1][2]=='O')){
			return true;
		}
		else if ((board[2][0]=='O')&&(board[2][1]=='O')&&(board[2][2]=='O')){
			return true;
		}
		else if ((board[0][0]=='O')&&(board[1][0]=='O')&&(board[2][0]=='O')){
			return true;
		}
		else if ((board[0][1]=='O')&&(board[1][1]=='O')&&(board[2][1]=='O')){
			return true;
		}
		else if ((board[0][2]=='O')&&(board[1][2]=='O')&&(board[2][2]=='O')){
			return true;
		}
		else if ((board[0][0]=='O')&&(board[1][1]=='O')&&(board[2][2]=='O')){
			return true;
		}
		else if ((board[0][2]=='O')&&(board[1][1]=='O')&&(board[2][0]=='O')){
			return true;
		}
		else if ((board[0][0]=='X')&&(board[0][1]=='X')&&(board[0][2]=='X')){
			return true;
		}
		else if ((board[1][0]=='X')&&(board[1][1]=='X')&&(board[1][2]=='X')){
			return true;
		}
		else if ((board[2][0]=='X')&&(board[2][1]=='X')&&(board[2][2]=='X')){
			return true;
		}
		else if ((board[0][0]=='X')&&(board[1][0]=='X')&&(board[2][0]=='X')){
			return true;
		}
		else if ((board[0][1]=='X')&&(board[1][1]=='X')&&(board[2][1]=='X')){
			return true;
		}
		else if ((board[0][2]=='X')&&(board[1][2]=='X')&&(board[2][2]=='X')){
			return true;
		}
		else if ((board[0][0]=='X')&&(board[1][1]=='X')&&(board[2][2]=='X')){
			return true;
		}
		else if ((board[0][2]=='X')&&(board[1][1]=='X')&&(board[2][0]=='X')){
			return true;
		}
		else 
			return false;
		
	}

	/*
	 * Returns true if there are no places left to move
	 */
	public boolean stalemate() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' '){
					return false;
				}
			}
		}
		
		/*
		 * Step 4: Fill in the code for the stalemate method. It
		 * should return true if there are no more places to move 
		 * on the board. Otherwise, return false return false; 
		 */
		return true;   // replace with your own return 
	}
	public char getPlayer() {
		return player;
	}
	public void print() {
		System.out.println();
		System.out.println("\t  1 2 3");
		System.out.println();
		System.out.println("\tA "+board[0][0]+"|"+board[0][1]+"|"+board[0][2]);
		System.out.println("\t  -----");
		System.out.println("\tB "+board[1][0]+"|"+board[1][1]+"|"+board[1][2]);
		System.out.println("\t  "+"-----");
		System.out.println("\tC "+board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
		System.out.println();
	}
}



package com.doski.moski.tictactoe;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by moski on 1/12/17.
 */

public class Game {

	private Context mContext;


	// Player 1 is X with value 0
	// Player 2 is O with value 1
	private int currentPlayer = 0;

	// -1 Means un-played
	int[] state = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

	//
	// When the game Starts, the state is initial
	//
	GameState gameState = GameState.INITIAL;

	int [][] winningPositions = {
			// Horizontal
			{0,1,2},{3,4,5},{6,7,8},

			// Vertical
			{0,3,6},{1,4,7},{2,5,8},

			// Diagonal
			{0,4,8},{2,4,6}
	};

	int [] score = {0,0};

	Game(Context c){
		mContext = c;
	}

	// Get Current Player
	public int getCurrentPlayer(){
		return currentPlayer;
	}

	// Set the current Player
	public void setCurrentPlayer(int newVal){
		currentPlayer = newVal;
	}

	public Boolean isPositionPlayed(int cellIndex){
		if(state[cellIndex] == -1){
			return false;
		}else{
			return true;
		}
		//return (state[cellIndex] != -1);
	}

	public Boolean play(int cellIndex){
		// Short Circuit if the cell has already been played
		if(isPositionPlayed(cellIndex)){
			return false;
		}

		// Let the gameState know that currentPlayer already played that cell.
		state[cellIndex] = currentPlayer;

		calculateGameState();

		return true;
	}

	//
	// Toggle the play turn.
	//
	public void switchTurn(){
		if(currentPlayer == 0){
			setCurrentPlayer(1);
		}else{
			setCurrentPlayer(0);
		}
	}


	public Boolean hasWon(){
		for(int[] winningPosition: winningPositions){

			int[] position = {
					state[winningPosition[0]],
					state[winningPosition[1]],
					state[winningPosition[2]]
			};

			if(allTheSame(position) && state[winningPosition[0]] != -1){
				return true;
			}
		}
		return false;
	}

	public String hint(){
		String msg = "";
		switch (gameState){
			case INITIAL:
				msg =  mContext.getString(R.string.start_the_game);
				break;
			case PLAYING:
				msg = mContext.getString(R.string.player_turn, currentPlayerName());
				break;
			case ENDED:
				if (hasWon()){
					msg =  mContext.getString(R.string.player_won, currentPlayerName());
				}else{
					msg =  mContext.getString(R.string.tie);
				}

		}
		return msg;
	}

	public String currentPlayerName(){
		if(getCurrentPlayer() == 0){
			return mContext.getString(R.string.player1Name);
		}else{
			return mContext.getString(R.string.player2Name);
		}
	}

	public String getScore(int player){
		int scoreForPlayer = score[player];
		if (scoreForPlayer == 0){
			return mContext.getString(R.string.noScore);
		}else{
			return Integer.toString(scoreForPlayer);
		}
	}

	public void reset(){
		setCurrentPlayer(0);
		Arrays.fill(state, -1);
		calculateGameState();
	}



	//
	// Check if the game has started.
	// A Game is running if any of the cells has already been played.
	//
	private Boolean hasStarted(){
		// If they are all the same, and anyone of them is -1, then the game has not started.
		return !(allTheSame(state) && state[0] == -1);
	}

	//
	// Check if the game has ended.
	// The game is usually ended when all the cells have been played.
	//
	private Boolean hasEnded(){
		for(int s: state){
			if(s == -1){
				return false;
			}
		}
		return true;
	}


	private Boolean allTheSame(int[] array){
		if (array.length == 0){
			return true;
		}else{
			int first =  array[0];
			for(int element: array){
				if(element != first){
					return false;
				}
			}
			return true;
		}
	}

	private void calculateGameState(){
		if (!hasStarted()){
			gameState = GameState.INITIAL;
		}else if(hasEnded() || hasWon()){
			gameState = GameState.ENDED;
			calculateScore();
		}else{
			gameState = GameState.PLAYING;
		}
	}

	private void calculateScore(){
		if(hasWon()){
			int current = score[getCurrentPlayer()];
			score[getCurrentPlayer()] = current + 1;
		}
	}
}

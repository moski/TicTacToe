package com.doski.moski.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;


import com.jrummyapps.android.widget.AnimatedSvgView;

public class MainActivity extends AppCompatActivity {

	Game game;
	TextView hint;
	View player1Selector;
	View player2Selector;

	TextView player1Score;
	TextView player2Score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		game = new Game(this);
		hint = (TextView) findViewById(R.id.hint);
		hint.setText(game.hint());

		player1Selector = findViewById(R.id.selectPlayer1);
		player2Selector = findViewById(R.id.selectPlayer2);

		player1Score = (TextView) findViewById(R.id.player1Score);
		player2Score = (TextView) findViewById(R.id.player2Score);
	}

	public void play(View view){
		AnimatedSvgView currentView = (AnimatedSvgView) view;

		int cellCounter = Integer.parseInt(currentView.getTag().toString());


		if (game.gameState != GameState.ENDED && game.play(cellCounter)){
			// Draw the Cell
			setSvg(currentView, SVG.values()[game.getCurrentPlayer()]);


			switch (game.gameState){
				case ENDED:
					break;
				case PLAYING:
					game.switchTurn();
					updateCurrentPlayerSelector();
					break;
			}
			hint.setText(game.hint());
			updateScore();
		}
	}

	public void resetGame(View view){
		// Reset the game State and currentPlayer
		game.reset();
		updateCurrentPlayerSelector();

		GridLayout gameBoard = (GridLayout) findViewById(R.id.gameBoard);

		// Reset the SVGs to empty state.
		for(int i=0; i<gameBoard.getChildCount(); i++){
			AnimatedSvgView cell = (AnimatedSvgView) gameBoard.getChildAt(i);
			setSvg(cell, SVG.values()[2]);
		}

		hint.setText(game.hint());
	}

	private void updateCurrentPlayerSelector(){
		if(game.getCurrentPlayer() == 0){
			player1Selector.setBackgroundColor(getResources().getColor(R.color.currentPlayer));
			player2Selector.setBackgroundColor(getResources().getColor(R.color.otherPlayer));
		}else{
			player1Selector.setBackgroundColor(getResources().getColor(R.color.otherPlayer));
			player2Selector.setBackgroundColor(getResources().getColor(R.color.currentPlayer));
		}
	}

	private void updateScore(){
		player1Score.setText(game.getScore(0));
		player2Score.setText(game.getScore(1));
	}

	private void setSvg(AnimatedSvgView svgView, SVG svg) {
		svgView.setGlyphStrings(svg.glyphs);
		svgView.setFillColors(svg.colors);
		svgView.setViewportSize(svg.width, svg.height);
		svgView.setTraceResidueColor(0x32000000);
		svgView.setTraceColors(svg.colors);
		svgView.rebuildGlyphData();
		svgView.start();
	}
}

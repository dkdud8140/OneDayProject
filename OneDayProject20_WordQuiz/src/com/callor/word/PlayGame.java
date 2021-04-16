package com.callor.word;

import com.callor.word.impl.WordGame_Service_implV1;
import com.callor.word.service.WordGameService;

public class PlayGame {
	public static void main(String[] args) {
		
		WordGameService playGame = new WordGame_Service_implV1();
		
		playGame.mainGame();
		
		System.out.println("\n게임종료");
		
		
		
	}
}

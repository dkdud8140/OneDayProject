package com.callor.word.service;

import com.callor.word.model.WordVO;

public interface WordGameService {

	public void mainGame() ;
	public void playGame() ;
	public void loadRecord() ;
	public void writerRecord() ;
	public WordVO radomWord() ;
	public String[] shuffleWord(String strWord) ;
	public void resetPlayer() ;
	
	
}

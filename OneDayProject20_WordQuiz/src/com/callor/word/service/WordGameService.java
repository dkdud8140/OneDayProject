package com.callor.word.service;

import com.callor.word.model.WordVO;

public interface WordGameService {

	public void mainGame() ;							// 게임 초기 화면
	public void playGame() ;							// 게임 진행 화면
	public void loadRecord() ;							// 파일 불러오기
	public void writerRecord() ;						// 파일 저장하기
	public WordVO radomWord() ;							// 단어 List에서 랜덤 단어 뽑기
	public String[] shuffleWord(String strWord) ;		// 단어 스펠링 섞기
	public void resetPlayer() ;							// 플레이어 정보 리셋
	
	public void savePlayerFile();						// 플레이어의 이름으로 파일 저장	
	
}

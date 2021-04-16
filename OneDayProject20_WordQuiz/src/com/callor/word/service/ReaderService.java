package com.callor.word.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.callor.word.model.WordVO;

public class ReaderService {

	List<WordVO> wordList ;
	
	public ReaderService() {
	}
	
	public List<WordVO> wordRead() {	
		wordList = new ArrayList<WordVO>() ;

		String fileName = "src/com/callor/word/word.txt";

		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);

			while (true) {
				String strReader = buffer.readLine();

				if (strReader == null) break;

				String strWord[] = strReader.split(":") ;
				
				WordVO wVO = new WordVO();
				wVO.setEng(strWord[0]) ;
				wVO.setKor(strWord[1]) ;
				wordList.add(wVO);
				
			}

			buffer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wordList ;
	}
	
	
	public Integer scoreRead(String file) {	

		String fileName = "src/com/callor/word/" + file + ".txt";

		FileReader fileReader = null;
		BufferedReader buffer = null;

		Integer intScore = null ; 
		
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);

				String strReader = buffer.readLine();
				String strWord[]  = strReader.split(":") ;
				
				intScore = Integer.valueOf(strWord[1]);

			buffer.close();

		} catch (IOException e) {
		}
		
		return intScore ;
	}

}

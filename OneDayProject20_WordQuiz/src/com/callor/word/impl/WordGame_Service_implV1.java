package com.callor.word.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.callor.word.model.WordVO;
import com.callor.word.service.ReaderService;
import com.callor.word.service.WordGameService;

public class WordGame_Service_implV1 implements WordGameService {

	protected Scanner scan;

	protected final int lineNum = 22; // 줄숫자
	protected ReaderService rS; // 파일 읽어오는 클래스

	protected List<WordVO> wordList; // 단어 리스트 저장하는 List

	protected String playerName; // 플레이어의 이름 변수
	protected Integer playerScore; // 플레이어의 점수 변수

	public WordGame_Service_implV1() {

		// TODO 생성자
		scan = new Scanner(System.in);
		rS = new ReaderService();

		wordList = rS.wordRead(); // TODO wordList에 단어 리스트 저장

		playerName = null;
		playerScore = 0;

	}

	@Override
	public void mainGame() {
		// TODO 게임 시작 화면

		System.out.println("☆★".repeat(lineNum));
		System.out.println("괴도 뤼팡이 나타났습니다!");
		System.out.println("괴도 뤼팡이 훔친 황금이 들어 있는 금고의\n비밀번호를 찾아주세요.");
		System.out.println("☆★".repeat(lineNum));

		while (true) {

			System.out.println("-".repeat(20));
			System.out.println("현재 점수 : " + playerScore); // 현재 점수 보여줌
			System.out.println("-".repeat(20));
			System.out.println(">> 1. 저장한 게임 불러오기");
			System.out.println(">> 2. 게임 시작");
			System.out.println(">> 3. 게임 저장");
			System.out.println(">> 4. 플레이어 초기화");
			System.out.println(">> Quit : 게임종료");
			System.out.println("*".repeat(lineNum * 2));

			System.out.print("\n>> 입력 : ");
			String selecM = scan.nextLine();

			if (selecM.equals("Quit"))
				return;

			Integer intM = null;
			try {
				intM = Integer.valueOf(selecM);
			} catch (NumberFormatException e) {
				System.out.println("\n입력은 1 혹은 2 혹은 Quit만 가능합니다.");
				continue;
			}

			if (intM == 1)
				this.loadRecord(); // 플레이어 정보 불러오기 메소드
			else if (intM == 2)
				this.playGame(); // 게임시작 메소드
			else if (intM == 3)
				this.writerRecord(); // 게임 저장하기
			else if (intM == 4)
				this.resetPlayer(); // 플레이어 정보 초기화
			else {
				System.out.println("\n입력은 1 혹은 2 혹은 Quit만 가능합니다.");
				continue;
			}
		}

	}

	@Override
	public void loadRecord() {
		// TODO 스코어 리로드

		System.out.println("*".repeat(lineNum * 2));
		System.out.println("\n플레이어의 이름을 입력하세요.");
		System.out.println("메뉴로 돌아가려면 Quit를 입력하세요.");
		System.out.println("!WARNING! 진행하던 게임를 저장하지 않고 다른 기록을 불러오면 " + "\n진행하던 게임 기록은 사라집니다.");

		while (true) {
			System.out.println("\n입력 >> ");
			playerName = scan.nextLine();

			playerScore = rS.scoreRead(playerName); // ReaderService 클래스의 scoreRead 메소드로 저장된 플레이어의 점수 파일 리로드
			if (playerName.equals("Quit")) {
				if (playerScore == null)
					playerScore = 0; // 플레이어 정보 찾기 실패시 playerScore가 null 값으로 출력되는 것을 방지
				return;
			} else if (playerScore == null) {
				System.out.println("\n" + playerName + "는(은) 등록되지 않은 플레이어입니다.");
			} else {
				System.out.println("\n" + playerName + "님의 점수는 " + playerScore + "점 입니다");
				System.out.println(playerName + "님의 이름으로 게임이 진행됩니다.");
				break;
			}

		} // while()문 종료

	}

	@Override
	public void playGame() {
		// TODO 게임 시작

		while (true) {
			WordVO vo = this.radomWord(); // 로드 했던 단어 리스트에서 랜덤한 단어 추출

			String rndEng = vo.getEng(); // 랜덤으로 추출한 영단어
			String rndKor = vo.getKor(); // 랜덤으로 추출한 한글뜻

			String shEng[] = this.shuffleWord(rndEng); // 영단어의 스펠링 섞어서 배열에 저장

			while (true) {
				System.out.println("\n▶▶▶ 현재점수 : " + playerScore + " ◀◀◀"); // 플레이어의 현재 점수 출력
				System.out.println("-".repeat(lineNum * 2));
				System.out.println("다음 영단어를 맞추세요.");
				System.out.println("게임종료시 Quit 입력 ( 단 , 점수가 2점 차감됩니다.)");
				// System.out.println("\n" + rndEng);
				System.out.print("\n▶▶▶");
				System.out.print("[ ");
				for (int i = 0; i < shEng.length; i++) { // shEng의 크기만큼 스펠링 출력
					System.out.print(shEng[i] + " ");
				}
				System.out.print("]\n");
				System.out.println("-".repeat(lineNum * 2));

				System.out.print(">> 정답입력 : ");
				String correctWor = scan.nextLine();

				if (correctWor.equals("Quit")) { // mainGame으로 돌아감
					playerScore -= 2; // 2점 차감
					return;
				}

				if (correctWor.equalsIgnoreCase(rndEng)) { // 영소문자 무시하고 정답 체크
					System.out.println("\n정답입니다!!!");
					System.out.println("◈◇ 점수 10↑↑ ◈◇");
					playerScore += 10;

					Integer goS = this.goStop(); // 게임 진행, 멈춤 선택, 점수차감없음
					if (goS == 1)
						break; // YES 진행시 : 다음 문제 출력
					else if (goS == 2)
						return; // NO 진행시 : mainGame으로 돌아감

				}

				else if (!correctWor.equals("Quit")) { // 오답시
					System.out.println("\n오답입니다ㅠㅠ");
					System.out.println("점수가 2점 차감됐습니다");
					playerScore -= 2; // 오답시 2점 차감

					Integer wrongA = this.wrongAnswer(); // 오답 시 게임진행 방향 선택 메소드
					if (wrongA == null)
						return; // mainGame으로 돌아가기
					else if (wrongA == 1)
						continue; // 현재문제 재도전
					else if (wrongA == 2)
						break; // 다음문제
					else if (wrongA == 3) { // 힌트 제공
						System.out.println("Hint : " + rndKor);
						continue;
					}

				}
			}
		}

	}

	@Override
	public WordVO radomWord() {
		// TODO 불러온 단어 리스트에서 랜덤한 단어를 뽑기

		Random rnd = new Random();
		int nSize = wordList.size();

		int index = rnd.nextInt(nSize); // 단어리스트에서 랜덤한 단어를 뽑기위한 index값

		WordVO vo = wordList.get(index);
		vo.getEng(); // 랜덤한 영단어
		vo.getKor(); // 랜덤한 영단어의 한글 뜻

		return vo; // 랜덤한 단어 WordVO 형태로 출력
	}

	@Override
	public String[] shuffleWord(String strWord) {
		// TODO 단어 스펠링 셔플

		String shWord[] = strWord.split("");

		for (int i = 0; i < 50; i++) {
			Random rnd = new Random();
			int num = rnd.nextInt(shWord.length);
			String temp = shWord[1];
			shWord[1] = shWord[num];
			shWord[num] = temp;
		}

		return shWord; // 스펠링 섞은 단어열 출력
	}

	@Override
	public void resetPlayer() {
		// TODO 플레이어 리셋
		playerName = "";
		playerScore = 0;
		return;

	}

	protected Integer wrongAnswer() {

		// TODO 오답입력시 게임 진행 선택 메소드

		while (true) {

			System.out.println("\n>> 1 : 재도전(점수 1점 차감)");
			System.out.println(">> 2 : 다음 문제로 넘어가기(점수 2점 차감)");
			System.out.println(">> 3 : 힌트보기 (점수 2점 차감)");
			System.out.println(">> Quit : 메뉴로 돌아가기(점수 2점 차감)");

			System.out.print("\n>> 입력 : ");
			String selecM = scan.nextLine();

			if (selecM.equals("Quit")) {
				playerScore -= 2;
				return null;
			}

			Integer intM = null;
			try {
				intM = Integer.valueOf(selecM);
			} catch (NumberFormatException e) {
				System.out.println("\n입력은 < 1, 2, 3, Quit > 만 가능합니다.");
				continue;
			}

			if (intM == 1) { // 재도전 시 : 1점 차감 후 1값 리턴
				playerScore -= 1;
				return 1;

			} else if (intM == 2) { // 다음문제 선택 시 : 2점 차감 후 2값 리턴
				playerScore -= 2;
				return 2;
			} else if (intM == 3) { // 힌트 선택시 : 2점 차감 후 3값 리턴
				if (playerScore <= 0) { // 현재 점수가 0이하이면 힌트제공 거부
					System.out.println("점수가 0점 이하이므로 힌트를 줄 수 없습니다.");
					continue;
				}
				playerScore -= 2;
				return 3;
			} else {
				System.out.println("\n입력은 < 1, 2, 3, Quit > 만 가능합니다.");
				continue;
			}

		}
	}

	protected Integer goStop() {

		// TODO 정답 입력시 GO / Stop 선택 메소드

		while (true) {
			System.out.println("\n계속 진행하시겠습니까?");
			System.out.println("YES  /  NO");
			System.out.print(" >> ");
			String yNo = scan.nextLine();
			if (yNo.equals("YES"))
				return 1;
			else if (yNo.equals("NO"))
				return 2;
			else {
				System.out.println("입력은 YES / NO 만 가능합니다");
				continue;
			}
		}

	}

	@Override
	public void writerRecord() {

		// TODO 스코어 파일로 저장

		if (!(playerName == null)) {			// 현재 진행중인 플레이어의 이름이 입력되어 있는 경우

			while (true) {
				System.out.println("현재이름( "+ playerName + " )으로 파일을 저장하시겠습니까?");
				System.out.println("YES  /  NO");
				System.out.print(" >> ");
				String selecSave = scan.nextLine();
				if (selecSave.equals("YES"))			//현재 플레이어의 이름으로 저장하기를 선택시 
					break;								//따로 플레이어의 이름을 입력받지 않고 현재 이름으로 저장
				else if (selecSave.equals("NO")) {
					this.saveFile();					//다른 이름으로 저장 원할시 다시 입력 받기
					break ;
				}
				else {									
					System.out.println("입력은 YES / NO 만 가능합니다");
					continue;
				}
			}
		} else {										//현재 진행중인 플레이어의 이름이 없는 경우
			this.saveFile();
		}
		
		//파일 저장 부분
		
		String str = "src/com/callor/word/" + playerName + ".txt"; // 입력한 이름으로 파일 저장

		FileWriter fileWriter = null;
		PrintWriter out = null;

		try {
			fileWriter = new FileWriter(str);
			out = new PrintWriter(fileWriter);

			out.print(playerName); // txt 파일에 "플레이어명:점수" 형태로 저장
			out.print(":");
			out.print(playerScore);

			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("저장이 완료되었습니다.");

	}

	protected void saveFile() {
		
		//TODO 플레이어 이름 입력 메소드	
		
		while (true) {
			System.out.println("\n저장할 플레이어 이름을 입력하세요");
			System.out.println("단, Quit는 입력 불가.");
			System.out.print(">> 입력 : ");

			playerName = scan.nextLine();
			if (playerName.equals("Quit")) {
				System.out.println("Quit는 입력불가입니다. 다시 입력하세요.");
				continue;
			}
			break;
		}
	}

}

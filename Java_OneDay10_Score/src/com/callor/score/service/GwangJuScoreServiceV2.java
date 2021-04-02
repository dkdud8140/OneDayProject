package com.callor.score.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.score.model.GwangJuScoreVO;
import com.callor.score.model.GwangJuScoreVOV2;

public class GwangJuScoreServiceV2 {

	// TODO 맴버변수 선언
	protected Scanner scan;
	
	protected List<GwangJuScoreVOV2> scoreList; // 학생의 이름, 과목별점수, 총점, 평균을 저장하는 List 
	
	protected int lineLegnth = 80;		//라인 수 조절
	protected String subName[];			//과목이름배열
	protected Integer subScore[];		//과목별 점수를 임시로 저장하는 배열
	
	public GwangJuScoreServiceV2() {
		// TODO 맴버변수 초기화
		scan = new Scanner(System.in);
		scoreList = new ArrayList<GwangJuScoreVOV2>();
		subName = new String[] { "국어", "영어", "수학", "과학", "국사" };
		subScore = new Integer[5];
	}

	public void printMenu() {
		// TODO 메뉴 선택 화면
		
		String menuSelect ; 
		Integer intMenu ;

		while (true) {
			System.out.println();
			System.out.println("=".repeat(lineLegnth));
			System.out.println("빛고을 고등학교 성적처리 프로젝트 2021");
			System.out.println("-".repeat(lineLegnth));
			System.out.println("1. 학생별 성적 입력");
			System.out.println("2. 학생 성적 리스트 출력");
			System.out.println("QUIT. 업무종료");
			System.out.println("=".repeat(lineLegnth));

			System.out.print("업무선택 >> ");
			menuSelect = scan.nextLine();

			if (menuSelect.equals("QUIT")) {							// MENU 업무 종료
				System.out.println();
				System.out.println("업무를 완전히 끝냈습니다.");
				return;
			}
			
			
			try {														// MENU 예외처리
				intMenu = Integer.valueOf(menuSelect);
			} catch (Exception e) {
				System.out.println();
				System.out.println("1 혹은 2 혹은 QUIT만 입력할 수 있습니다");
				continue;
			}

			if (intMenu == 1) {											// 학생 점수 입력 메소드 호출
				this.inputScore();
			} else if (intMenu == 2) {									// 학생들의 점수 리스트 출력하는 메소드 호출
				this.printScore();
			} else {													// MENU 범위 외 값 처리
				System.out.println();
				System.out.println("1 혹은 2 혹은 QUIT만 입력할 수 있습니다");
			}

		}

	}

	
	public void inputScore() {
		
		String strScore;			//점수 입력받는 변수
		
		// TODO 한 학생의 이름과 성적을 입력하여 List변수 scoreList에 저장하는 반복문 while()
		while (true) {
			
		// TODO 학생 이름 입력
			System.out.println();
			System.out.println("=".repeat(lineLegnth));
			System.out.println("학생이름을 입력하세요.(업무중단시 : QUIT)");
			System.out.println("-".repeat(lineLegnth));
			System.out.print("이름 >> ");
			String stuName = scan.nextLine(); 							// 학생 이름 입력
			if (stuName.equals("QUIT")) { 								// 업무중단시 메뉴화면으로 return
				return;
			}

		// TODO 학생의 점수 입력	
			System.out.println("=".repeat(lineLegnth));
			System.out.printf("%s 학생의 성적을 입력하세요.\n", stuName);
			System.out.println("(성적의 범위는 0 ~ 100 / 업무중단시 : QUIT ");
			System.out.println("=".repeat(lineLegnth));
			
			for (int i = 0; i < subName.length; i++) { 					// 과목별 성적 입력 for()

				while (true) {
					System.out.printf("%s >> ", subName[i]);
					strScore = scan.nextLine();
					
					if (strScore.equals("QUIT")) { 						// 업무중단시 메뉴화면으로 return
						return;
					}
					
					try { 												// 과목 점수에 숫자 외를 입력했을때
						subScore[i] = Integer.valueOf(strScore);
					} catch (Exception e) {
						System.out.println("성적은 숫자 정수만 입력하세요.");
						continue;
					}

					
					if (subScore[i] < 0 || subScore[i] > 100) { 					// 과목점수에 범위 벗어남
						System.out.println("성적은 0점 이상 100점 이하만 입력하세요.");
						continue;
					}
					break;

				} // 학생 점수 입력 끝
			}

			
			// TODO 한 학생의 이름,과목별성적,총점,평균을 List scoreList에 저장
			GwangJuScoreVOV2 vo = new GwangJuScoreVOV2(); 				
			vo.setStuName(stuName);
			vo.setScoreKor(subScore[0]);
			vo.setScoreEng(subScore[1]);
			vo.setScoreMath(subScore[2]);
			vo.setScoreSci(subScore[3]);
			vo.setScoreHis(subScore[4]);

			scoreList.add(vo);
			
			// TODO 학생의 성적 입력 결과를 출력
			System.out.println(); 											
			System.out.println("=".repeat(lineLegnth));
			System.out.printf("%s 학생의 성적이 추가되었습니다.\n", stuName);
			System.out.println("-".repeat(lineLegnth));
			for (int i = 0; i < subName.length; i++) {
				System.out.printf("%s : %d\n", subName[i], subScore[i]);
			}
			System.out.println("=".repeat(lineLegnth));


		} // while 끝

	}
	
	public void printScore() {
		
		Integer scoreTotal[] = new Integer[subName.length];		// 모든 학생의 과목별 점수 합계
		for(int i = 0 ; i < scoreTotal.length ; i ++) {			// 배열값 초기화
			scoreTotal[i] = 0 ;
		}
		
		Integer totalSum = 0;	 								// 모든 학생의 총점의 총점
		Float totalAgv = 0.0F ;									// 모든 학생의 평균의 총점

		// TODO 성적리스트 출력
		System.out.println();
		System.out.println("=".repeat(lineLegnth));
		System.out.println("순번\t이름\t국어\t영어\t수학\t과학\t국사\t총점\t평균");
		System.out.println("-".repeat(lineLegnth));

		for (int i = 0; i < scoreList.size(); i++) {

			GwangJuScoreVOV2 vo = new GwangJuScoreVOV2();
			vo = scoreList.get(i);

			System.out.print((i + 1) + "\t");						//리스트 출력
			System.out.print(vo.getStuName() + "\t");
			System.out.print(vo.getScoreKor() + "\t");
			System.out.print(vo.getScoreEng() + "\t");
			System.out.print(vo.getScoreMath() + "\t");
			System.out.print(vo.getScoreSci() + "\t");
			System.out.print(vo.getScoreHis() + "\t");
			System.out.print(vo.getScoreSum() + "\t");
			System.out.printf("%3.2f\n", vo.getScoreAvg());

			scoreTotal[0] += vo.getScoreKor();						//리스트 출력과 동시에 학생들의 과목별 총점 계산
			scoreTotal[1] += vo.getScoreEng();
			scoreTotal[2] += vo.getScoreMath();
			scoreTotal[3] += vo.getScoreSci();
			scoreTotal[4] += vo.getScoreHis();
			
			totalSum += vo.getScoreSum();							//모든 학생의 총점의 총점
			totalAgv += vo.getScoreAvg();							//모든 학생읨 평균의 총점
		}
		System.out.println("-".repeat(lineLegnth));

		System.out.print("총점\t");
		System.out.print("\t");
		
		for (int i = 0; i < subName.length; i++) {				// 과목별 모든 학생의 점수를 출력
			System.out.print(scoreTotal[i] + "\t");
		}
		
		System.out.print(totalSum + "\t");
		System.out.printf("%3.2f\n", totalAgv/scoreList.size() );
		System.out.println("=".repeat(lineLegnth));
		return;
	}

}

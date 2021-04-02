package com.callor.score.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.score.model.GwangJuScoreVO;

public class GwangJuScoreService {

	Scanner scan;
	List<GwangJuScoreVO> scoreList;

	int lineLegnth = 80;
	String subName[];
	int subScore[];

	public GwangJuScoreService() {
		scan = new Scanner(System.in);
		scoreList = new ArrayList<GwangJuScoreVO>();
		subName = new String[] { "국어", "영어", "수학", "과학", "국사" };
		subScore = new int[5];
	}

	public void inputScore() {
		// TODO 학생 이름 입력

		while (true) {
			System.out.println();
			System.out.println("=".repeat(lineLegnth));
			System.out.println("학생이름을 입력하세요.(업무중단시 : QUIT)");
			System.out.println("-".repeat(lineLegnth));
			System.out.print("이름 >> ");
			String stuName = scan.nextLine(); // 학생 이름 입력
			if (stuName.equals("QUIT")) { // 업무중단시 메뉴 출력
				return;
			}

			System.out.println("=".repeat(lineLegnth));
			System.out.printf("%s 학생의 성적을 입력하세요.\n", stuName);
			System.out.println("(성적의 범위는 0 ~ 100 / 업무중단시 : QUIT ");
			System.out.println("=".repeat(lineLegnth));

			for (int i = 0; i < subName.length; i++) { // 과목별 성적 입력 for()

				String strScore;
				Integer intScore;

				while (true) {
					System.out.printf("%s >> ", subName[i]);
					strScore = scan.nextLine();
					if (strScore.equals("QUIT")) { // 업무중단시 메뉴 출력
						return;
					}
					try { // 과목 점수에 숫자 외를 입력했을때
						intScore = Integer.valueOf(strScore);
					} catch (Exception e) {
						System.out.println("성적은 숫자만 입력하세요.");
						continue;
					}

					if (intScore < 0 || intScore > 100) { // 과목점수에 범위 벗어남
						System.out.println("성적은 0점 이상 100점 이하만 입력하세요.");
						continue;
					} else {
						subScore[i] = intScore; // 점수배열에 점수 입력
						break;
					}

				}
			}

			System.out.println(); // 학생 성적 입력 결과
			System.out.println("=".repeat(lineLegnth));
			System.out.printf("%s 학생의 성적이 추가되었습니다.\n", stuName);
			System.out.println("-".repeat(lineLegnth));
			for (int i = 0; i < subName.length; i++) {
				System.out.printf("%s : %d\n", subName[i], subScore[i]);
			}
			System.out.println("=".repeat(lineLegnth));

			Integer intSum = 0;
			Float floAvg = 0.0f;

			for (int i = 0; i < subName.length; i++) {
				intSum += subScore[i];
			}

			floAvg = (float) intSum / subName.length;

			GwangJuScoreVO vo = new GwangJuScoreVO(); // List에 이름 및 성적 추가
			vo.setStuName(stuName);
			vo.setScoreKor(subScore[0]);
			vo.setScoreEng(subScore[1]);
			vo.setScoreMath(subScore[2]);
			vo.setScoreSci(subScore[3]);
			vo.setScoreHis(subScore[4]);
			vo.setScoreSum(intSum);
			vo.setScoreAvg(floAvg);

			scoreList.add(vo);

		}

	}

	public void printMenu() {
		// TODO 메뉴 선택 화면

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
			String menuSelect = scan.nextLine();
			Integer intMenu;

			if (menuSelect.equals("QUIT")) {
				System.out.println();
				System.out.println("업무를 완전히 끝냈습니다.");
				return;
			}
			try {
				intMenu = Integer.valueOf(menuSelect);
			} catch (Exception e) {
				System.out.println();
				System.out.println("1 혹은 2 혹은 QUIT만 입력할 수 있습니다");
				continue;
			}

			if (intMenu == 1) {
				this.inputScore();
			} else if (intMenu == 2) {
				this.printScore();
			} else {
				System.out.println();
				System.out.println("1 혹은 2 혹은 QUIT만 입력할 수 있습니다");
			}

		}

	}

	public void printScore() {

		Integer scoreTotal[] = new Integer[subScore.length];
		for(int i = 0 ; i < scoreTotal.length ; i++) {
			scoreTotal[i] = 0;
		}
		Integer totalSum = 0;

		System.out.println();
		System.out.println("=".repeat(lineLegnth));
		System.out.println("순번\t이름\t\t국어\t영어\t수학\t과학\t국사\t총점\t평균");
		System.out.println("-".repeat(lineLegnth));

		for (int i = 0; i < scoreList.size(); i++) {

			GwangJuScoreVO vo = new GwangJuScoreVO();
			vo = scoreList.get(i);
			
			System.out.print((i + 1) + "\t");
			System.out.print(vo.getStuName() + "\t\t");
			System.out.print(vo.getScoreKor() + "\t");
			System.out.print(vo.getScoreEng() + "\t");
			System.out.print(vo.getScoreMath() + "\t");
			System.out.print(vo.getScoreSci() + "\t");
			System.out.print(vo.getScoreHis() + "\t");
			System.out.print(vo.getScoreSum() + "\t");
			System.out.printf("%3.2f\n", vo.getScoreAvg());

			scoreTotal[0] += vo.getScoreKor();
			scoreTotal[1] += vo.getScoreEng();
			scoreTotal[2] += vo.getScoreMath();
			scoreTotal[3] += vo.getScoreSci();
			scoreTotal[4] += vo.getScoreHis();
		}
		System.out.println("-".repeat(lineLegnth));

		System.out.print("총점\t");
		System.out.print("\t\t");
		for (int i = 0; i < subName.length; i++) {
			System.out.print(scoreTotal[i] + "\t");
			totalSum +=scoreTotal[i];
		}
		System.out.print(totalSum + "\t");
		float totalAgv = totalSum / scoreTotal.length;
		System.out.printf("%3.2f\n", totalAgv);

		System.out.println("=".repeat(lineLegnth));
		return;
	}

}

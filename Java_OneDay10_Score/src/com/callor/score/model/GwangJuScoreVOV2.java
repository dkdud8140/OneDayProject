package com.callor.score.model;

public class GwangJuScoreVOV2 {

	private String stuName;
	private Integer scoreKor;
	private Integer scoreEng;
	private Integer scoreMath;
	private Integer scoreSci;
	private Integer scoreHis;

	private Integer scoreSum;
	private Float scoreAvg;

	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getScoreKor() {
		return scoreKor;
	}
	public void setScoreKor(Integer scoreKor) {
		this.scoreKor = scoreKor;
	}

	public Integer getScoreEng() {
		return scoreEng;
	}
	public void setScoreEng(Integer scoreEng) {
		this.scoreEng = scoreEng;
	}

	public Integer getScoreMath() {
		return scoreMath;
	}
	public void setScoreMath(Integer scoreMath) {
		this.scoreMath = scoreMath;
	}

	public Integer getScoreSci() {
		return scoreSci;
	}
	public void setScoreSci(Integer scoreSci) {
		this.scoreSci = scoreSci;
	}

	public Integer getScoreHis() {
		return scoreHis;
	}
	public void setScoreHis(Integer scoreHis) {
		this.scoreHis = scoreHis;
	}

	
	public Integer getScoreSum() {
		this.scoreSum = this.scoreKor;
		this.scoreSum += this.scoreEng;
		this.scoreSum += this.scoreMath;
		this.scoreSum += this.scoreSci;
		this.scoreSum += this.scoreHis;

		return scoreSum;
	}

	
	public Float getScoreAvg() {
		this.scoreAvg = (this.scoreSum / 5.0F);
		
		return scoreAvg ;
	}

}

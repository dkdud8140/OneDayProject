package com.callor.score.model;

public class GwangJuScoreVO {

	private String stuName ;
	private Integer scoreKor ;
	private Integer scoreEng ;
	private Integer scoreMath ;
	private Integer scoreSci ;
	private Integer scoreHis ;
	
	private Integer scoreSum ;
	private Float scoreAvg ;
	
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public String getStuName() {
		return stuName;
	}
	
	
	public Integer getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(Integer scoreSum) {
		this.scoreSum = scoreSum;
	}
	
	
	public Float getScoreAvg() {
		return scoreAvg;
	}
	public void setScoreAvg(Float scoreAvg) {
		this.scoreAvg = scoreAvg;
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
	
	
	
}

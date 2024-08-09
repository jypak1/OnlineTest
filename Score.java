package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Score implements Comparable<Score>, Serializable {
	
	private double score;
	private int examId;
	
	public Score(double score, int examId)
	{
		this.score = score;
		this.examId = examId;
	}
	public double getScore()
	{
		return score;
	}
	public int getExamId()
	{
		return examId;
	}
	public void addToScore(double points)
	{
		score += points;
	}
	@Override
	public String toString()
	{
		String str = "";
		return str += score;
	}
	@Override
	public int compareTo(Score o) {
		return (int) (score - o.getScore());
	}

}

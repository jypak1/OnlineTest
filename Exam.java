package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Exam implements Serializable{
	
	private String name;
	private int id;
	ArrayList<Question> questions = new ArrayList<Question>();
	TreeSet<Score> scores = new TreeSet<Score>();
	private double score;
	
	public Exam(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public int getId()
	{
		return id;
	}
	public ArrayList<Question> getQuestions()
	{
		return questions;
	}
	public void addScore(double score, int examId)
	{
		this.scores.add(new Score(score, examId));
	}
	public TreeSet<Score> getScores()
	{
		return scores;
	}
	public double getScore()
	{
		return score;
	}
	public void addPoints(double points)
	{
		score += points;
	}

}

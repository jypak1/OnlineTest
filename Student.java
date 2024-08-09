package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
	
	private String name;
	private String grade;
	private HashMap<Integer, Exam> exams;
	private HashMap<Integer, Score> scores;
	private HashMap<Integer, HashMap<Integer, Answer>> answers;
	private HashMap<Integer, Double> points;
	
	public Student(String name)
	{
		this.name = name;
		this.grade = "F";
		this.exams = new HashMap<Integer, Exam>();
		this.scores = new HashMap<Integer, Score>();
		this.answers = new HashMap<Integer, HashMap<Integer, Answer>>();
	}
	public String getName()
	{
		return name;
	}
	public HashMap<Integer, Exam> getExams()
	{
		return exams;
	}
	public Score getScores(int examId)
	{
		return scores.get(examId);
	}
	public HashMap<Integer, Score> getHashMapScore()
	{
		return scores;
	}
	public void addExam(int examId, Exam exam)
	{
		exams.put(examId, exam);
	}
	public void addScore(Score score, int examId)
	{
		this.scores.put(examId, score);
	}
	public void addAnswer(Answer answer, int examId, int questionNumber)
	{
		if(!answers.containsKey(examId))
		{
			HashMap<Integer, Answer> exam = answers.get(examId);
			exam = new HashMap<Integer, Answer>();
			answers.put(examId, exam);
		}

		answers.get(examId).put(questionNumber, answer);
	}
	public HashMap<Integer, HashMap<Integer, Answer>> getAnswers()
	{
		return this.answers;
	}
	public HashMap<Integer, Double> getPoints()
	{
		return this.points;
	}
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	public String getGrade()
	{
		return grade;
	}
}

package onlineTest;

import java.io.Serializable;

public class TrueOrFalse implements Question, Serializable{

	private String text;
	private int examId;
	private int questionNumber;
	private double points;
	private boolean answer;
	
	
	public TrueOrFalse(int examId, int questionNumber, String text,
						double points, boolean answer)
	{
		this.text = text;
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.points = points;
		this.answer = answer;
	}
	@Override
	public int getQuestionNumber() {
		return questionNumber;
	}
	@Override
	public double getPoints() {
		return points;
	}
	@Override
	public String getText() {
		return text;
	}
	@Override
	public int getExamId()
	{
		return examId;
	}
	public String getAnswer()
	{
		String str = "";
		if(answer == false)
		{
			return str += "False";
		}
		return str += "True";
	}
	public String getKey()
	{
		String str = "";
		str += "Question Text: " + text + "\n";
		str += "Points: " + points + "\n";
		str += "Correct Answer: ";
		if(answer == false)
		{
			str += "False";
		}
		else
		{
			str += "True";
		}
		return str;
	}
	
}

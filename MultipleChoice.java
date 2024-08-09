package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MultipleChoice implements Question, Serializable{
	
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private String[] answer;
	
	public MultipleChoice(int examId, int questionNumber, String text,
							double points, String[] answer)
	{
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = new String[answer.length];
		for(int index = 0; index < answer.length; index++)
		{
			this.answer[index] = answer[index];
		}
	}
	@Override
	public int getQuestionNumber() {
		return questionNumber;
	}

	@Override
	public int getExamId() {
		return examId;
	}

	@Override
	public double getPoints() {
		return points;
	}

	@Override
	public String getText() {
		return text;
	}
	public String[] getAnswer()
	{
		return answer;
	}
	public String getStringAnswer()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int index = 0; index < answer.length; index++)
		{
			list.add(answer[index]);
		}
		Collections.sort(list);
		String[] arr = new String[answer.length];
		for(int index = 0; index < answer.length; index++)
		{
			arr[index] = list.get(index);
		}
		return Arrays.toString(arr);
	}
	public String getKey()
	{
		String str = "";
		str += "Question Text: " + text + "\n";
		str += "Points: " + points + "\n";
		str += "Correct Answer: " + Arrays.toString(answer);
		return str;
	}

}

package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Answer implements Serializable{
	
	private boolean TrueOrFalse;
	private String[] answer;
	private int examId;
	private int questionNumber;
	
	public Answer(boolean TrueOrFalse, int examId, int questionNumber)
	{
		this.TrueOrFalse = TrueOrFalse;
		this.examId = examId;
		this.questionNumber = questionNumber;
	}
	public Answer(String[] answer, int examId, int questionNumber)
	{
		this.answer = answer;
		this.examId = examId;
		this.questionNumber = questionNumber;
	}
	public String getTFAnswer()
	{
		String str = "";
		if(TrueOrFalse == false)
		{
			return str += "False";
		}
		return str += "True";
	}
	public String getMCAnswer()
	{
		return Arrays.toString(answer);
	}
	public String getFBAnswer()
	{
		String str = "";
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
		str += Arrays.toString(arr);
		return str;
	}
	public String[] getAnswer()
	{
		return answer;
	}
	public int getExamId()
	{
		return examId;
	}	
}

package onlineTest;

import java.util.*;
import java.io.*;

public class SystemManager implements Manager, Serializable {
	
	ArrayList<Exam> exams = new ArrayList<Exam>();
	ArrayList<Student> students = new ArrayList<Student>();
	private String[] letterGrades;
	private double[] cutoffs;
	

	@Override
	public boolean addExam(int examId, String title) {
		for(Exam e : exams)
		{
			if(e.getId() == examId)
			{
				return false;
			}

		}
		exams.add(new Exam(title, examId));
		return true;
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber,
				String text, double points, boolean answer) {
		for(Exam e : exams)
		{
			if(e.getId() == examId)
			{
				for(int index = 0; index < e.getQuestions().size(); index++)
				{
					Question q = e.getQuestions().get(index);
					if(q.getQuestionNumber() == questionNumber)
					{
						e.getQuestions().set(index, new TrueOrFalse(examId,
						questionNumber, text, points, answer));
						return;
					}
				}
				e.getQuestions().add(new TrueOrFalse(examId, questionNumber,
						text, points, answer));
				e.addPoints(points);
			}
		}
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber,
				String text, double points, String[] answer) {
		for(Exam e : exams)
		{
			if(e.getId() == examId)
			{
				for(int index = 0; index < e.getQuestions().size(); index++)
				{
					Question q = e.getQuestions().get(index);
					if(q.getQuestionNumber() == questionNumber)
					{
						e.getQuestions().set(index, new MultipleChoice(examId,
						questionNumber, text, points, answer));
						return;
					}
				}
				e.getQuestions().add(new MultipleChoice(examId, questionNumber,
						text, points, answer));
				e.addPoints(points);
			}
		}	
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
				String text, double points, String[] answer) {
		for(Exam e : exams)
		{
			if(e.getId() == examId)
			{
				for(int index = 0; index < e.getQuestions().size(); index++)
				{
					Question q = e.getQuestions().get(index);
					if(q.getQuestionNumber() == questionNumber)
					{
						e.getQuestions().set(index, 
									new FillInTheBlanks(examId,
									questionNumber, text, points, answer));
						return;
					}
				}
				e.getQuestions().add(new FillInTheBlanks(examId, 
						questionNumber, text, points, answer));
				e.addPoints(points);
			}
		}	
		
	}
	

	@Override
	public String getKey(int examId) {
		String str = "";
		
		for(int index = 0; index < exams.size(); index++)
		{
			if(exams.get(index).getId() == examId)
			{
				ArrayList<Question> examQ = exams.get(index).getQuestions();
				for(int newIndex = 0; newIndex < examQ.size(); newIndex++)
				{
					Question Q = examQ.get(newIndex);
					if(Q instanceof TrueOrFalse)
					{
						str += ((TrueOrFalse) Q).getKey() + "\n";
					}
					else if(Q instanceof MultipleChoice)
					{
						str += ((MultipleChoice) Q).getKey() + "\n";
					}
					else
					{
						str += ((FillInTheBlanks) Q).getKey() + "\n";
					}
				}
				return str;
			}
		}
		return str += "Exam not found";
	}

	@Override
	public boolean addStudent(String name) {
		for(Student s : students)
		{
			if(s.getName().equals(name))
			{
				return false;
			}
		}
		students.add(new Student(name));
		return true;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId,
				int questionNumber, boolean answer) {
		for(int index = 0; index < students.size(); index++)
		{
			Student stu = students.get(index);
			if(stu.getName().equals(studentName))
			{
				stu.addAnswer(new Answer(answer, examId, questionNumber), examId, questionNumber);
			}
		}
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId,
				int questionNumber, String[] answer) {
		for(int index = 0; index < students.size(); index++)
		{
			Student stu = students.get(index);
			if(stu.getName().equals(studentName))
			{
				stu.addAnswer(new Answer(answer, examId, questionNumber), examId, questionNumber);			
			}
		}
		
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId,
				int questionNumber, String[] answer) {
		for(int index = 0; index < students.size(); index++)
		{
			Student stu = students.get(index);
			if(stu.getName().equals(studentName))
			{
				stu.addAnswer(new Answer(answer, examId, questionNumber), examId, questionNumber);
			}
		}
		
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		double studentScore = 0;
		int examIndex = 0;
		for(int index = 0; index < exams.size(); index++)
		{
			if(exams.get(index).getId() == examId)
			{
				examIndex = index;
			}
		}
		Exam exam = exams.get(examIndex);
		for(int index = 0; index < students.size(); index++)
		{
			Student stu = students.get(index);
			if(stu.getName().equals(studentName))
			{
				stu.addScore(new Score(0, examId), examId);
				for(int newIndex = 0; newIndex < stu.getAnswers().get(examId).size(); newIndex++) 
				{	
					Answer answer = stu.getAnswers().get(examId).get(newIndex + 1); //issue HERE
					Score score = stu.getScores(examId);

					if(exam.getQuestions().get(newIndex) instanceof TrueOrFalse)
					{
						TrueOrFalse TF = (TrueOrFalse) exam.getQuestions().get(newIndex);
						if(answer.getTFAnswer().equals(TF.getAnswer()))
						{
							score.addToScore(TF.getPoints());
						}
					}
					//might need some work
					if(exam.getQuestions().get(newIndex) instanceof MultipleChoice)
					{
						MultipleChoice MC = (MultipleChoice) exam.getQuestions().get(newIndex);
						if(answer.getMCAnswer().equals(MC.getStringAnswer()))
						{
							score.addToScore(MC.getPoints());
						}
 					}
					//might need some work
					if(exam.getQuestions().get(newIndex) instanceof FillInTheBlanks)
					{
						FillInTheBlanks FB = (FillInTheBlanks) exam.getQuestions().get(newIndex);
						double eachRightAnswer = FB.getPoints() / FB.getAnswer().length;
						
						for(int counter = 0; counter < answer.getAnswer().length; counter++)
						{
							for(int next = 0; next < FB.getAnswer().length; next++)
							{
								if(answer.getAnswer()[counter].equals(FB.getAnswer()[next]))
								{
									score.addToScore(eachRightAnswer);

								}
							}
						}
 					}
					studentScore = score.getScore();
				}
			}
		}
		return studentScore;
	}
	@Override
	public String getGradingReport(String studentName, int examId) {
		int examIndex = 0;
		double points = 0;
		double totalPoints = 0;
		double finalPoints = 0;
		double examTotalPoints = 0;
		String str = "";
		for(int index = 0; index < exams.size(); index++)
		{
			if(exams.get(index).getId() == examId)
			{
				examIndex = index;
			}
		}
		Exam exam = exams.get(examIndex);			
		for(int index = 0; index < students.size(); index++)
		{
			Student stu = students.get(index);
			if(stu.getName().equals(studentName))
			{
				for(int counter = 0; counter < exam.getQuestions().size(); counter++)
				{
					examTotalPoints += exam.getQuestions().get(counter).getPoints();
				}
				totalPoints = examTotalPoints;
				for(int newIndex = 0; newIndex < stu.getAnswers().get(examId).size(); newIndex++) 
				{	
					Answer answer = stu.getAnswers().get(examId).get(newIndex + 1);
					Score score = stu.getScores(examId);

					if(exam.getQuestions().get(newIndex) instanceof TrueOrFalse)
					{
						TrueOrFalse TF = (TrueOrFalse) exam.getQuestions().get(newIndex);
						if(answer.getTFAnswer().equals(TF.getAnswer()))
						{
							points = TF.getPoints();
							finalPoints += points;

						}
						str += "Question #" + (newIndex + 1) + " " + points + " points out of "
								+ TF.getPoints() + "\n";
						points = 0;
					}
					//might need some work
					if(exam.getQuestions().get(newIndex) instanceof MultipleChoice)
					{
						MultipleChoice MC = (MultipleChoice) exam.getQuestions().get(newIndex);
						if(answer.getMCAnswer().equals(MC.getStringAnswer()))
						{
							points += MC.getPoints();							
						}
						finalPoints += points;
						str += "Question #" + (newIndex + 1) + " " + points + " points out of "
								+ MC.getPoints() + "\n";
						points = 0;

 					}
					if(exam.getQuestions().get(newIndex) instanceof FillInTheBlanks)
					{
						FillInTheBlanks FB = (FillInTheBlanks) exam.getQuestions().get(newIndex);
						double eachRightAnswer = FB.getPoints() / FB.getAnswer().length;
						for(int counter = 0; counter < answer.getAnswer().length; counter++)
						{
							for(int next = 0; next < FB.getAnswer().length; next++)
							{
								if(answer.getAnswer()[counter].equals(FB.getAnswer()[next]))
								{
									points += eachRightAnswer;

								}
							}
						}
						totalPoints = points;
						points = 0;
						str += "Question #" + (newIndex + 1) + " " + totalPoints + " points out of "
								+ FB.getPoints() + "\n";
						finalPoints += totalPoints;
						totalPoints = 0;
 					}
				}
				str += "Final Score: " + finalPoints + " out of " + examTotalPoints;
				finalPoints = 0;
			}
		}
		return str;
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades,
				double[] cutoffs) {
		this.letterGrades = new String[letterGrades.length];
		this.cutoffs = new double[cutoffs.length];
		for(int index = 0; index < letterGrades.length; index++)
		{
			this.letterGrades[index] = letterGrades[index];
		}
		for(int index = 0; index < cutoffs.length; index++)
		{
			this.cutoffs[index] = cutoffs[index];
		}
		
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double score = 0;
		for(int index = 0; index < students.size(); index++)
		{
			int examTracker = 0;
			String name = students.get(index).getName();
			if(name.equals(studentName))
			{
				double studentScore = 0;
				for(int counter = 1; counter <= exams.size(); counter++)
				{
					double examScore = exams.get(examTracker).getScore();
					studentScore += getExamScore(studentName, counter) / examScore;
					examTracker++;
				}
				score = (studentScore / examTracker) * 100;
			}
		}
		return score;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		double numericScore = getCourseNumericGrade(studentName);
		for(int index = 0; index < cutoffs.length - 1; index++)
		{
			if(numericScore < cutoffs[index] && numericScore >= cutoffs[index+1])
			{
				return letterGrades[index+1];
			}
		}
		return letterGrades[0];
	}

	@Override
	public String getCourseGrades() {
		String str = "";
		ArrayList<String> list = new ArrayList<String>();
		for(int index = 0; index < students.size(); index++)
		{
			String words;
			String name = students.get(index).getName();
			words = name + " " + getCourseNumericGrade(name) + " " + getCourseLetterGrade(name) + "\n";
			list.add(words);
		}
		list.sort(null);
		for(int index = 0; index < list.size(); index++)
		{
			str += list.get(index);
		}
		return str;
	}

	@Override
	public double getMaxScore(int examId) {
		double max = 0;
		for(int index = 0; index < students.size(); index++)
		{
			for(int counter = 0; counter < students.size(); counter++)
			{
				double score = getExamScore(students.get(counter).getName(), examId);
				if(score > max)
				{
					max = score;
				}
			}
		}
		return max;
	}

	@Override
	public double getMinScore(int examId)
	{
		double min = 100;
		for(int index = 0; index < students.size(); index++)
		{
			for(int counter = 0; counter < students.size(); counter++)
			{
				double score = getExamScore(students.get(counter).getName(), examId);
				if(score < min)
				{
					min = score;
				}
			}
		}
		return min;
	}

	@Override
	public double getAverageScore(int examId) {
		double totalScore = 0;
		double average = 0;
		for(int index = 0; index < students.size(); index++)
		{
			double score = getExamScore(students.get(index).getName(), examId);
			totalScore += score;
		}
		average = (totalScore / students.size());
		return average;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		try {
		File file = new File(fileName);
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
		
		output.writeObject(manager);
		output.close();
		
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public Manager restoreManager(String fileName) {
		File file = new File(fileName);
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			Manager manager = (Manager) input.readObject();
			return manager;
			
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return null;
	}

}

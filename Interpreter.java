package cmdLineInterpreter;

import java.util.Scanner;

import javax.swing.JOptionPane;

import onlineTest.*;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {

	public static void main(String[] args) {
		SystemManager manager = new SystemManager();
		Scanner keyboard = new Scanner(System.in);
		
		int choice;
		
		do {
			String menu = "---------MENU--------\n";
			menu +=	"Enter 1 to add a new student\n";
			menu += "Enter 2 to add a new exam\n";
			menu += "Enter 3 to add a true/false question\n";
			menu += "Enter 4 to answer a true/false question\n";
			menu += "Enter 5 to get an exam score for a student\n";
			menu += "Enter 6 to quit";
			
			System.out.println(menu);
			System.out.print("Please enter your choice: ");
			choice = keyboard.nextInt();
			keyboard.nextLine();
			String studentName;
			String examName, text, strAnswer;
			int examId, questionNumber;
			double points, score;
			boolean answer;
			switch(choice) {
			
			case 1:
				System.out.print("Please enter the students name: ");
				studentName = keyboard.nextLine();
				manager.addStudent(studentName);
				System.out.println("------Name entered------");
				System.out.println("");
				break;
			case 2:
				System.out.print("Please enter the exam Id: ");
				examId = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("Please enter the exam name: ");
				examName = keyboard.nextLine();
				manager.addExam(examId, examName);
				System.out.println("------Exam entered------");
				System.out.println("");
				break;
			case 3:
				System.out.print("Please enter the question text: ");
				text = keyboard.nextLine();
				System.out.print("Please enter the exam ID: ");
				examId = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("Please enter the question number: ");
				questionNumber = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("How many points is this question worth? ");
				points = keyboard.nextDouble();
				System.out.print("What is the question's answer? ");
				answer = keyboard.nextBoolean();
				manager.addTrueFalseQuestion(examId, questionNumber, text, points, answer);
				System.out.println("------Question entered------");
				System.out.println("");
				break;
			case 4:
				System.out.print("Enter the student name that is answering the question: ");
				studentName = keyboard.nextLine();
				System.out.print("Enter the exam Id that the answer is for: ");
				examId = keyboard.nextInt();
				System.out.print("Enter the question number: ");
				questionNumber = keyboard.nextInt();
				System.out.print("Enter the answer: ");
				strAnswer = keyboard.next();
				answer = Boolean.parseBoolean(strAnswer);
				manager.answerTrueFalseQuestion(studentName, examId, questionNumber, answer);
				System.out.println("------Question answered------");
				System.out.println("");
				break;
			case 5:
				System.out.print("Enter the student's name: ");
				studentName = keyboard.nextLine();
				System.out.print("Enter the exam Id: ");
				examId = keyboard.nextInt();
				score = manager.getExamScore(studentName, examId);
				System.out.println(studentName + "'s score is " + score);
				System.out.println("");
				break;
				
			default:
				JOptionPane.showInputDialog(null, "Invalid choice");
			}
			
		}while(choice != 6);
		
	}
}

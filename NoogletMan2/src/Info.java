package MainFiles;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

abstract public class Info {
	private String question;
	private String answer;
	private String guess;
	private int lives;
	private boolean head;
	private boolean body;
	private boolean arms;
	private boolean legs;
	int currentIndex = 0;
	
	
	
	String[][] info = {
			{"<html>What is the process where you extend an existing class to create a subclass instead of creating an entirely new class?</html>", "Inheritance"},
			{"<html>What is the relationship called when a class is derived from an existing class</html>","IsA"},
			{"<html>What is the procress where a subclass has the same signature as a public method of the superclass</html>","Overriding"},	
			{"<html>What is the keyword you use when you want to refer to a field or method that belongs to the superclass?</html>", "Super"},
			{"<html>What keyword describes more general details and actions of a type of object</html>", "Abstract"},
			{"<html>What refers to the capability of objects to react differently to the same method?</html>", "Polymorphism"},
			{"<html>What helps programmers organize the programming design process?</html>", "UML"},
			{"<html>What is the design concept that facilitates the bundling of data with the methods operating on that data?</html>", "Encapsulation"},
			{"<html>Which access modifier can be accessed from any class?</html>", "Public"},
			{"<html>Which access modifier can be accessed from only within its own classes?</html>", "Private"},
			{"<html>Which access modifier can be accessed from any class in the same package or its subclasses?</html>", "Protected"}	
			};
	
	public Info() { //(String question, String answer){
		this.question = info[currentIndex][0];//question;
		this.answer = info[currentIndex][1];//answer;
		this.guess = "";
		this.head = false;
		this.body = false;
		this.arms = false;
		this.legs = false;
		
	}
	
	
	//getters
	public String getQuestion() {
		return(question);
	}
	public String getAnswer() {
		return(answer);
	}
	public int getLives() {
		return(lives);
	}
	public String getGuess() {
		return(guess);
	}
	public boolean getHead() {
		return(head);
	}
	public boolean getBody() {
		return(body);
	}
	public boolean getArms() {
		return(arms);
	}
	public boolean getLegs() {
		return(legs);
	}
	
	//setters
	public void setQuestion(String input) {
		question = input;
	}
	public void setAnswer(String input) {
		answer = input;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public void setGuess(String input) {
		guess = input;
	}
	public void setHead(boolean input) {
		head = input;
	}
	public void setBody(boolean input) {
		body = input;
	}
	public void setArms(boolean input) {
		arms = input;
	}
	public void setLegs(boolean input) {
		legs = input;
	}
	
	public String toString() {
		return "Question: " + getQuestion() +", Answer: " + getAnswer() + ", Guess: " + getGuess() + ", Lives: " + getLives() + ", Head: " + getHead() +
				", Body: " + getBody() + ", Arms: " + getArms() + ", Legs: " + getLegs();
	}
	
	public boolean includesChar(String input) {
		boolean output = false;
		for(int i = 0 ;i<this.answer.length();i++) {
			if(String.valueOf(this.answer.charAt(i)).equalsIgnoreCase(input)) {
				output = true;
			}
		}
		return output;
	}
	
	public String hideAnswer() {
		String output = "";	
		for(int i = 0; i<answer.length(); i++) {
			output+="#";
			
		}
		return output;
	}
	
	public void updateAnswer(String letter) {
		String testChar = letter.toLowerCase();
		String[] guessArr = guess.split("");
		System.out.println(guessArr[0]+guessArr[1]+guessArr[2]);
		String output = "";
		String testWord = answer.toLowerCase();
		ArrayList<Integer> index = new ArrayList<>();
		int indices = testWord.indexOf(testChar);
		while (indices >= 0) {
		    index.add(indices);   
		    System.out.println("index: "+indices);
		    indices = testWord.indexOf(testChar, indices + 1);
		}
		for(int i = 0; i<index.size();i++) {
			
			guessArr[index.get(i)] = " ";	
		}
		System.out.println("new: "+guessArr[0]+guessArr[1]+guessArr[2]);
		for(int i = 0;i<guessArr.length;i++) {
			output+=guessArr[i];
		}
		System.out.println("output: "+output);
		output = output.replaceAll(" ", letter);
		System.out.println("output new: "+output);
		setGuess(output);
	}
	
	public void nextQA() {
		System.out.println(toString());
		
		currentIndex++;
		if(currentIndex>=info.length) {
			JOptionPane.showConfirmDialog(null, "<html>Congratulations!<br/>You have completed all the levels.</br>Thanks for playing:)</html>", "Game Complete", JOptionPane.DEFAULT_OPTION);							
			System.exit(0);
		}
		setQuestion(info[currentIndex][0]);
		setAnswer(info[currentIndex][1]);
		setGuess("");
		setHead(false);
		setBody(false);
		setArms(false);
		setLegs(false);
		setLives();
		System.out.println("AFTER::::::" + toString());
	
		}
	
	public boolean checkGuess() {
		return(guess.equalsIgnoreCase(answer));
	}
	
	
	
	
	
			
	abstract public void setLives();
	abstract public void updateHangMan();
	
	
	
	

	
	
	
}

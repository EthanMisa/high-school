import java.util.Scanner;
public class MathTutor {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int firstNum= (int)((10-1+1)*Math.random()+1);
		int secondNum= (int)((10-1+1)*Math.random()+1);
		int operation= (int)((4-1+1)*Math.random()+1);
		int answer;//equation answer
		
		int guess=0; //users guess
		if(operation==1){//addition
			answer=firstNum+secondNum;
			System.out.println("What is "+firstNum+" + "+secondNum);
			guess=scan.nextInt();
			if(guess==answer){
				System.out.println("Correct!");
			}
			else{
				System.out.println("Incorrect. The answer is "+(firstNum+secondNum));
			}
		}
		else if (operation==2){ //subtraction
			answer=firstNum-secondNum;
			System.out.println("What is "+firstNum+" - "+secondNum);
			guess=scan.nextInt();
			if(guess==answer){
				System.out.println("Correct!");
			}
			else{
				System.out.println("Incorrect. The answer is "+(firstNum-secondNum));
			}
		}
		else if (operation==3){ //multiplication
			answer=firstNum*secondNum;
			System.out.println("What is "+firstNum+" * "+secondNum);
			guess=scan.nextInt();
			if(guess==answer){
				System.out.println("Correct!");
			}
			else{
				System.out.println("Incorrect. The answer is "+(firstNum*secondNum));
			}
		}
		else if (operation==4){ //division
			answer=(firstNum/secondNum);
			System.out.println("What is "+firstNum+" / "+secondNum);
			guess=scan.nextInt();
			if(guess==answer){
				System.out.println("Correct!");
			}
			else{	
				System.out.println("Incorrect. The answer is "+answer);
			}
		}
		else{}
		scan.close();
		
	}

}

import java.util.Scanner;
public class TournamentSelection {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the win/loss for six games:");
		int count=0;
		String answer;
		int x=0;
		while (x<6){
			answer=scan.next();
			if(answer.equals("W")){
				count=count+1;
			}
			x=x+1;
		}
		if (count==5||count==6){
			System.out.println("Group 1");
		}
		else if (count==4||count==3){
			System.out.println("Group 2");
		}
		else if (count==1||count==2){
			System.out.println("Group 3");
		}
		else{
			System.out.println("-1");
		}
	}

}

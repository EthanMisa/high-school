import java.util.Scanner;
public class SurfsUp3 {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("How high are the waves?");
		int wave=scan.nextInt();
		if (wave>=6){
			System.out.println("Go surfing!");
		}
		else if (wave>=3){
			System.out.println("Go body surfing!");
		}
		else if (wave>=0){
			System.out.println("Go for a swim!");
		}
		else{
			System.out.println("What kind of waves are those");
		}
		scan.close();

	}

}

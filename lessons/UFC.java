import java.util.Scanner;
public class UFC {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("What is the fighter's name?");
		String name=scan.nextLine();
		System.out.println("What is the fighters weight?");
		int weight=scan.nextInt();
		scan.close();
		if (weight<145){
			System.out.println(name + " is unavailable to fight.");
		}
		else if (weight<=155){
			System.out.println(name + " can fight in the lightweight catagory");
		}
		else if (weight<=170){
			System.out.println(name + " can fight in the welterweight catagory");
		}
		else if (weight<=185){
			System.out.println(name + " can fight in the light heavyweight catagory");
		}
		else if (weight<=265){
			System.out.println(name + " can fight in the heavyweight catagory");
		}
		else{
			System.out.println(name + " is unavailable to fight.");
		}

	}

}

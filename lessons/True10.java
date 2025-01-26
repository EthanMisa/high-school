import java.util.Scanner;
public class True10 {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num=scan.nextInt();
		if (num%10==0||(num-1)%10==0||(num-2)%10==0){
			System.out.println("true");
		}
		else{
			System.out.println("false");
		}
	}

}

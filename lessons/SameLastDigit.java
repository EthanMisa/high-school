import java.util.Scanner;
public class SameLastDigit {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter first number:");
		int first=scan.nextInt();
		System.out.println("Enter second number:");
		int second=scan.nextInt();
		System.out.println("Enter third number:");
		int third=scan.nextInt();
		if (first>0&&second>0&&third>0){
			if (first%10==second%10 || first%10 == third%10 || third%10==second%10){
				System.out.println("True");
			}
			else {
				System.out.println("False");
			}
		}
		else{
			System.out.println("Error: Negative numbers");
		}
	}

}

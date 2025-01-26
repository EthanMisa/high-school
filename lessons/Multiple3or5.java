import java.util.Scanner;
public class Multiple3or5 {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num=scan.nextInt();
		if (num>0){
			if ((num%3==0&&num%5!=0)||(num%5==0&&num%3!=0)){
				System.out.println("True");
			}
			else{
				System.out.println("False");
			}
		}
		else{
			System.out.println("Error: Negative Number");
		}

	}

}

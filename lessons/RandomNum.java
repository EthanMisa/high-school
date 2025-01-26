import java.util.Scanner;
public class RandomNum {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("What is the min value?");
		int min=scan.nextInt();
		System.out.println("What is the max value?");
		int max=scan.nextInt();
		int ran=(int)((max-min+1)*Math.random()+min);
		System.out.println("A random number from " + min + " and " + max + " is " + ran);
		System.exit(1);
	}

}

import java.util.Scanner;
public class TestIfStatement {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int x=0;
		System.out.println("Input right front pressure");
		int fr=scan.nextInt();
		if (!(fr>=35&&fr<=45)){
			System.out.println("Warning: pressure is out of range\n");
			x=x+1;
		}
		System.out.println("Input left front pressure");
		int fl=scan.nextInt();
		if (!(fl>=35&&fl<=45)){
			System.out.println("Warning: pressure is out of range\n");
			x=x+1;
		}
		System.out.println("Input right rear pressure");
		int rr=scan.nextInt();
		if (!(rr>=35&&rr<=45)){
			System.out.println("Warning: pressure is out of range\n");
			x=x+1;
		}
		System.out.println("Input left rear pressure");
		int rl=scan.nextInt();
		if (!(rl>=35&&rl<=45)){
			System.out.println("Warning: pressure is out of rang\n");
			x=x+1;
		}
		if((fr==fl&&rr==rl)&&x==0){
			System.out.println("Inflation is OK");
		}
		else{
			System.out.println("Inflation is BAD");
		}
	

	}

}

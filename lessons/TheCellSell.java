import java.util.Scanner;
public class TheCellSell {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Daytime minutes:");
		int daytime=scan.nextInt();
		System.out.println("Evening minutes:");
		int evening=scan.nextInt();
		System.out.println("Weekend minutes:");
		int weekend=scan.nextInt();
		double planA,planB;
		if(daytime>100){
			planA=((daytime-100)*0.25)+(evening*0.15)+(weekend*0.20);
		}
		else{
			planA=(evening*0.15)+(weekend*0.20);
		}
		if(daytime>250){
			planB=((daytime-250)*0.45)+(evening*0.35)+(weekend*0.25);
		}
		else{
			planB=(evening*0.35)+(weekend*0.25);	
		}
		planA=Math.round(planA*100.00)/100.00;
		planB=Math.round(planB*100.00)/100.00;
		System.out.println("Plan A: $"+planA);
		System.out.println("Plan B: $"+planB);
		if(planA==planB){
			System.out.println("Plan A and Plan B are the same price.");
		}
		if(planA>planB){
			System.out.println("Plan A is cheapest");
		}
		if(planA<planB){
			System.out.println("Plan B is cheapest");
		}
		scan.close();
	}

}

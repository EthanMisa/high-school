import java.util.Scanner;
public class WeightOnADifferentPlanet {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter your weight:");
		double weight=scan.nextDouble();
		System.out.println("Where do you want to travel:");
		String place=scan.next();
		scan.close();
		if (place.equals("Earth")){
			weight=weight*1;
		}
		else if (place.equals("Jupiter")){
			weight=weight*((double)21/8);
		}
		else if (place.equals("Mars")){
			weight=weight*((double)3/8);
		}
		else if (place.equals("Mercury")){
			weight=weight*((double)3/10);
		}
		else if (place.equals("Neptune")){
			weight=weight*((double)11/10);
		}
		else if (place.equals("Neptune")){
			weight=weight*((double)7/10);
		}
		else if (place.equals("Saturn")){
			weight=weight*((double)6/5);
		}
		else if (place.equals("Uranus")){
			weight=weight*((double)9/10);
		}
		else if (place.equals("Venus")){
			weight=weight*((double)7/8);
		}
		else{
			weight=0;
			System.out.println("Invalid Planet.");
		}
		System.out.println("Your weight on " + place + " is: " +  weight);
	
	}

}

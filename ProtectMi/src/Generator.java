package ProtectMi;

public class Generator {
	static char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
			'p','q','r','s','t','u','v','w','x','y','z'};
	char[] specChar = {'@','$','%','&','*','<','>','?'};
	public static void main(String[] args) {
		System.out.print(Generator());
	}
	
	public static String Generator() {//int length, boolean letters, boolean numbers, boolean specChar) {
		String password = "";
		password=password+letterChose();
		return password;
	}
	
	static char letterChose() {
		return letters[(int)(Math.random()*(letters.length-0)+0)];
	}

}

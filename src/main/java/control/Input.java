package control;

import java.util.Scanner;

public class Input {
	private Scanner in;
	public Input() {
		this.in = new Scanner(System.in);
	}
	
	public String readLine() {
		return in.nextLine().trim();
	}
	
	public int readInt() {
		int x = 0;
		if(in.hasNextInt()) {
			x = in.nextInt();
		} else {
			System.out.println("Invalid input, please enter a integer");
		}
		in.nextLine();
		return x;
	}
	
	public boolean readOption(String question) {
		String ans;
		do {
			System.out.print(question+"(y/n) ");
			ans = readLine();
		}while(!ans.matches("^[YyNn].*$"));
		return ans.matches("^[Yy].*$");
	}
	
}

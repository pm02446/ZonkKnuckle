package battleship;

import java.util.Scanner;

import javafx.application.Application;

public class Launcher {

	public static void main(String[] args) {
		// :c
		System.out.println("Welcome to battleship! What would you like to launch?\n1: Host\n2: Client\n3: Demo");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		switch (input){
			case 1:
				System.out.println("Not yet implemented.");
				break;
			case 2:
				System.out.println("Not yet implemented.");
				break;
			case 3: 
				Application.launch(MainDemo.class);
				break;
			default:
				System.out.println("what?");
		}
		in.close();
	}
	
	
}

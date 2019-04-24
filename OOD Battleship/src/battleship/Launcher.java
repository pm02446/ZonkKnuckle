package battleship;

import java.util.Scanner;

import javafx.application.Application;

public class Launcher {

	public static void main(String[] args) {
		// :c
		System.out.println("Welcome to battleship! What would you like to launch?\n1: Host\n2: Client\n3: Demo \n4: Mystery");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		switch (input){
			case 1:
				Application.launch(HostMain.class);
				break;
			case 2:
				Application.launch(ClientMain.class);
				break;
			case 3: 
				Application.launch(MainDemo.class);
				break;
			case 4:
				//TODO: it's fucked right now I'm gonna fix it -A
				//Application.launch(MainEnemy.class);
				System.out.println("it's fucked");
				break;
			default:
				System.out.println("what?");
		}
		in.close();
	}
	
	
}

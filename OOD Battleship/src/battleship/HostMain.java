package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import javax.swing.JOptionPane;

import battleship.ClientMain.ClientReader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HostMain extends Main {
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static InputStreamReader isr;
	static BufferedReader reader;
	static PrintWriter writer;
	
	public static void main(String[] args) {
		launch();
	}
	
	String msgout = "X|X|error";
	String msgin = "";
	boolean dead = false;

	@Override
	public void start(Stage primStage) throws Exception {
		Pane contentPane = totalInit();
		Scene boardScene = new Scene(contentPane);
		
		  //TextField ip = new TextField("ip address"); TextField port = new
		  //TextField("port"); ip.setLayoutY(170); ip.setLayoutX(5);
		  //port.setLayoutY(200); port.setLayoutX(5); 
		 
		  Button btnHost = new
		  Button("Host"); 
		  btnHost.setLayoutY(170); 
		  btnHost.setLayoutX(170);
		/*
		 * btnHost.setOnMouseClicked(new EventHandler<MouseEvent>(){ public void
		 * handle(MouseEvent event) { me.fact.makeCommand(me,
		 * "2|3|miss|response").execute(); } });
		 */
		  //contentPane.getChildren().add(ip); contentPane.getChildren().add(port);
		  contentPane.getChildren().add(btnHost);
		 
		primStage.setScene(boardScene);
		primStage.show();
		//TODO: REMOVE this bitch
		boardPlayerState[2][3].addShip(new ExShip(boardPlayerState[2][3],  new Space[]{boardPlayerState[2][3]}));
		redrawBoards();
		//okay here we go again
		//makes a background thread to read from the socket
		new Thread(() -> {
			try {
				ss = new ServerSocket(1201);
				s = ss.accept();
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				isr = new InputStreamReader(s.getInputStream());
				//ideally this loop will allow us to continually read inputs from the client
				while(!dead) { 
					msgout = "";
					msgin = din.readUTF();
					String splat;
					System.out.println("in "+ msgin);
					if(!msgin.equals("")) {
						Platform.runLater(() -> msgout = this.fact.makeCommand(this, msgin).execute());
						Platform.runLater(() -> System.out.println("out "+msgout));
						 splat = msgin.split("\\|")[3];
						if(!splat.equals("response")){
							Platform.runLater(() -> {
								try {
									dout.writeUTF(msgout);
								} catch (IOException e) {
									e.printStackTrace();
								}
							});
						}
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}).start();
		//beginHosting();
	}

	@Override
	void makeCommands(Space target) {
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack|init");
		System.out.println("man "+ msg);
		try {
			dout.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Command out = this.fact.makeCommand(msg);
	}
	
	private void beginHosting() {
		ClientHandler reader = new ClientHandler(this);
		Thread readThread = new Thread(reader);
		readThread.run();
	}
	
	public class ClientHandler implements Runnable {
		HostMain me;
		public ClientHandler(HostMain me) {
			this.me = me;
		}
		public void run() {
			//here we go
			//set up the sockets
			
		}
	}

	@Override
	public String toString() {
		return "hostmain";
	}

}

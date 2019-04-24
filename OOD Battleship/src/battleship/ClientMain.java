package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientMain extends Main {
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	private BufferedReader reader;
	private PrintWriter writer;
	
	boolean dead = false;
	public static void main(String[] args0) {
		launch();
	}
	String msgin = "";
	String msgout = "X|X|error";
	@Override
	public void start(Stage primStage) throws Exception {
		Pane contentPane = totalInit();
		Scene boardScene = new Scene(contentPane);
		  Button btnJoin = new Button("Join"); 
		  btnJoin.setLayoutY(170); 
		  btnJoin.setLayoutX(170);
		  //contentPane.getChildren().add(ip); contentPane.getChildren().add(port);
		  //contentPane.getChildren().add(btnJoin);
		  btnJoin.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					joinServer();
				}
			});
		
		primStage.setScene(boardScene);
		primStage.show();
		new Thread(() -> {
			try {
				s = new Socket("127.0.0.1",1201);
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				//ideally this loop will allow us to continually read inputs from the client
				while(!dead) { 
					msgout = "";
					msgin = din.readUTF();
					String splat;
					System.out.println("in "+ msgin);
					if(!msgin.equals("")) {
						splat = msgin.split("\\|")[3];
						System.out.println("msgin");
						Platform.runLater(() -> msgout = this.fact.makeCommand(this, msgin).execute());
						Platform.runLater(() -> System.out.println("out "+msgout));
						if(!splat.equals("response")){
							Platform.runLater(() -> {
								try {
									System.out.println("Sent back");
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
		//joinServer();
	}

	@Override
	void makeCommands(Space target) {
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack|init");
		try {
			dout.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void joinServer() {
		ClientReader reader = new ClientReader(this);
		Thread readThread = new Thread(reader);
		readThread.run();
	}
	
	public class ClientReader implements Runnable {
		ClientMain me;
		public ClientReader(ClientMain me) {
			this.me = me;
		}
		public void run() {
			//here we go
			String msgin = "";
			//set up the sockets
			try {
				s = new Socket("127.0.0.1",1201);
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				//ideally this loop will allow us to continually read inputs from the server
				while(true) {
					msgin = din.readUTF();
					String send = me.fact.makeCommand(me, msgin).execute();
					dout.writeUTF(send);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "ClientMain";
	}

}

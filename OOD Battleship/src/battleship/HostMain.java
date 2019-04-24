package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.*;

import battleship.ClientMain.ClientReader;
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
	private BufferedReader reader;
	private PrintWriter writer;
	
	
	

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
		  btnHost.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					beginHosting();
				}
			});
		  //contentPane.getChildren().add(ip); contentPane.getChildren().add(port);
		  contentPane.getChildren().add(btnHost);
		 
		primStage.setScene(boardScene);
		primStage.show();
		
	}

	@Override
	void makeCommands(Space target) {
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack");
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
			String msgin = "";
			//set up the sockets
			try {
				ss = new ServerSocket(1201);
				s = ss.accept();
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				//ideally this loop will allow us to continually read inputs from the client
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

}
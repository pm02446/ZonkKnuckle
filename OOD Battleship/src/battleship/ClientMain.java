package battleship;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientMain extends Main {
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	boolean dead = false;
	String msgin = "";
	String msgout = "X|X|error";
	int port;
	String ipAddress;

	public static void main(String[] args0) {
		launch();
	}
	//rebererberbe
	
	@Override
	public void start(Stage primStage) throws Exception {
		//myTurn = false;
		//UI stuff
		Pane contentPane = totalInit();
		Scene boardScene = new Scene(contentPane);
		Label lblPort = new Label("Port");
		lblPort.setLayoutX(160);
		lblPort.setLayoutY(360);
		TextField txtPort = new TextField();
		TextField txtIP = new TextField();
		txtIP.setLayoutX(200);
		txtIP.setLayoutY(355);
		txtPort.setLayoutY(355);
		Button btnJoin = new Button("Join");
		btnJoin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						port = Integer.parseInt(txtPort.getText());
						ipAddress = txtIP.getText();
						startHandler();
					}
				});
			}
		});
		
		contentPane.getChildren().add(lblPort);
		contentPane.getChildren().add(txtIP);
		contentPane.getChildren().add(txtPort);
		contentPane.getChildren().add(btnJoin);
		btnJoin.setLayoutY(385);
		primStage.setScene(boardScene);
		primStage.show();
	}
	
	//this method creates and starts the client handler thread- it continually listens to the socket in the background and uses the results to make commands
	private void startHandler() {
		new Thread(() -> {
			try {
				s = new Socket(ipAddress,port);
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				//ideally this loop will allow us to continually read inputs from the server
				while(!dead) {
					msgout = "";
					msgin = din.readUTF();
					//These strings are used for holding the message footer- splat is init or response, splot is the sender
					String splat;
					String splot;
					if(!msgin.equals("")) {
						//if the message received is not blank, start processing
						String[] splitArr = msgin.split("\\|");
						splat = splitArr[3];
						splot = splitArr[4];
						//if the command received is for us, we want to execute it
						if(splot.equals("fromHostmain")){
							//execute the command and store the response if it's from the right sender
							msgout = this.fact.makeCommand(this, msgin).execute();
						}
						else {
							splat = "response"; //force it not to run if it's not
						}
						if(!splat.equals("response")){
							//if it's already a response, don't respond
							// (responding to a response makes it re-execute)
							try {
								dout.writeUTF(msgout);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				return;
				//kill thread when dead
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void stop() {
		try {
			dead = true;
			din.close();
			dout.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	void makeCommands(Space target) {
		//When you click a space, make an attack command and ping it away
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack|init|fromClientmain");
		
		try {
			dout.writeUTF(msg);
			setTurn(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Clientmain";
	}

	//Placement of 5 ships only on non occupied spaces
	@Override
	void shipPlacement(Space selection) {
		int ex = selection.x;
		int ey = selection.y;
		Space[] spaces;
		Space origin = boardPlayerState[ex][ey];
		ExShip newShip;
		switch (shipsPlaced) {
		case 0:
			if (!selection.hasShip) {
				newShip = new ExShip(origin,new Space[] {origin}); 
				ships.add(newShip);
				redrawBoards();
				shipsPlaced++;
			}
			break;

		case 1:
			if (!selection.hasShip) {
				newShip = new ExShip(origin,new Space[] {origin}); 
				ships.add(newShip);
				redrawBoards();
				shipsPlaced++;
			}
			break;

		case 2:
			if (!selection.hasShip) {
				newShip = new ExShip(origin,new Space[] {origin}); 
				ships.add(newShip);
				redrawBoards();
				shipsPlaced++;
			}
			break;

		case 3:
			if (!selection.hasShip) {
				newShip = new ExShip(origin,new Space[] {origin}); 
				ships.add(newShip);
				redrawBoards();
				setTurn(false);
				shipsPlaced++;
			}
			break;

		default:
			break;

		}

	}

}

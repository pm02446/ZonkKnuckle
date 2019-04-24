package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientMain extends Main {
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	private BufferedReader reader;
	private PrintWriter writer;
	
	@Override
	public void start(Stage primStage) throws Exception {
		Pane contentPane = totalInit();
		
		Scene boardScene = new Scene(contentPane);
		primStage.setScene(boardScene);
		primStage.show();
	}

	@Override
	void makeCommands(Space target) {
		// TODO Auto-generated method stub
		
	}

}

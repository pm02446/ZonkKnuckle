package battleship;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainEnemy extends Main{

	Space[][] foeBoardPlayerState = new Space[8][8];
	Space[][] foeBoardFoeState = new Space[8][8];
	
	public void start(Stage primStage) throws Exception {
		Pane boardPane = totalInit();
		Scene boardScene = new Scene(boardPane);
		primStage.setScene(boardScene);
		//Initializes enemy board state
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				foeBoardPlayerState[x][y] = new Space(false, x, y); //space constructor w/ boolean determines target/playfield status
				foeBoardFoeState[x][y] = new Space(true,x, y);
			}
		}
		
		primStage.show();
		createShips();		
		redrawBoards();
	}
	
	//abstract method called by target space listeners
	void makeCommands(Space target) {
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack");
		me.fact.makeCommand(me, msg).execute(foeBoardPlayerState);		
		int ax = (int) (Math.random()*8);
		int ay = (int) (Math.random()*8);
		msg = (ax+"|"+ay+"|attack");
		me.fact.makeCommand(me, msg).execute();	
	}
	
	public void createShips(){
		//creates enemy ships at a random layout, will need to use z to choose a direction when size is implemented.
		//I swear this was working at one point, it's fixed if it is broken it is your fault.
		int x;
		int y;
		int z;
		int fiveShips;
		for(fiveShips = 0; fiveShips < 5; fiveShips++) {
			x = (int) ((Math.random()*8));
			y = (int) ((Math.random()*8));
			z = (int) ((Math.random()*2));
			foeBoardPlayerState[x][y].addShip(new ExShip(foeBoardPlayerState[x][y], new Space[]{foeBoardPlayerState[x][y]}));

		}
		for(fiveShips = 0; fiveShips < 5; fiveShips++) {
			x = (int) ((Math.random()*8));
			y = (int) ((Math.random()*8));
			z = (int) ((Math.random()*2));
			boardPlayerState[x][y].addShip(new ExShip(boardPlayerState[x][y], new Space[]{boardPlayerState[x][y]}));

		}
	}
}

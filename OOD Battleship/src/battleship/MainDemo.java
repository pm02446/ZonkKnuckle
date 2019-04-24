package battleship;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainDemo extends Main {
	@Override
	public void start(Stage primStage) throws Exception {
		//initialize it all
		Pane boardPane = totalInit();
		//TODO: Remove demo settings
		//Force a ship to spawn at 3, 4
		//boardPlayerState[3][4].addShip(new ExShip(boardPlayerState[3][4], new Space[]{boardPlayerState[3][4]}));
		new ExShip(boardPlayerState[3][4], new Space[]{boardPlayerState[3][4]});
		redrawBoards();
		//actually display shit
		Scene boardScene = new Scene(boardPane);
		primStage.setScene(boardScene);
		primStage.show();
		
	}
	

	@Override
	void makeCommands(Space target) {
		int ex = target.x;
		int ey = target.y;
		String msg = (ex+"|"+ey+"|attack");
		//TODO: Remove wacky demo settings
		//because this is a weirdo demo, the factory will create the command targeting ourselves
		//oh god oh fuck
		me.fact.makeCommand(me,me.fact.makeCommand(me, msg).execute()).execute();		
	}

}

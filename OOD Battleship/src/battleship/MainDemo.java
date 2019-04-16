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
		Pane boardPane = new Pane();
		boardPane.setMinSize(330, 270);
		boardPane.setMaxSize(330,270);
		ImageView boardPlayDisp = new ImageView(boardPlayer);
		ImageView boardFoeDisp = new ImageView(boardFoe);
		boardFoeDisp.setLayoutX(170);
		boardPane.getChildren().add(boardPlayDisp);
		boardPane.getChildren().add(boardFoeDisp);
		//initialize everything
		totalInit();
		//add all those pictures to the pane
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				boardPane.getChildren().add(boardPlayerState[x][y]);
				boardPane.getChildren().add(boardFoeState[x][y]);
			}
		}
		//TODO: Remove demo settings
		//Force a ship to spawn at 3, 4
		boardPlayerState[3][4].addShip(new ExShip(boardPlayerState[3][4], new Space[]{boardPlayerState[3][4]}));
		redrawBoards();
		//actually display shit
		Scene boardScene = new Scene(boardPane);
		primStage.setScene(boardScene);
		primStage.show();
		
	}
	
	//for starting the program
	@Override
	public void totalInit() {
		//actually make Spaces (which extend imageviews) for each space
		//then initialize space arrays and draw based on them
		reinitBoards();
		redrawBoards();
		//THEN add listeners to every single one of them
		//this is the observer pattern here:
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				boardFoeState[x][y].setOnMouseClicked(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent event) {
						Space source = (Space)event.getSource();
						//FOES/targets ARE ON THE RIGHT
						int ex = source.x;
						int ey = source.y;
						String msg = (ex+"|"+ey+"|attack");
						//TODO: Remove wacky demo settings
						//because this is a weirdo demo, the factory will create the command targeting ourselves
						me.fact.makeCommand(me, me, msg).execute();		
					}
				});
			}	
		}
		
	}

}

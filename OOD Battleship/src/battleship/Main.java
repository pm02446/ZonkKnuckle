package battleship;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.*;



public class Main extends Application{
	//I ADDED THIS ON A FORK
	//AND THIS ON THE MAIN
	Main me = this;
	boolean myTurn = true;
	ReceivedCommandFactory fact = new ReceivedCommandFactory();
	Image boardPlayer = new Image("https://i.imgur.com/UIOEQRN.png");
	Image boardFoe = new Image("https://i.imgur.com/UIOEQRN.png");
	Space[][] boardPlayerState = new Space[8][8];
	Space[][] boardFoeState = new Space[8][8];
	ImageView[][] playerImgs = new ImageView[8][8];
	ImageView[][] foeImgs = new ImageView[8][8];
	
	public static void main(String[] args) {
		launch();
	}
	
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
				boardPane.getChildren().add(playerImgs[x][y]);
				boardPane.getChildren().add(foeImgs[x][y]);
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
	public void totalInit() {
		//actually make ImageViews for each space
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				playerImgs[x][y] = new ImageView();
				foeImgs[x][y] = new ImageView();
			}	
		}
		//then initialize space arrays and draw based on them
		reinitBoards();
		redrawBoards();
		//THEN add listeners to every single one of them
		//this is the observer pattern here:
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				foeImgs[x][y].setOnMouseClicked(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent event) {
						ImageView source = (ImageView)event.getSource();
						//FOES/targets ARE ON THE RIGHT
						double testvar = source.getLayoutX();
						double ax = (source.getLayoutX()-170)/20;
						double ay = source.getLayoutY()/20;
						int ex = (int)ax;
						int ey = (int)ay;
						String msg = (ex+"|"+ey+"|attack");
						//TODO: Remove wacky demo settings
						//because this is a weirdo demo, the factory will create the command targeting ourselves
						me.fact.makeCommand(me, me, msg).execute();		
					}
				});
			}	
		}
		
	}
	//command to update ImageViews based on state of Space arrays
	public void redrawBoards() {
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				playerImgs[x][y].setImage(boardPlayerState[x][y].spacePic);
				playerImgs[x][y].setLayoutX(x*20);
				playerImgs[x][y].setLayoutY(y*20);
				foeImgs[x][y].setImage(boardFoeState[x][y].spacePic);
				foeImgs[x][y].setLayoutX(170+(x*20));
				foeImgs[x][y].setLayoutY(y*20);
			}
		}
	}
	
	//initialize the board state Space arrays to be blank on both sides
	public void reinitBoards() {
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				boardPlayerState[x][y] = new Space(false, x, y); //space constructor w/ boolean determines target/playfield status
				boardFoeState[x][y] = new Space(true,x, y);
			}
		}
	}
	


}

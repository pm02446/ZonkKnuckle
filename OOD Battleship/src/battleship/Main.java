package battleship;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.*;



public abstract class Main extends Application{
	Main me = this;
	boolean myTurn = true;
	ReceivedCommandFactory fact = new ReceivedCommandFactory();
	Image boardPlayer = new Image("https://i.imgur.com/UIOEQRN.png");
	Image boardFoe = new Image("https://i.imgur.com/UIOEQRN.png");
	Space[][] boardPlayerState = new Space[8][8];
	Space[][] boardFoeState = new Space[8][8];
	
	public static void main(String[] args) {
		launch();
	}
	
	public abstract void start(Stage primStage) throws Exception;
	
	//for starting the program
	public Pane totalInit() {
		//make the pane that serves as the foundation for the whole thing
		Pane boardPane = new Pane();
		boardPane.setMinSize(330, 270);
		boardPane.setMaxSize(330,270);
		ImageView boardPlayDisp = new ImageView(boardPlayer);
		ImageView boardFoeDisp = new ImageView(boardFoe);
		boardFoeDisp.setLayoutX(170);
		boardPane.getChildren().add(boardPlayDisp);
		boardPane.getChildren().add(boardFoeDisp);
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
						makeCommands(source);						
					}
				});
			}	
		}
		//add all those pictures to the pane
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				boardPane.getChildren().add(boardPlayerState[x][y]);
				boardPane.getChildren().add(boardFoeState[x][y]);
			}
		}
		//all of that construction is returned here
		return boardPane;
	}
	//method to reduce code reuse on startup
	public Pane startSetup() {
		Pane boardPane = new Pane();
		boardPane.setMinSize(330, 270);
		boardPane.setMaxSize(330,270);
		ImageView boardPlayDisp = new ImageView(boardPlayer);
		ImageView boardFoeDisp = new ImageView(boardFoe);
		boardFoeDisp.setLayoutX(170);
		boardPane.getChildren().add(boardPlayDisp);
		boardPane.getChildren().add(boardFoeDisp);
		return boardPane;
	}
	
	//method to update ImageViews based on state of Space arrays
	public void redrawBoards() {
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				boardPlayerState[x][y].setImage(boardPlayerState[x][y].spacePic);
				boardPlayerState[x][y].setLayoutX(x*20);
				boardPlayerState[x][y].setLayoutY(y*20);
				boardFoeState[x][y].setImage(boardFoeState[x][y].spacePic);
				boardFoeState[x][y].setLayoutX(170+(x*20));
				boardFoeState[x][y].setLayoutY(y*20);
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
	
	//abstract method called by target space listeners
	abstract void makeCommands(Space target);


}

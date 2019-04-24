package battleship;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Space extends ImageView {
	Image trans = new Image("https://i.imgur.com/hC4v7fo.png");
	Image missTemp = new Image("https://i.imgur.com/mKC6loN.png");
	Image hitTemp = new Image("https://i.imgur.com/lbpqlVw.png");
	Image shipTemp = new Image("https://i.imgur.com/ESGw5NR.png");
	Image hitShipTemp = new Image("https://i.imgur.com/naooKUA.png");
	Image spacePic;
	int x;
	int y;
	boolean isTarget;
	boolean hasShip;
	boolean isHit;
	Ship ship;
	public void addShip(Ship ship) {
		if(!hasShip) {
			this.ship = ship;
			this.hasShip = true;
			spacePic = shipTemp;
		}
	}
	public void targMissed() {
		spacePic = missTemp;
	}
	public void targHit() {
		spacePic = hitTemp;
	}
	public boolean hasShip() {
		return this.hasShip();
	}
	public String hit() {
		this.isHit = true;
		if(hasShip) {
			spacePic = hitShipTemp;
			return (x+"|"+y+"|hit");
		}
		else return (x+"|"+y+"|miss");
	}
	public Space(boolean target){
		hasShip = false;
		isHit = false;
		isTarget = target;
		if(target) {
			spacePic = trans;
		}
		else {
			spacePic = trans;
		}
	}
	public Space(Image image) {
		super(image);
	}
	public Space() {
		super();
	}
	public Space(boolean target, int x, int y) {
		hasShip = false;
		isHit = false;
		isTarget = target;
		this.x = x;
		this.y = y;		
		if(target) {
			spacePic = trans;
		}
		else {
			spacePic = trans;
		}
	}
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

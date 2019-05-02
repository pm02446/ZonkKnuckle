package battleship;

public class HitCommand implements Command {
	Main target;
	int xCord;
	int yCord;
	
	public HitCommand(Main target, int x, int y) {
		this.target = target;
		this.xCord = x;
		this.yCord = y;
	}
	
	public HitCommand(int x, int y) {
		this.xCord = x;
		this.yCord = y;
	}
	
	public String execute() {
		target.boardFoeState[xCord][yCord].targHit();
		target.redrawBoards();
		return "";
	}

}

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
	
	public void execute() {
		target.boardFoeState[xCord][yCord].targHit();
		target.redrawBoards();
	}

	@Override
	public void execute(Space[][] spaces) {
		// TODO Auto-generated method stub
	}

}

package battleship;

public class MissCommand implements Command{

	Main target;
	int xCord;
	int yCord;
	
	public MissCommand(Main target, int x, int y) {
		this.target = target;
		this.xCord = x;
		this.yCord = y;
	}
	
	public void execute() {		
	target.boardFoeState[xCord][yCord].targMissed();
	target.redrawBoards();
	}

	@Override
	public void execute(Space[][] spaces) {
		// TODO Auto-generated method stub
		
	}
}

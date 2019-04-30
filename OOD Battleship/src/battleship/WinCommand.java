package battleship;

public class WinCommand implements Command{
	Main target;
	int xCord;
	int yCord;
	
	public WinCommand(Main target, int x, int y) {
		this.target = target;
		this.xCord = x;
		this.yCord = y;
	}
	@Override
	public String execute() {
		target.boardFoeState[xCord][yCord].targHit();
		target.redrawBoards();
		target.setTurn(false);
		target.setLabel("YOU DID IT!");
		return "win";
	}

	@Override
	public void execute(Space[][] spaces) {
		// TODO Auto-generated method stub
		
	}

}

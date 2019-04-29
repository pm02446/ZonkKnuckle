package battleship;

public class AttackCommand implements Command{
	Main weirdoDemo;
	Main target;
	int xCord;
	int yCord;
	
	public AttackCommand(Main target, int x, int y) {
		this.target = target;
		this.xCord = x;
		this.yCord = y;
	}
	public String execute() {
		String result = target.boardPlayerState[xCord][yCord].hit();
		target.setTurn(true);
		target.redrawBoards();
		return result;
		
	}
	public void execute(Space[][] spaces) {
		String result = spaces[xCord][yCord].hit();
		weirdoDemo.redrawBoards();
		target.fact.makeCommand(weirdoDemo, result).execute();
	}

}

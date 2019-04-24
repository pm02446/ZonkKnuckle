package battleship;

public class AttackCommand implements Command{
	Main target;
	Main sender;
	int xCord;
	int yCord;
	
	public AttackCommand(Main sender, int x, int y) {
		this.sender = sender;
		this.xCord = x;
		this.yCord = y;
	}
	public String execute() {
		String result = sender.boardPlayerState[xCord][yCord].hit();
		sender.redrawBoards();
		sender.fact.makeCommand(sender, result).execute();
		return result;
		
	}
	public void execute(Space[][] spaces) {
		String result = spaces[xCord][yCord].hit();
		target.redrawBoards();
		sender.fact.makeCommand(target, result).execute();
	}

}

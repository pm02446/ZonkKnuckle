package battleship;

public class AttackCommand implements Command{
	Main target;
	Main sender;
	int xCord;
	int yCord;
	
	public AttackCommand(Main target, Main sender, int x, int y) {
		this.target = target;
		this.sender = sender;
		this.xCord = x;
		this.yCord = y;
	}
	
	public void execute() {
		String result = target.boardPlayerState[xCord][yCord].hit();
		target.redrawBoards();
		sender.fact.makeCommand(sender, target, result).execute();
	}

	@Override
	public void execute(Main target) {
		// TODO Auto-generated method stub
		//check the hit
		//make the hit
		//send a command to the target's socket
		
	}

}

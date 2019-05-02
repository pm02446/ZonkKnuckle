package battleship;

public class WinCommand implements Command{
	Main target;

	public WinCommand(Main target) {
		this.target = target;

	}
	
	//realize you've won, make it known
	@Override
	public String execute() {
		target.setTurn(false);
		target.setLabel("YOU WIN!");
		target.lastSelected.targHit();
		target.redrawBoards();
		target.dead = true;
		return "win";
	}

}

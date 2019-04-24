package battleship;

public interface Command {
	String execute();
	void execute(Space[][] spaces);

}

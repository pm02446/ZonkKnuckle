package battleship;

public interface Command {
	void execute();
	void execute(Space[][] spaces);
}

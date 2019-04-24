package battleship;

public interface Command {
	void execute();
	void execute(Main target);
}

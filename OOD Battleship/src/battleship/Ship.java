package battleship;

public interface Ship {
	
	int shipSize();
	Space[] getSpaces();
	Space originSpace();
	boolean isDestroyed();
}

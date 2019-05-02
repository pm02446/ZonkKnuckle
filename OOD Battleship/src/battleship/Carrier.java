package battleship;

public class Carrier implements Ship {
	private Space[] spaces;
	public Carrier(Space[] spaces){
		this.spaces = spaces;
		for(Space s:spaces) {
			s.addShip(this);
		}
	}

	@Override
	public int shipSize() {
		return 5;
	}

	@Override
	public Space[] getSpaces() {
		return spaces;
	}	

	@Override
	public boolean isDestroyed() {
		for(Space space : spaces){
			if(!space.isHit()){
				return false;
			}
		}
			return true;
	}
}

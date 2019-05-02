package battleship;

public class ReceivedCommandFactory {
	
	public Command makeCommand(Main from, String spec) {
		String[] split = spec.split("\\|");
		int x = Integer.parseInt(split[0]);
		int y = Integer.parseInt(split[1]);
		String type = split[2];
		if(type.equals("attack")) {
			return new AttackCommand(from, x, y);
		}
		else if(type.equals("miss")) {
			return new MissCommand(from, x, y);
		}
		else if(type.equals("hit")) {
			return new HitCommand(from, x, y);
		}
		else if(type.equals("win")) {
			return new WinCommand(from);
		}
		else return new AttackCommand(from, x, y);
	}
}



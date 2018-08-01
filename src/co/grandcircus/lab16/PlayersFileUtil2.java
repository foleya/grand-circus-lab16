package co.grandcircus.lab16;

// TODO: THIS ALL BROKEN

public class PlayersFileUtil2 extends AbstractFileUtil<Player> {

	public PlayersFileUtil2() {
		super("players.txt");
	}

	@Override
	protected Player convertLineToItem(String line) {
		String[] parts = line.split("\t");
		Player player = new Player();
		player.setName(parts[0]);
		player.setJersey(Integer.parseInt(parts[1]));
		return player;
	}

	@Override
	protected String convertItemToLine(Player item) {
		return String.format("%s\t%d", item.getName(), item.getJersey());
	}

	
}

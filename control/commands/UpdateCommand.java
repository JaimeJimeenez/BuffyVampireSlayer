package control.commands;

import logic.Game;

public class UpdateCommand extends Command{

	private static String name = "none";
	private static String shortcut = "n";
	private static String details = "[n]one | []";
	private static String help = "update";
	
	public UpdateCommand() {
		super (name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut) || commandWords[0].equals("")) return this;
		return null;
	}
}

package control.commands;

import logic.Game;

public class UpdateCommand extends Command{

	public UpdateCommand() {
		super ("none", "n", "[n]one | []", "update");
		UpdateCommand.name = super.name;
		UpdateCommand.shortcut = super.shortcut;
		UpdateCommand.details = "[n]one";
		UpdateCommand.help = "update";
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
	
	private static String name;
	private static String shortcut;
	private static String details;
	private static String help;
}

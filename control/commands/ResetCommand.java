package control.commands;

import logic.Game;

public class ResetCommand extends Command{

	public ResetCommand() {
		super("reset", "r", "[r]eset", "reset game");
		ResetCommand.name = super.name;
		ResetCommand.shortcut = super.shortcut;
		ResetCommand.details = "[r]eset";
		ResetCommand.help = "reset game";
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute (Game game) {
		game.reset();
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
	private static String name;
	private static String shortcut;
	private static String details;
	private static String help;
}

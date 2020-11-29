package control.commands;

import logic.Game;

public class ExitCommand extends Command{

	public ExitCommand() {
		super("exit", "e", "[e]xit", "exit game");
		ExitCommand.name = super.name;
		ExitCommand.shortcut = super.shortcut;
		ExitCommand.details = "[e]xit";
		ExitCommand.help = "exit game";
	}

	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		game.exitGame();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
	private static String name;
	private static String shortcut;
	private static String details;
	private static String help;
}

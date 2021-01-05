package control.commands;

import logic.Game;
import exceptions.CommandParseException;

public class ExitCommand extends Command{

	private static String name = "exit";
	private static String shortcut = "e";
	private static String details = "[e]xit";
	private static String help = "exit game";
	
	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		game.exitGame();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
}

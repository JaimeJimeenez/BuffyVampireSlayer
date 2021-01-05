package control.commands;

import logic.Game;
import exceptions.CommandParseException;

public class ResetCommand extends Command{

	private static String name = "reset";
	private static String shortcut = "r";
	private static String details = "[r]eset";
	private static String help = "reset game";
	
	public ResetCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute (Game game) {
		game.reset();
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

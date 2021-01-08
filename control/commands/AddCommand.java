package control.commands;

import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class AddCommand extends Command{

	private static String name = "add";
	private static String shortcut = "a";
	private static String details = "[a]dd <x> <y>";
	private static String help = "add a slayer in position x, y";
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try {
			if (game.addSlayer(x, y)) {
				game.update();
				return true;
			}
		}
		catch(CommandExecuteException exception) {
			System.out.println(exception.getMessage());
			throw new CommandExecuteException("add slayer", exception);
		}
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut)) {
			try {
				if (commandWords.length == 3) { 
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
				}
				else throw new CommandParseException("[ERROR]: Incorrect number of arguments for add command: " + details);
				return this;
			}
			catch (NumberFormatException nfe) {
				throw new CommandParseException("add slayer command, number expected: " + details, nfe);
			}
		}
		
		return null;
	}
	
	private int x;
	private int y;
}

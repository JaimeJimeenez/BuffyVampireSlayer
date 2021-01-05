package control.commands;

import logic.Game;
import exceptions.CommandParseException;

public class SerializeCommand extends Command {

	private static String name = "serialize";
	private static String shortcut = "z";
	private static String help = "Seriali[z]e";
	private static String details = "Serializes the board.";
	
	public SerializeCommand() {
		super(name, shortcut, help, details);
	}
	
	public boolean execute(Game game) {
		System.out.println(game.serialize());
		return false;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
}

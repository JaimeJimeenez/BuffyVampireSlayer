package control.commands;

import logic.Game;
import exceptions.CommandParseException;

public class HelpCommand extends Command{

	private static String name = "help";
	private static String shortcut = "h";
	private static String details = "[h]elp";
	private static String help = "show this help";
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		System.out.println("Available commands:");
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

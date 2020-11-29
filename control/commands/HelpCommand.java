package control.commands;

import logic.Game;

public class HelpCommand extends Command{

	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
		HelpCommand.name = super.name;
		HelpCommand.shortcut = super.shortcut;
		HelpCommand.details = "[h]elp";
		HelpCommand.help = "show this help";
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		System.out.println("Available commands:");
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}
	
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
	private static String name;
	private static String shortcut;
	private static String details;
	private static String help;
}

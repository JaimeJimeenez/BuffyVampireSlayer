package control.commands;

import exceptions.CommandParseException;

public class CommandGenerator {
	
	public static final String unknownCommandMsg = "Unknown command";
	
	public static Command parse(String[] parameters) throws CommandParseException {
		
		for (Command commands : availableCommands) {
			Command parsedCommand = commands.parse(parameters);
			if (parsedCommand != null) return parsedCommand;
		}
		
		throw new CommandParseException ("[ERROR]: " + unknownCommandMsg);
	}
	
	public static String commandHelp() {
		StringBuilder helpCommand = new StringBuilder();
		
		for (Command elem : availableCommands) {
			helpCommand.append(elem.helpText());
		}
		
		return helpCommand.toString();
	}
	
	private static Command[] availableCommands = {
			
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand(),
	};
}
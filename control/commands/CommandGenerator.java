package control.commands;

public class CommandGenerator {
	
	public static Command parse(String[] parameters) {
		
		for (Command elem : availableCommands) {
			Command parsedCommand = elem.parse(parameters);
			if (parsedCommand != null) return parsedCommand;
		}
		
		return null;
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
	};
}
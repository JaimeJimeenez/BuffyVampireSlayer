package control.commands;

import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class LightFlashCommand extends Command{
	
	private static String name = "light";
	private static String shortcut = "l";
	private static String details = "[l]ight";
	private static String help = "kills all the vampires";
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		
		try {
			if (game.eraseVampires()) {
				game.update();
				return true;
			}
			return false;
		}
		catch(CommandExecuteException exception) {
			System.out.println(exception.getMessage());
			throw new CommandExecuteException("light flash", exception);
		}
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

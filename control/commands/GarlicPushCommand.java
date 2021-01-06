package control.commands;

import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class GarlicPushCommand extends Command{

	private static String name = "garlic";
	private static String shortcut = "g";
	private static String details = "[g]arlic ";
	private static String help = "pushes back vampires";
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException { 
		
		try {
			if (game.pushVampires()) {
				game.update();
				return true;
			}
			
			return false;
		}
		catch(CommandExecuteException exception) {
			System.out.println(exception.getMessage());
			throw new CommandExecuteException("this garlic", exception);
		}
	}
	
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

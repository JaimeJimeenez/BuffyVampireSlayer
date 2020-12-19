package control.commands;

import logic.Game;

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
	public boolean execute(Game game) { 
		
		if (game.pushVampires()) {
			game.update();
			return true;
		}
		return false;
	}
	
	
	@Override
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
}

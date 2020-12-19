package control.commands;

import logic.Game;

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
	public boolean execute(Game game) {
		
		if (game.eraseVampires()) {
			game.update();
			return true;
		}
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
}

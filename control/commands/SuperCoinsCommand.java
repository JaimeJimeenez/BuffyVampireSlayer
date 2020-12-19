package control.commands;

import logic.Game;

public class SuperCoinsCommand extends Command {

	private static String name = "coins";
	private static String shortcut = "c";
	private static String details = "[c]oins";
	private static String help = "add 1000 coins";
	
	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		
		game.addCoins();
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) { return parseNoParamsCommand(commandWords); }
	
	
}

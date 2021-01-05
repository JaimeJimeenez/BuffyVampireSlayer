package control.commands;

import logic.Game;
import logic.gameObjects.Player;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;


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
	public boolean execute(Game game) throws NotEnoughCoinsException {
		
		if (game.canPlayerBuy(Player.VALUE_LIGHT)) {
			game.eraseVampires();
			return true;
		}
		
		throw new NotEnoughCoinsException("Not enough coins");
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

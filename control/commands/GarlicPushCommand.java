package control.commands;

import logic.Game;
import logic.gameObjects.Player;
import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
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
		
		if (game.canPlayerBuy(Player.VALUE_GARLIC)) {
			game.pushVampires();
			game.update();
			return true;
		}
		
		throw new NotEnoughCoinsException("Not enough coins");
	}
	
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException { return parseNoParamsCommand(commandWords); }
	
}

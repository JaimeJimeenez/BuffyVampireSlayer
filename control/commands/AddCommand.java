package control.commands;

import logic.Game;
import logic.gameObjects.Slayer;
import exceptions.UnvalidPositionException;
import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;

public class AddCommand extends Command{

	private static String name = "add";
	private static String shortcut = "a";
	private static String details = "[a]dd <x> <y>";
	private static String help = "add a slayer in position x, y";
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		if (game.isPositionValid(x, y) && x < game.getDim_X() - 1) {
			if (game.canPlayerBuy(Slayer.COST)) {
				game.addSlayer(x, y);
				game.update();
				return true;
			}
			else throw new NotEnoughCoinsException("Not enough coins");
		}
		else throw new UnvalidPositionException("Invalid position");
	}
	
	@Override
	public Command parse(String[] commandWords) {

		try {
			if (commandWords[0].equals(name) || commandWords[0].equals(shortcut)) {
				if (commandWords.length == 3) {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				}
			}
		}
		catch (NumberFormatException nfe) {
			System.out.println("[ERROR]: Unvalid argument for add slayer command, number expected: " + details);
		}
		
		return null;
	}
	
	private int x;
	private int y;
}

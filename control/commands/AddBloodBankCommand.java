package control.commands;

import logic.Game;
import exceptions.CommandParseException;
import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;

public class AddBloodBankCommand extends Command {

	private static String name = "bank";
	private static String shortcut = "b";
	private static String details = "[b]ank <x> <y> <z>";
	private static String help = "add a blood bank with cost z in position x, y";
	
	public AddBloodBankCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		if (game.isPositionValid(x, y) && x < game.getDim_X() - 1) {
			if (game.canPlayerBuy(imputCoins)) {
				game.addBloodBank(x, y, imputCoins);
				game.update();
				return true;
			}
			
			else throw new NotEnoughCoinsException("Not enough coins");
		}
		
		else throw new UnvalidPositionException("Invalid position");
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		
		if ((commandWords[0].equals(name) || (commandWords[0].equals(shortcut)) && commandWords.length == 4)) {
			x = Integer.parseInt(commandWords[1]);
			y = Integer.parseInt(commandWords[2]);
			imputCoins = Integer.parseInt(commandWords[3]);
			return this;
		}
		
		return null;
	}
	
	private int x;
	private int y;
	private int imputCoins;
}

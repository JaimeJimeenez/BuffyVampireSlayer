package control.commands;

import control.Controller;
import logic.Game;

public class AddCommand extends Command{

	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
		AddCommand.name = super.name;
		AddCommand.shortcut = super.shortcut;
		AddCommand.details = "[a]dd";
		AddCommand.help = "add slayer in positionx, y";
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) {
		
		if (pos_x < 0 || pos_y < 0) {
			System.out.println(Controller.invalidPositionMsg);
			return false;
		}
		
		if (game.addSlayer(pos_x, pos_y)) {
			game.update();
			return true;
		}
		
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) {

		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut)) {
			pos_x = Integer.parseInt(commandWords[1]);
			pos_y = Integer.parseInt(commandWords[2]);
			return this;
		}
		return null;
	}
	
	private static String name;
	private static String shortcut;
	private static String details;
	private static String help;
	private int pos_x;
	private int pos_y;
}

package control.commands;

import logic.Game;

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
	public boolean execute(Game game) {
		
		if (game.addSlayer(pos_x, pos_y)) {
			game.update();
			return true;
		}
		
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) {

		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut) && commandWords.length == 3) {
			pos_x = Integer.parseInt(commandWords[1]);
			pos_y = Integer.parseInt(commandWords[2]);
			return this;
		}
		return null;
	}
	
	private int pos_x;
	private int pos_y;
}

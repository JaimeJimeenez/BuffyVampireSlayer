package control.commands;

import logic.Game;
import logic.gameObjects.Dracula;
import logic.gameObjects.Vampire;
import exceptions.DraculaIsAliveException;
import exceptions.NoMoreVampiresException;
import exceptions.UnvalidPositionException;
import exceptions.CommandExecuteException;

public class AddVampireCommand extends Command {

	private static String name = "vampire";
	private static String shortcut = "v";
	private static String details = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	private static String help = "add a vampire in position x, y";
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}
	
	public String helpText() { return super.helpText(); }
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		if (Vampire.remaining != 0) {
			if (typeVampire == null) {
				if (game.isPositionValid(x, y)) {
					game.addVampire(x, y);
					return true;
				}
				else throw new UnvalidPositionException("Invalid Position");
			}
			
		if (typeVampire.equalsIgnoreCase("D")) {
			if (!Dracula.isAlive) {
				if (game.isPositionValid(x, y)) {						
					game.addDracula(x, y);
					return true;	
				}
				
				else throw new UnvalidPositionException("Invalid position");	
			}	
			
			else throw new DraculaIsAliveException();
		}
			
		if (typeVampire.equalsIgnoreCase("E")) {
			if (game.isPositionValid(x, y)) {
				game.addExplosiveVampire(x, y);					
				return true;	
			}
			
			else throw new UnvalidPositionException("Invalid position.");
			}
		throw new CommandExecuteException("[ERROR]: Unvalid type: " + details);
		}
		
		else throw new NoMoreVampiresException();
	}
	
	@Override
	public Command parse(String[] commandWords) {
		
		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut) && commandWords.length >= 3) {
			
			if (commandWords.length == 3) {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
			}
			else {
				typeVampire = commandWords[1].toLowerCase();
				x = Integer.parseInt(commandWords[2]);
				y = Integer.parseInt(commandWords[3]);
			}
			
			return this;
		}
		
		return null;
	}
	
	
	private int x;
	private int y;
	private String typeVampire;
}

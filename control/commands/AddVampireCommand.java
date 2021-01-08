package control.commands;

import logic.Game;
import logic.gameObjects.Vampire;
import exceptions.CommandParseException;
import exceptions.CommandExecuteException;
import exceptions.NoMoreVampiresException;

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
		
		try {
			if (Vampire.remaining != 0) {
				if (typeVampire == null) 
					if (game.addVampireByUser(x, y)) return true;
					
				if (typeVampire.equalsIgnoreCase("D")) 
					if (game.addDraculaByUser(x, y)) return true;
					
				if (typeVampire.equalsIgnoreCase("E")) {
					if (game.addExplosiveByUser(x, y)) return true;
				}	
				
				return false;
			}
			throw new NoMoreVampiresException();
		}
		catch(CommandExecuteException exception) {
			System.out.println(exception.getMessage());
			throw new CommandExecuteException ("add this vampire ", exception);
		}
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut)) {
			try {
				if (commandWords.length == 3) {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
				}
				if (commandWords.length == 4){
					if (commandWords[1].length() != 1) throw new CommandParseException("[ERROR]: Unvalid type: " + details);
					typeVampire = commandWords[1].toLowerCase();
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[3]);
				}
				else throw new CommandParseException("add command: " + details);
				return this;
			}
			catch(CommandParseException typeException) {
				System.out.println(typeException.getMessage());
			}
			catch(NumberFormatException nfe) {
				throw new CommandParseException("add vampire", nfe);
				
			}
			
		}
		
		return null;
	}
	
	
	private int x;
	private int y;
	private String typeVampire;
}

package control.commands;

import logic.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.CommandParseException;

public class SaveCommand extends Command{

	private static String name = "save";
	private static String shortcut = "s";
	private static String help = "[S]ave <filename>";
	private static String details = "Save the state of the game to a file.";
	
	public SaveCommand() {
		super(name, shortcut, help, details);
	}
	
	public boolean execute(Game game) {
		
		try (BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName + ".dat"))) {
			outFile.write(game.serialize());
			outFile.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		System.out.println("Game succesfully saved in file < " + fileName + " >.dat");
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].equals(name) || commandWords[0].equals(shortcut) && commandWords.length == 2) {
			fileName = commandWords[1];
			return this;
		}
		
		throw new CommandParseException("Unknown command");
	}
	
	private String fileName = " ";
}

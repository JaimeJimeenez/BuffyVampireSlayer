package control;

import java.util.Scanner;
import exceptions.GameException;
import logic.Game;
import control.commands.*;

public class Controller {

	public final String prompt = "Command > ";
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("[ERROR]: Invalid position");

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() { System.out.println(game); }
    
    public void run() {
    	boolean refreshDisplay = true;
    	
    	while(!game.isFinished()) {
    		
    		if (refreshDisplay) printGame();
    		refreshDisplay = false;
    		System.out.println(prompt);
    		String s = scanner.nextLine();
    		String[] parameters = s.toLowerCase().trim().split(" ");
    		System.out.println("[DEBUG] Executing: " + s);
    		
    		try {
    			Command command = CommandGenerator.parse(parameters);
    			refreshDisplay = command.execute(game);
    		}
    		catch(GameException exception) {
    			System.out.println(exception.getMessage() + "\n\n");
    		}
    	}
    	
    	if (refreshDisplay) printGame();
		System.out.println ("[GAME OVER] " + game.getWinnerMessage());
    }

}

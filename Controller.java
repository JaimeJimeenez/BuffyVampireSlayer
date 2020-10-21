package control;

import java.util.Scanner;
import logic.Game;

public class Controller {

	
	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() {
		// TODO fill your code
    	while(!game.checkEnd()) {
    		
    		System.out.print(prompt);
        	String command = scanner.nextLine();
        	switch(command) {
        	
        	case "add": System.out.println("Adding slayer.");
        	break;
        	
        	case "help": System.out.println(helpMsg);
        	break;
        	
        	case "exit": System.out.close();
        	break;
        	
        	case "none": break;
        	
        	default: System.out.println(invalidCommandMsg);
        	
        	}
        	game.update();
        	printGame();
    	}
    	
    }
    
    private Game game;
    private Scanner scanner;
    
}



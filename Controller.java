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
    	
    	printGame();
    	
		while(!game.checkEnd()) {

	    	String command;
	    	
	    	System.out.print(prompt);
	    	command = scanner.nextLine();
	    	String[] dim = command.split(" ");
	    	String order = dim[0];
	    	
	    	switch(order) {
	    	
	    	case "add": 
	    		int dim_x = Integer.parseUnsignedInt(dim[1]);
	    		int dim_y = Integer.parseUnsignedInt(dim[2]);
	    		
	    		if (dim_x > game.setDim_x(game.getLevel()) - 1 || dim_y > game.setDim_y(game.getLevel())) System.out.println(invalidPositionMsg);
	    		else game.addSlayer(dim_x, dim_y);
	    		break;
	    		
	    	case "help":
	    		System.out.println(helpMsg);
	    		break;
	    	
	    	case "reset": 
	    		break;
	    		
	    	case "exit": 
	    		System.out.println("Game Ended");
	    		break;
	    	
	    	case "none": break;
	    	
	    	default: System.out.println(invalidCommandMsg); //OR unknownCommandMsg
	    	
	    	}
	    	
	    	game.update();
	    	printGame();
		}
    	
    }
  
    private Game game;
    private Scanner scanner;
    
}
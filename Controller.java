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
    	
    	
		while(!game.getEnd()) {

	    	String command;
	    	int aux = game.getCycles();
	    	
	    	printGame();
	    	System.out.print(prompt);
	    	command = scanner.nextLine();
	    	String[] dim = command.split(" ");
	    	String order = dim[0];
	    	
	    	switch(order) {
	    	
	    	case "add": 
	    		int dim_x = Integer.parseUnsignedInt(dim[1]);
	    		int dim_y = Integer.parseUnsignedInt(dim[2]);
	    		
	    		if (dim_x > game.setDim_x() - 1  || dim_x <= 0 || dim_y > game.setDim_y() || dim_y <= 0) System.out.println(invalidPositionMsg);
	    		else {
	    			game.addSlayer(dim_x, dim_y);
	    			game.updateCycles();
	    		}
	    		break;
	    		
	    	case "help":
	    		System.out.println(helpMsg);
	    		break;
	    	
	    	case "reset": Controller newController = new Controller (new Game(game.getSeed(), game.getLevel()), new Scanner(System.in));
	    	newController.run();
	    		break;
	    		
	    	case "exit": 
	    		System.out.println("Game Ended");
	    		game.setEnd(true);
	    		break;
	    	
	    	case "none": game.updateCycles();
	    		break;
	    	
	    	default: 
	    		System.out.println(invalidCommandMsg); 
	    		//OR unknownCommandMsg
	    	
	    	}
	    	if (aux != game.getCycles()) game.update();
		}
    	
    }
  
    private Game game;
    private Scanner scanner;
    
}
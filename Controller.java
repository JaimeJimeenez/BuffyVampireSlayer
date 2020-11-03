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

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() { System.out.println(game); }
    
    public void run() {
    	
    	printGame();
    	
    	while (!game.getEnd()) {
    		
    		String command;
    		String order;
    		int aux = game.getCycles();
    		
    		System.out.print(prompt);
    		
    		command = scanner.nextLine();
    		String dim[] = command.split(" ");
    		order = dim[0];
    		
    		System.out.println("[DEBUG] Executing: " + order);
    		
    		switch (order) {
    		
    		case "Add":
    		case "a":
    		case "add":
    			int dim_x = Integer.parseUnsignedInt(dim[1]);
    			int dim_y = Integer.parseUnsignedInt(dim[2]);
    			
    			if (dim_x >= game.getLevel().getDim_x() - 1 || dim_x < 0 || dim_y > game.getLevel().getDim_y() - 1 || dim_y < 0) System.out.println("[ERROR]: " + invalidPositionMsg);
    			else if (game.getPlayer().getCoins() >= 50) {
    				game.addSlayer(dim_x, dim_y);
    				game.updateCycles();
    			}
    			else System.out.println("[ERROR]: Not enough coins");
    			
    			break;
    			
    		case "h":
    		case "help": 
    			System.out.println(helpMsg);
    			break;
    		
    		case "r":
    		case "reset":
    			Controller newController = new Controller(new Game(Game.getSeed(), game.getLevel()), new Scanner(System.in));
    			newController.run();
    			break;
    			
    		case "e":
    		case "exit":
    			System.out.println("Game Ended.");
    			game.setEnd(true);
    			break;
    		
    		case "":
    		case "n":
    		case "none": game.updateCycles();
    		break;
    		
    		default: System.out.println("[ERROR]: " + unknownCommandMsg);
    		
    		}
    		
    		if (aux != game.getCycles()) {
    			game.update();
    			printGame();
    		}
    		
    	}
    }

}

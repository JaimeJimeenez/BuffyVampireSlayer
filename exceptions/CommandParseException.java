package exceptions;

public class CommandParseException extends GameException {

	private static final long serialVersionUID = 1L;
	
	public CommandParseException() {
		super();
	}
	
	public CommandParseException(String message) {
		super("[ERROR]: Unvalid argument for " + message + "command, number expected:");
	}
	
	public CommandParseException(Throwable cause) {
		super(cause);
	}
	
	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}
}

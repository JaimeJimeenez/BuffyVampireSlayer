package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {

	private static final long serialVersionUID = 1L;
	private static final String notEnoughCoinsMsg = "Not enough coins";

	public NotEnoughCoinsException() {
		super();
	}
	
	public NotEnoughCoinsException(String message) {
		super("[ERROR]: " + message + notEnoughCoinsMsg);
	}
	
	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

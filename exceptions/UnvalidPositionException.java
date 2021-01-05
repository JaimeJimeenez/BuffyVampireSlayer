package exceptions;

public class UnvalidPositionException extends CommandExecuteException {
	
	private static final long serialVersionUID = 1L;

	public UnvalidPositionException() {
		super();
	}
	
	public UnvalidPositionException(String message) {
		super(message);
	}
	
	public UnvalidPositionException(Throwable cause) {
		super(cause);
	}
	
	public UnvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

package exception;

/**
 * this class represent an abort exception
 */
public class AbortException extends Exception {

	/**
	 * ctor
	 * 
	 * @param message a given string
	 * @param cause a given throwable
	 */
	public AbortException(String message, Throwable cause) {
		
		super(message, cause);
	}

	/**
	 * ctor
	 * 
	 * @param message a given string
	 */
	public AbortException(String message) {
		
		super(message);
	}

	/**
	 * ctor
	 * 
	 * @param cause a given throwable
	 */
	public AbortException(Throwable cause) {
		
		super(cause);
	}

	/**
	 * ctor
	 */
	public AbortException(){
		
		super();
	}
	
	/**
	 * the serial Version UID
	 */
	private static final long serialVersionUID = 1L;

}

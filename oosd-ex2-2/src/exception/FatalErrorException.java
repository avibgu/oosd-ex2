package exception;

/**
 * this class represent a Fatal Exception
 */
public class FatalErrorException extends Exception {

	/**
	 * the serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ctor
	 */
	public FatalErrorException() {
		
		super();
	}
	
	/**
	 * ctor
	 * 
	 * @param arg0 a given string
	 */
	public FatalErrorException(String arg0) {
		
		super(arg0);
	}

	/**
	 * ctor
	 * 
	 * @param arg0 a given throwable
	 */
	public FatalErrorException(Throwable arg0) {
		
		super(arg0);
	}

	/**
	 * ctor
	 * 
	 * @param arg0 a given string
	 * @param arg1 a given throwable
	 */
	public FatalErrorException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
	}
}

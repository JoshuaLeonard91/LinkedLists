package source;

/**
 * This class file is used for interval delete to help clarify error checking
 * 
 * @author Joshua Leonard
 * @version 10/15/2022
 */
@SuppressWarnings("serial")
public class IntervalException extends Exception {
	public IntervalException(String e) {
		super(e);
	}

	public IntervalException() {

	}
}

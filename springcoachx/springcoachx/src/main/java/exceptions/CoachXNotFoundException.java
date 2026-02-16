package exceptions;

public class CoachXNotFoundException extends RuntimeException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public CoachXNotFoundException() {
        super();
    }
      

    public CoachXNotFoundException(String message) {
        super(message);
    }
 
    public CoachXNotFoundException(long id) {
        super("CoachX not found: " + id);
    }
}


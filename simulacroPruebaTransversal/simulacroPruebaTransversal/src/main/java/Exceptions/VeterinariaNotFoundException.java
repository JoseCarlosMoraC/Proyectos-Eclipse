package Exceptions;

public class VeterinariaNotFoundException extends RuntimeException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public VeterinariaNotFoundException() {
        super();
    }
      

    public VeterinariaNotFoundException(String message) {
        super(message);
    }
 
    public VeterinariaNotFoundException(long id) {
        super("Product not found: " + id);
    }
}


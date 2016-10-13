package pfpt;

public class Except extends Exception {

	// I do not know why I need this
	private static final long serialVersionUID = 1L;
	
	// constructor
	Except(){}
	public Except(String message){
	            super(message);
	}
	public Except(Throwable cause){
        super(cause);
    }
	public Except(String message, Throwable cause){
        super(message, cause);
    }

}


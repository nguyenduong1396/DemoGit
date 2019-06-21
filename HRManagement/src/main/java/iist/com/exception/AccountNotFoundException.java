package iist.com.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	AccountNotFoundException(String username){
		super("Could not find Account with username: " + username);
	}

	
}

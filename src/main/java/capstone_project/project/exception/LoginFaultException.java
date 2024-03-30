package capstone_project.project.exception;

public class LoginFaultException extends RuntimeException{

    // Costruttore della classe LoginFaultException.
    public LoginFaultException(String message){
        // Richiama il costruttore della classe genitore (superclasse), che è Throwable, passando il messaggio dell'eccezione.
        super(message);
    }
}

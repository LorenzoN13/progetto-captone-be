package capstone_project.project.exception;

public class UnAuthorizedException extends RuntimeException{
// Costruttore della classe UnAuthorizedException.

    public UnAuthorizedException(String msg){
        // Chiama il costruttore della classe genitore (superclasse), che è Throwable, passando il messaggio dell'eccezione.
        super(msg);
    }
}

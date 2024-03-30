package capstone_project.project.exception;

public class NotFoundException extends RuntimeException{
    // Costruttore della classe NotFoundException.
    public NotFoundException(String msg){
        // Chiama il costruttore della classe genitore (superclasse), che è Throwable, passando il messaggio dell'eccezione.
        super(msg);
    }}

package capstone_project.project.exception;

public class BadRequestException extends RuntimeException{
    // Costruttore che accetta un messaggio personalizzato
    public BadRequestException(String msg) {
        // Richiama il costruttore della classe Exception con il messaggio
        super(msg);
    }
}

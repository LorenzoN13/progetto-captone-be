package capstone_project.project.exception;

public class BadRequestExceptionHandler extends Exception{
    // Costruttore che accetta un messaggio personalizzato
    public BadRequestExceptionHandler(String message) {
        // Richiama il costruttore della classe Exception con il messaggio
        super(message);
    }
}

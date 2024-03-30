package capstone_project.project.exception;

public class AlreadyAdminException extends Exception{
    // Costruttore che accetta un messaggio personalizzato
    public AlreadyAdminException(String message) {
        // Richiama il costruttore della classe Exception con il messaggio
        super(message);
    }
}

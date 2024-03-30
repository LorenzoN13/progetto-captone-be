package capstone_project.project.responses;

import lombok.Data;

import java.time.LocalDateTime;
// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class ErrorResponse {
    // Messaggio di errore
    private String ErrorMessage;

    // Data e ora dell'errore
    private LocalDateTime ErrorDate;

    // Costruttore che imposta il messaggio di errore e registra la data e l'ora dell'errore
    public ErrorResponse(String message){
        ErrorMessage = message;
        ErrorDate = LocalDateTime.now();
    }
}

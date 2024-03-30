package capstone_project.project.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// Classe per rappresentare una risposta di errore.
public class ErrorResponse {
    // Variabile per memorizzare lo status HTTP della risposta.
    private int status;
    // Variabile per memorizzare il messaggio di errore o l'oggetto associato all'errore.
    private Object message;
    // Variabile per memorizzare la data e l'ora in cui è stata creata la risposta di errore.
    private LocalDateTime dataResponse;

    // Costruttore della classe ErrorResponse.
    public ErrorResponse(int status, Object message){
        // Imposta lo status HTTP e il messaggio di errore.
        this.status = status;
        this.message = message;
        // Ottiene la data e l'ora correnti.
        dataResponse = LocalDateTime.now();
    }
}

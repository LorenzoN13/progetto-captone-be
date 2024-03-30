package capstone_project.project.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class DefaultResponse {
    // Messaggio generico
    private String message;

    // Oggetto associato alla risposta
    private Object obj;

    // Data e ora della risposta
    private LocalDateTime date;

    // Costruttore che imposta il messaggio, l'oggetto e registra la data e l'ora della risposta
    public DefaultResponse(String message, Object obj) {
        this.message = message;
        this.obj = obj;
        date = LocalDateTime.now();
    }

    // Costruttore che imposta solo il messaggio e registra la data e l'ora della risposta
    public DefaultResponse(String message) {
        this.message = message;
        date = LocalDateTime.now();
    }

    // Metodo statico per creare una risposta completa con un messaggio, un oggetto e uno stato HTTP specificato
    public static ResponseEntity<DefaultResponse> full(String message, Object obj, HttpStatus httpStatus){
        // Crea un'istanza di DefaultResponse con messaggio e oggetto specificati
        DefaultResponse defaultResponse = new DefaultResponse(message, obj);

        // Restituisce un'entità ResponseEntity con l'oggetto DefaultResponse e lo stato HTTP specificato
        return new ResponseEntity<>(defaultResponse, httpStatus);
    }

    // Metodo statico per creare una risposta senza un messaggio con un oggetto e uno stato HTTP specificato
    public static ResponseEntity<DefaultResponse> noMessage(Object obj, HttpStatus httpStatus){
        // Crea un'istanza di DefaultResponse con lo stato HTTP come messaggio e l'oggetto specificato
        DefaultResponse defaultResponse = new DefaultResponse(httpStatus.toString(), obj);

        // Restituisce un'entità ResponseEntity con l'oggetto DefaultResponse e lo stato HTTP specificato
        return new ResponseEntity<>(defaultResponse, httpStatus);
    }

    // Metodo statico per creare una risposta senza un oggetto con un messaggio e uno stato HTTP specificato
    public static ResponseEntity<DefaultResponse> noObject(String message, HttpStatus httpStatus){
        // Crea un'istanza di DefaultResponse con il messaggio specificato e senza oggetto
        DefaultResponse defaultResponse = new DefaultResponse(message);

        // Restituisce un'entità ResponseEntity con l'oggetto DefaultResponse e lo stato HTTP specificato
        return new ResponseEntity<>(defaultResponse, httpStatus);
    }
}

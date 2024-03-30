package capstone_project.project.request;

import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class SendMessageRequest {

    // Destinatario del messaggio
    private String destinatario;

    // Oggetto del messaggio
    private String oggetto;

    // Testo del messaggio
    private String messaggio;
}

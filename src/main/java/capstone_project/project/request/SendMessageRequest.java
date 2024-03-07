package capstone_project.project.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String destinatario;
    private String oggetto;
    private String messaggio;
}

package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class IndirizzoRequest {
    @NotBlank(message = "via obbligatoria")
    private String via;
    @NotBlank(message = "civico obbligatorio")
    private String civico;
    @NotBlank(message = "comune obbligatorio")
    private String comune;
    @NotBlank(message = "cap obbligatorio")
    private String cap;

    private Integer idUtente;

}

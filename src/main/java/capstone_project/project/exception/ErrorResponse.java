package capstone_project.project.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int status;
    private Object message;
    private LocalDateTime dataResponse;

    public ErrorResponse(int status, Object message){
        this.status = status;
        this.message = message;
        dataResponse = LocalDateTime.now();
    }
}

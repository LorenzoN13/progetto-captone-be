package capstone_project.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    // Gestisce le eccezioni di tipo BadRequestException.
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestExceptionHandler(BadRequestException e){
        // Restituisce una risposta con lo status code BAD_REQUEST e il messaggio dell'eccezione.
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    // Gestisce le eccezioni di tipo NotFoundException.
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException e){
        // Restituisce una risposta con lo status code NOT_FOUND e il messaggio dell'eccezione.
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    // Gestisce tutte le altre eccezioni che non sono state gestite esplicitamente.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e){
        // Restituisce una risposta con lo status code INTERNAL_SERVER_ERROR e il tipo di eccezione.
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getClass());
    }

    // Gestisce le eccezioni di tipo AlreadyAdminException.
    @ExceptionHandler(AlreadyAdminException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse alreadyAdminException(AlreadyAdminException e){
        // Restituisce una risposta con lo status code FORBIDDEN e il messaggio dell'eccezione.
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}

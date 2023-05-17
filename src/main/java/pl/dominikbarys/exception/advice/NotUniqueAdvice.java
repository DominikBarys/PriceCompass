package pl.dominikbarys.exception.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dominikbarys.exception.NotUniqueException;

@ControllerAdvice
public class NotUniqueAdvice {

    @ResponseBody
    @ExceptionHandler(NotUniqueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String notUniqueHandler(NotUniqueException ex){
        return ex.getMessage();
    }

}

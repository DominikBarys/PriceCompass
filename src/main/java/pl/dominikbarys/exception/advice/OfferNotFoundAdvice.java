package pl.dominikbarys.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dominikbarys.exception.OfferNotFoundException;

@ControllerAdvice
public class OfferNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String offerNotFoundAdvice(OfferNotFoundException ex){
        return ex.getMessage();
    }

}

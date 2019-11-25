package it.dinokrodino.restApi.controllers.v1.exceptions;

import it.dinokrodino.restApi.service.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        HttpHeaders headers = new HttpHeaders();
        if (exception instanceof ResourceNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ResourceNotFoundException rnfe = (ResourceNotFoundException) exception;

            return handleResourceNotFoundException(rnfe, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(exception, null, headers, status, request);
        }
    }

    /** ResourseNotFoundException. */
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exeption, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Resource Not Found",new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}

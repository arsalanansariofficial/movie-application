package demo.ServiceLayer.Exception.Handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(ConstraintViolationException exception) {
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", exception.getLocalizedMessage());
        errorResponse.put("cause", "duplicate entry");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

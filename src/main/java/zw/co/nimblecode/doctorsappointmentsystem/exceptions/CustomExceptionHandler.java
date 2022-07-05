package zw.co.nimblecode.doctorsappointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(
            value = {
                    ResourceNotFoundException.class,
                    ResourceAlreadyExistsException.class
            })
    public ResponseEntity<CustomException> handleConflicts(RuntimeException e) {
        return new ResponseEntity<>(new CustomException(e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }
}

package zw.co.nimblecode.doctorsappointmentsystem.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class CustomException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String timeStamp;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = ZonedDateTime.now().toString();
    }
}

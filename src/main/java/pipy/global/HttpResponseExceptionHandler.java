package pipy.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpResponseExceptionHandler {

    @ExceptionHandler(HttpResponseException.class)
    public ResponseEntity<ApiErrorResponse.ApiErrorResult> handle(final HttpResponseException exception) {
        final HttpStatus status = exception.getStatus();
        final String message = exception.getMessage();
        return ApiErrorResponse.error(status, message);
    }
}

package pipy.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpResponseException extends PipyException {

    private final HttpStatus status;
    private final String message;

    private HttpResponseException(final HttpStatus status, final String message) {
        super(message);
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message cannot be null or blank");
        }
        this.status = status;
        this.message = message;
    }

    public static HttpResponseException notFound(final String message) {
        return new HttpResponseException(HttpStatus.NOT_FOUND, message);
    }

    public static HttpResponseException badRequest(final String message) {
        return new HttpResponseException(HttpStatus.BAD_REQUEST, message);
    }

    public static HttpResponseException internalServerError(final String message) {
        return new HttpResponseException(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static HttpResponseException exception(final String message) {
        return new HttpResponseException(null, message);
    }
}

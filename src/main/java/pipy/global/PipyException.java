package pipy.global;

import org.springframework.http.HttpStatus;

public class PipyException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    private PipyException(final HttpStatus status, final String message) {
        super(message);
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message cannot be null or blank");
        }
        this.status = status;
        this.message = message;
    }

    public static PipyException notFoundException(final String message) {
        return new PipyException(HttpStatus.NOT_FOUND, message);
    }

    public static PipyException badRequestException(final String message) {
        return new PipyException(HttpStatus.BAD_REQUEST, message);
    }

    public static PipyException internalServerErrorException(final String message) {
        return new PipyException(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static PipyException exception(final String message) {
        return new PipyException(null, message);
    }
}

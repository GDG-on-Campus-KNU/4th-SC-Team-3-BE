package pipy.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiErrorResponse {

    public static ResponseEntity<ApiErrorResult> error(
        final HttpStatus status,
        final String... errors
    ) {
        final ApiErrorResult result = new ApiErrorResult(status.value(), errors);
        return ResponseEntity.status(status).body(result);
    }

    @Getter
    @JsonInclude(Include.NON_NULL)
    public static class ApiErrorResult {

        private final int status;
        private final String[] errors;

        private ApiErrorResult(final int status, final String... messages) {
            this.status = status;
            this.errors = messages;
        }
    }
}

package pipy.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiSuccessResponse {

    private static <T> ApiSuccessResult<T> body(final HttpStatus status, final T data) {
        return new ApiSuccessResult<>(status.value(), data);
    }

    private static <T> ApiSuccessResult<T> body(final HttpStatus status) {
        return new ApiSuccessResult<>(status.value());
    }

    public static <T> ResponseEntity<ApiSuccessResult<T>> success(final HttpStatus status, final T data) {
        return ResponseEntity.status(status).body(ApiSuccessResponse.body(status, data));
    }

    public static ResponseEntity<ApiSuccessResult<Void>> success(final HttpStatus status) {
        return ResponseEntity.status(status).body(ApiSuccessResponse.body(status));
    }

    @Getter
    public static class ApiSuccessResult<T> {

        @Schema(description = "HTTP 상태 코드")
        private final int status;

        @Schema(
            description = "응답 데이터",
            types = { "object", "null" }
        )
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private final T data;

        private ApiSuccessResult(final int status, final T data) {
            this.status = status;
            this.data = data;
        }

        private ApiSuccessResult(final int status) {
            this.status = status;
            this.data = null;
        }
    }
}

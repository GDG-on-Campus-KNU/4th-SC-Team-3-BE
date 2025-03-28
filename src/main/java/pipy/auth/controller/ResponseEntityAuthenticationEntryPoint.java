package pipy.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pipy.global.ApiErrorResponse;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ResponseEntityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException authException
    ) throws IOException {
        final HttpStatus status = HttpStatus.UNAUTHORIZED;
        final String message = "로그인이 필요합니다.";

        final ResponseEntity<ApiErrorResponse.ApiErrorResult> responseEntity = ApiErrorResponse.error(status, message);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final HttpHeaders headers = responseEntity.getHeaders();
        headers.forEach((key, value) -> {
            String headerValue = String.join(",", value);
            response.addHeader(key, headerValue);
        });

        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)) {
            objectMapper.writeValue(writer, responseEntity.getBody());
        }
    }
}

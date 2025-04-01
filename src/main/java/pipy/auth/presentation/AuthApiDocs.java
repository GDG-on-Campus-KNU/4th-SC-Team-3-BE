package pipy.auth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Tag(name = "인증")
@RequestMapping("/auth")
public interface AuthApiDocs {

    @Operation(summary = "로그인", description = "로그인 페이지로 리다이렉션합니다.")
    @Parameters({
        @Parameter(
            name = "registrationId",
            description = "OAuth2 클라이언트 ID",
            required = true,
            example = "google"
        ),
        @Parameter(
            name = "redirect",
            description = "로그인 성공 시 리다이렉션할 URL",
            required = true,
            example = "https://pipy.me"
        )
    })
    @ApiResponse(
        responseCode = "302",
        description = "로그인 성공 시 `redirect`으로 리다이렉션",
        content = @Content(schema = @Schema(hidden = true))
    )
    RedirectView login(String registrationId, String redirect);
}

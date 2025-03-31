package pipy.auth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Tag(name = "인증")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Operation(summary = "로그인", description = "구글 로그인 페이지로 리다이렉션합니다.")
    @ApiResponse(
        responseCode = "302",
        description = "로그인 성공 시 '/'으로 리다이렉션",
        content = @Content(schema = @Schema(hidden = true))
    )
    @GetMapping("/login")
    public RedirectView login() {
        final String url = "/oauth2/authorization/google";
        return new RedirectView(url);
    }
}

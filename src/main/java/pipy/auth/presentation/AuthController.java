package pipy.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static pipy.auth.presentation.AuthorizationRequestRedirectResolver.REDIRECT_PARAM_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthApiDocs {

    @GetMapping("/login/{registrationId}")
    public RedirectView login(
        @PathVariable String registrationId,
        @RequestParam(REDIRECT_PARAM_KEY) String redirect
    ) {
        final String url = String.format(
            "/oauth2/authorization/%s?%s=%s",
            registrationId,
            REDIRECT_PARAM_KEY,
            redirect
        );
        return new RedirectView(url);
    }
}

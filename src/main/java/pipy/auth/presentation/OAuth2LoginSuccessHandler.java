package pipy.auth.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static pipy.auth.presentation.AuthorizationRequestRedirectResolver.REDIRECT_PARAM_KEY;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final String STATE_PARAM_KEY = "state";

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException {
        String state = request.getParameter(STATE_PARAM_KEY);
        Map<String, String> stateParams = parseState(state);
        String redirect = stateParams.getOrDefault(REDIRECT_PARAM_KEY, "/");
        response.sendRedirect(redirect);
    }

    private Map<String, String> parseState(final String state) {
        Map<String, String> params = new HashMap<>();
        UriComponentsBuilder.fromUriString("?" + state).build()
            .getQueryParams()
            .forEach((key, value) -> params.put(key, value.getFirst()));
        return params;
    }
}

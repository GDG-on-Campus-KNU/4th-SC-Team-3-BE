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
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication
    ) throws IOException {
        final String state = request.getParameter(STATE_PARAM_KEY);
        final Map<String, String> stateParams = parseState(state);
        final String redirect = stateParams.get(REDIRECT_PARAM_KEY);
        if (redirect == null) return ;
        response.sendRedirect(redirect);
    }

    private Map<String, String> parseState(final String state) {
        final Map<String, String> params = new HashMap<>();
        UriComponentsBuilder.fromUriString("?" + state).build()
            .getQueryParams()
            .forEach((key, value) -> params.put(key, value.getFirst()));
        return params;
    }
}

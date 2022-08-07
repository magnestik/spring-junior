package ru.iteco.teachbase.springjunior.gateway.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LogoutHandler extends SecurityContextLogoutHandler {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public LogoutHandler(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        super.logout(request, response, authentication);
        ClientRegistration auth0 = getClientRegistration();
        String issuer = (String) auth0.getProviderDetails().getConfigurationMetadata().get("issuer");
        String clientId = auth0.getClientId();
        String returnTo = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();
        String logoutUrl = UriComponentsBuilder.fromHttpUrl(issuer)
            .path("v2")
            .path("logout")
            .queryParam("client_id", clientId)
            .queryParam("returnTo", returnTo)
            .build(true)
            .toUriString();
        try {
            response.sendRedirect(logoutUrl);
        } catch (IOException e) {
            log.error("Error redirection to logout");
        }
    }

    private ClientRegistration getClientRegistration() {
        return clientRegistrationRepository.findByRegistrationId("auth0");
    }
}

package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import by.aipos.aipos_lab2.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationController {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    private static final String authorizationRequestBaseUri = "oauth2/authorize-client";
    private Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    @Autowired
    private GoogleAuthService googleAuthService;
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(), authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login";
    }

    @GetMapping(value = "/loginSuccess")
    public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
        Map userAttributes = googleAuthService.getUserAttributes(authentication);
        Client user = googleAuthService.loadClient(userAttributes);
        clientService.addClient(user);
        return "loginSuccess";
    }
}

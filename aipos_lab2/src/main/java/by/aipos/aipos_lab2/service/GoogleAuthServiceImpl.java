package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    private SecureRandom RND = new SecureRandom();

    @Override
    public Map getUserAttributes(OAuth2AuthenticationToken authentication){
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());

            HttpEntity<String> entity = new HttpEntity<String>("", headers);

            ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            return userAttributes;
        }
        return null;
    }

    @Override
    public Client loadClient(Map userAttributes){
        String email = userAttributes.get("email").toString();
        String username = getUsernameFromEmail(email);
        Client client = new Client();
        client.setName(username);
        client.setPassword(generateRandomPassword());
        return client;
    }

    @Override
    public String getUsernameFromEmail(String email){
        String userName;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@'){
                userName = email.substring(0, i);
                System.out.println(userName);
                return userName;
            }
        }
        return null;
    }

    @Override
    public String generateRandomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678";
        StringBuilder sb = new StringBuilder(Math.max(8, 16));
        for (int i = 0; i < 8; i++) {
            int len = alphabet.length();
            int random = RND.nextInt(len);
            char c = alphabet.charAt(random);
            sb.append(c);
        }
        return sb.toString();
    }
}

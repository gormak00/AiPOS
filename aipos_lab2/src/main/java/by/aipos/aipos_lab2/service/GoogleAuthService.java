
package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Client;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Map;

public interface GoogleAuthService {

    /**
     * Создание map для текущего пользователя из google
     *
     * @param authentication данные полученные из google авторизации
     * @return userAttributes - данные текущего пользователя, хранящиеся в map
     */

    Map getUserAttributes(OAuth2AuthenticationToken authentication);


    /**
     * Создание объекта (Client) текущего пользователя из google
     *
     * @param userAttributes данные текущего пользователя, хранящиеся в map
     * @return client - сам пользователь
     */

    Client loadClient(Map userAttributes);


    /**
     * Создание username from email
     *
     * @param email - почта текущего пользователя
     * @return username - новый username
     */

    String getUsernameFromEmail(String email);


    /**
     * Создание random password
     *
     * @return password - новый пароль длинной в 8 символов
     */

    String generateRandomPassword();
}


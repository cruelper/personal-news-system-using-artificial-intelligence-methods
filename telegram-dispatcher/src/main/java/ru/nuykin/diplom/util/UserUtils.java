package ru.nuykin.diplom.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String basePath;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    //    @Value("${bot.uri}")
    private String callback = "http://localhost:8085/auth";

    public String generateAuthorizationUrl(String code) {
        StringBuilder authorizationUrl = new StringBuilder(basePath + "/protocol/openid-connect/auth")
                .append("?response_type=code&")
                .append("client_id=").append(clientId).append("&")
                .append("redirect_uri=");

        String redirectUrl = (callback + "/" + code)
                .replace("/", "%2F")
                .replace(":", "%3A");

        authorizationUrl.append(redirectUrl);

        return authorizationUrl.toString();
    }
}

package ru.nuykin.diplom.controller;

import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.nuykin.diplom.component.ActiveUserStorage;
import ru.nuykin.diplom.util.UserUtils;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class WebController {
    @Value("${bot.name}")
    private String botName;
    private String botUri;
    private final UserUtils userUtils;

    private final ActiveUserStorage activeUserStorage;

    @PostConstruct
    public void init() throws URISyntaxException {
        botUri = "tg://resolve?domain=" + botName;
    }

    @GetMapping(path = "/auth")
    public String customers(Principal principal, Model model) {
        String url = userUtils.generateAuthorizationUrl("123");

        return url;
    }

    @GetMapping(path = "/auth/{id}")
    public Object customers(
            @QueryParam("session_state") String session_state,
            @QueryParam("code") String code,
            @PathVariable String id,
            Principal principal, Model model
    ) {
        boolean isSuccess = activeUserStorage.completeAuthorize(id, principal.getName());
        if (isSuccess) return new RedirectView(botUri);
        else return "Произошла ошибка или время авторизации истекло.";
    }
}

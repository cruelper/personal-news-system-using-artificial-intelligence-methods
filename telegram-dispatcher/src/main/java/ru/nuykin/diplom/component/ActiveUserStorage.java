package ru.nuykin.diplom.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nuykin.diplom.service.DistributorService;
import ru.nuykin.diplom.service.impl.DistributorServiceImpl;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ActiveUserStorage {
//    private final SessionRegistry sessionRegistry;
    private DistributorServiceImpl distributorService;
    private final Map<String, Long> activeCodesToTelegramId = new HashMap<>();
    private final Map<Long, String> telegramIdToKeycloakName = new HashMap<>();
    private final Map<Long, Long> telegramIdToChatId = new HashMap<>();
    private final Set<String> activeUsers = new HashSet<>();

    public void registerDistributorService(DistributorServiceImpl distributorService) {
        this.distributorService = distributorService;
    }

    public boolean completeAuthorize(String code, String username) {
        System.out.println("completeAuthorize");
        if (activeCodesToTelegramId.containsKey(code)) {
            Long telegramId = activeCodesToTelegramId.get(code);
            telegramIdToKeycloakName.put(telegramId, username);
            activeCodesToTelegramId.remove(code);

            activeUsers.add(username);
            distributorService.distributeLogInSuccessMessage(telegramIdToChatId.get(telegramId));
            return true;
        }
        return false;
    }

    public String startAuthorize(Long telegramId, Long chatId) {
        System.out.println("startAuthorize");
        String uuid = UUID.randomUUID().toString() + String.valueOf(new Date().getTime());
        activeCodesToTelegramId.put(uuid, telegramId);
        telegramIdToChatId.put(telegramId, chatId);
        return uuid;
    }

    public boolean isUserAuthorize(Long telegramId) {
        System.out.println("isUserAuthorize");

        return activeUsers.contains(telegramIdToKeycloakName.get(telegramId));

//        List<Object> principals = sessionRegistry.getAllPrincipals();
//
//        List<String> usersNamesList = new ArrayList<String>();
//
//        for (Object principal: principals) {
//            if (principal instanceof User) {
//                usersNamesList.add(((User) principal).getUsername());
//            }
//        }

//        if (!telegramIdToKeycloakName.containsKey(telegramId)) return false;
//        if (usersNamesList.contains(telegramIdToKeycloakName.get(telegramId))) return true;
//        telegramIdToKeycloakName.remove(telegramId);
//        return false;
    }

    public void logOutUser(Long telegramId) {
        activeUsers.remove(telegramIdToKeycloakName.get(telegramId));
    }

}

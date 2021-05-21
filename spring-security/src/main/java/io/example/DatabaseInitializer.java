package io.example;

import io.example.domain.dto.CreateUserRequest;
import io.example.domain.model.Role;
import io.example.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    List<String> s = new ArrayList<>();

    private final List<String> usernames =  new ArrayList() {{
        add("ada.lovelace@nix.io");
        add("alan.turing@nix.io");
        add("dennis.ritchie@nix.io");
    }};

    private final List<String> fullNames = new ArrayList() {
        {
            add("Ada Lovelace");
            add("Alan Turing");
            add("Dennis Ritchie");
        }};
    private final List<String> roles =new ArrayList(){{
            add( Role.USER_ADMIN);
            add(Role.AUTHOR_ADMIN);
            add( Role.BOOK_ADMIN);
    }};
    private final String password = "Test12345_";

    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        for (int i = 0; i < usernames.size(); ++i) {
            CreateUserRequest request = new CreateUserRequest();
            request.setUsername(usernames.get(i));
            request.setFullName(fullNames.get(i));
            request.setPassword(password);
            request.setRePassword(password);
            Set<String> authority = new HashSet<>();
            authority.add(roles.get(i));
            request.setAuthorities(authority);

            userService.upsert(request);
        }
    }

}

package com.github.vlsidlyarevich.unity.domain.assistant;

public interface UserAssistant {

    void checkForUserExistance(String id);

    void checkForUsernameExistance(String username);

    boolean usernameExists(String username);

    boolean userNotExists(String id);
}

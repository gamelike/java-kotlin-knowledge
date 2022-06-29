package effitiveJava.chapter2.interfaceTest;

import effitiveJava.chapter2.model.dto.User;

public interface StaticInterface {
    private static User privateBuilder() {
        return User.builder().build();
    }
}

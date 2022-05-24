package application.domain.rest.dto;

/**
 * @author violet.
 */
public record User(String username, String password) {
    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }
}

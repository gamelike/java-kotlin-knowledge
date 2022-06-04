package application.domain.service;

import application.model.po.User;

import java.util.List;

/**
 * @author violet.
 */
public interface AlphaService {

    String login(String username, String password);

    List<User> aspect();

    User save(User user);

}

package application.domain.service.impl;

import application.domain.service.AlphaService;
import application.infrastructure.annotation.EnableFilter;
import application.infrastructure.repository.UserRepository;
import application.model.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author violet.
 */
@Service
public class AlphaServiceImpl implements AlphaService {

    private final UserRepository userRepository;

    public AlphaServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String login(String username, String password) {
        return "success";
    }

    @Override
    @Transactional
    @EnableFilter(name = "username", value = "username")
    public List<User> aspect() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

}

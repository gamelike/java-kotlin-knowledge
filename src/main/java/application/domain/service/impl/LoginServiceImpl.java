package application.domain.service.impl;

import application.domain.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author violet.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String username, String password) {
        return "success";
    }

}

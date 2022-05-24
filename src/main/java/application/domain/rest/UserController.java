package application.domain.rest;

import application.domain.rest.dto.User;
import application.domain.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author violet.
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        log.info("user:{}",user);
        String result = loginService.login(user.username(), user.password());
        return result;
    }



}

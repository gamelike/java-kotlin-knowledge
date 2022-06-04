package application.domain.rest;

import application.domain.rest.dto.User;
import application.domain.service.AlphaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author violet.
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    private final AlphaService alphaService;

    public UserController(AlphaService alphaService) {
        this.alphaService = alphaService;
    }

    @RequestMapping(path = "login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        log.info("user:{}",user);
        return alphaService.login(user.username(), user.password());
    }

    @PutMapping("insert")
    @SneakyThrows
    public String saveUser(@RequestBody application.model.po.User user){
        log.info("user:{}",user);
        return new ObjectMapper().writeValueAsString(alphaService.save(user));
    }

    @GetMapping("getAll")
    @SneakyThrows
    public String getAll(){
        return new ObjectMapper().writeValueAsString(alphaService.aspect());
    }

}

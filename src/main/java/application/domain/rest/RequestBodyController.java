package application.domain.rest;

import annotation.demo.Fruit;
import application.domain.rest.dto.ResponseResult;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author violet.
 */
@RestController
@Slf4j
public class RequestBodyController {

    @Data
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE)
    static class RequestEntity {
        String URL;

        String REQ_BODY;
    }

    @RequestMapping(value = "/requestBody",method = RequestMethod.POST)
    public ResponseResult<RequestEntity> requestBodyTest(@RequestBody Fruit fruit) {
        log.info("fruit: {}", fruit);
        return new ResponseResult<>(1 ,new RequestEntity("/requestBody", fruit.toString()));
    }

}

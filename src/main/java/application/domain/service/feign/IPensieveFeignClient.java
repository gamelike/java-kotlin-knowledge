package application.domain.service.feign;

import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author violet.
 */
@FeignClient(name = "aasserver",path = "/TsmAas", url = "10.7.212.241:22100")
public interface IPensieveFeignClient {

    @GetMapping(value = "/resources/user/getUser/{identityName}")
    JsonObject getUserInfo(@PathVariable String identityName, @RequestParam String clientPrincipal);

}

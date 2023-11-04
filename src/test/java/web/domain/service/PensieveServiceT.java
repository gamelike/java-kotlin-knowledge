package web.domain.service;

import application.domain.service.feign.IPensieveFeignClient;
import com.google.gson.JsonObject;
import org.junit.Test;
import web.annotation.BaseTest;

import javax.annotation.Resource;

/**
 * @author violet.
 */
@BaseTest
public class PensieveServiceT {

    @Resource
    private IPensieveFeignClient pensieveService;

    @Test
    void pensieve_test() {
        JsonObject userInfo = pensieveService.getUserInfo("", "");
    }

}

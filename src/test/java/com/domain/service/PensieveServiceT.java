package com.domain.service;

import application.domain.service.feign.IPensieveFeignClient;
import com.annotation.BaseTest;
import com.google.gson.JsonObject;
import org.junit.Test;

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

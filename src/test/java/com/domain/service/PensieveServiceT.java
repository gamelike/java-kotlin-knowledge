package com.domain.service;

import application.domain.service.feign.IPensieveFeignClient;
import com.annotation.BaseTest;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.sun.xml.bind.v2.TODO;
import feign.Feign;
import feign.Request;
import feign.Target;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

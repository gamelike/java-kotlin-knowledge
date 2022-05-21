package com;

import application.infrastructure.repository.HistoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.BootstrapApplication;

import javax.annotation.Resource;

/**
 * @author violet.
 */
@SpringBootTest(classes = BootstrapApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BootstrapApplicationSmokingTest {

    @Resource
    private HistoryRepository repository;

    @Test
    public void smokingTest(){
        Assert.assertNotNull(repository);
    }

}

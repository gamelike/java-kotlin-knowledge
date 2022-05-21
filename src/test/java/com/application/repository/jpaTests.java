package com.application.repository;

import application.domain.service.HistoryService;
import application.model.constant.HistoryType;
import application.model.po.History;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.BootstrapApplication;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author violet.
 */
@SpringBootTest(classes = BootstrapApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class jpaTests {

    @Resource
    private HistoryService historyService;

    @Test
    public void convert_enum_db_to_vo(){
        History history = historyService.saveHistory(new History(1, "test", HistoryType.安全事件));
        Assert.assertEquals(HistoryType.安全事件, history.getType());
        log.info("{}", history);
        history = historyService.saveHistory(new History(2,"enum", HistoryType.安全管理));
        Assert.assertEquals(HistoryType.安全管理, history.getType());
        log.info("{}", history);

        List<History> allHistory = historyService.getAllHistory();
        Assert.assertEquals(2, allHistory.size());
    }

}

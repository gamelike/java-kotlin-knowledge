package elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import web.BootstrapApplication;
import web.elasticsearch.po.JudgeHistory;
import web.elasticsearch.repository.JudgeHistoryRepository;

import javax.annotation.Resource;
import javax.persistence.EnumType;
import java.util.List;

/**
 * @author violet.
 */

@SpringBootTest(classes = BootstrapApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@Slf4j
public class ElasticsearchSpringData {

    @Resource
    private JudgeHistoryRepository judgeHistoryRepository;

    @Test
    public void judge_history_repository_test() {
        JudgeHistory judgeHistory = new JudgeHistory();
        judgeHistory.setId("111");
        judgeHistory.setName("test");
        judgeHistory.setConverter("converter");
        judgeHistory.setEnumType(EnumType.STRING);
        JudgeHistory save = judgeHistoryRepository.save(judgeHistory);

        List<JudgeHistory> test = judgeHistoryRepository.findAllByNameEquals("test");
        Assert.assertEquals(test.get(0).getConverter(), save.getConverter());
        log.info("{}", save);
        log.info("{}", test);
    }

}

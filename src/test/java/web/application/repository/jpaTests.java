package web.application.repository;

import application.domain.service.HistoryService;
import application.infrastructure.annotation.EnableFilter;
import application.infrastructure.repository.UserRepository;
import application.model.constant.HistoryType;
import application.model.po.Book;
import application.model.po.History;
import application.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import web.BootstrapApplication;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Resource
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void convert_enum_db_to_vo() {
        History history = historyService.saveHistory(new History(1, "test", HistoryType.安全事件));
        Assert.assertEquals(HistoryType.安全事件, history.getType());
        log.info("{}", history);
        history = historyService.saveHistory(new History(2, "enum", HistoryType.安全管理));
        Assert.assertEquals(HistoryType.安全管理, history.getType());
        log.info("{}", history);

        List<History> allHistory = historyService.getAllHistory();
        Assert.assertEquals(2, allHistory.size());
    }

    @Test
    public void formula_query() {
        // 插入一对多和多对一数据时候，需要注意： 需要在外键的实体插入关联对象，否则外键字段为空
        User user = new User();
        user.setUsername("test");
        Book b1 = new Book();
        b1.setNum(4);
        b1.setPrice(2.5d);
        b1.setUser(user);
        List<Book> books = Lists.newArrayList();
        books.add(b1);
        user.setBooks(books);
        User save = userRepository.save(user);
        log.info("{}", save.getBooks().get(0));

        List<User> users = userRepository.findAll();

        Assert.assertEquals(1, users.size());

        log.info("{}", users.get(0).getBooks().get(0).getCount());

        Assert.assertEquals(10d, users.get(0).getBooks().get(0).getCount(), 1d);
    }

    @Test
    @Transactional
    @EnableFilter(name = "username",value = "test")
    public void filter_query() {
        User user = new User();
        user.setUsername("test");
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        Assert.assertEquals(1, users.size());

        User user2 = new User();
        user2.setUsername("abc");
        userRepository.save(user2);

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("filter");
        filter.setParameter("username", "test");
        List<User> list = session.createQuery("from User", User.class)
                .getResultList();
        log.info("{}", list);
        session.disableFilter("filter");
        Assert.assertEquals(1, list.size());
        List<User> all = userRepository.findAll();

        Assert.assertEquals(2, all.size());
    }

}

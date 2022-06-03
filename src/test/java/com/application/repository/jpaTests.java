package com.application.repository;

import application.domain.service.HistoryService;
import application.infrastructure.annotation.EnableFilter;
import application.infrastructure.repository.UserRepository;
import application.model.constant.HistoryType;
import application.model.po.Book;
import application.model.po.History;
import application.model.po.User;
import com.annotation.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.util.BeanDefinitionUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.config.JtaTransactionManagerFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;
import web.BootstrapApplication;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Objects;

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
    public void filter_query() {
        test();
        select();
    }

    @Transactional
    public void test() {
        User user = new User();
        user.setUsername("test");
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        Assert.assertEquals(1, users.size());

        User user2 = new User();
        user2.setUsername("test2");
        userRepository.save(user2);


    }

    @Transactional
    @EnableFilter(value = "test")
    public void select() {
        Filter filter = entityManager.unwrap(Session.class).enableFilter("filter");
        filter.setParameter("filterName", "test");
        List<User> users2 = userRepository.findAll();
        Assert.assertEquals(1, users2.size());
        entityManager.unwrap(Session.class).disableFilter("filter");
    }

}

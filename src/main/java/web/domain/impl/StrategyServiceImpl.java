package web.domain.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import web.design.StrategyPatternFactory;
import web.design.model.SearchType;
import web.design.model.po.StrategyEntity;
import web.domain.StrategyService;

import java.util.List;
import java.util.Objects;

/**
 * @author violet
 * @since 2022/11/3
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class StrategyServiceImpl implements StrategyService {

    private final StrategyPatternFactory factory;

    @Override
    public List<StrategyEntity> queryStrategyEntityList(SearchType type) {
        log.info("查询策略为 {} 的方法", type.toString());
        return factory.create(type).queryDataList();
    }

    @Override
    public StrategyEntity saveStrategyEntity(StrategyEntity entity, SearchType type) {
        log.info("保存策略为 {} 的方法", type.toString());
        return factory.create(Objects.requireNonNull(type)).saveData(entity);
    }
}

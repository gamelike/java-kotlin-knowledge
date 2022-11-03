package web.design.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.design.StrategyPattern;
import web.design.model.SearchType;
import web.design.model.po.StrategyEntity;
import web.design.model.repository.StrategyRepository;

import java.util.List;

/**
 * @author violet
 * @since 2022/11/3
 */
@Service("Database")
@RequiredArgsConstructor
@Slf4j
public class DefaultStrategyPatternImpl implements StrategyPattern {

    private final StrategyRepository strategyRepository;

    @Override
    public SearchType type() {
        return SearchType.Database;
    }

    @Override
    public List<StrategyEntity> queryDataList() {
        return strategyRepository.findAll();
    }

    @Override
    public StrategyEntity saveData(StrategyEntity entity) {
        return strategyRepository.save(entity);
    }
}

package web.design;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.design.model.SearchType;

import java.util.Map;

/**
 * @author violet
 * @since 2022/11/3
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StrategyPatternFactory {

    private final Map<String, StrategyPattern> strategyPatternMap;

    public StrategyPattern create(SearchType type) {
        return strategyPatternMap.get(type.name());
    }

}

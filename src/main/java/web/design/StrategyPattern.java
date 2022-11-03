package web.design;

import web.design.model.SearchType;
import web.design.model.po.StrategyEntity;

import java.util.List;

/**
 * @author violet
 * @since 2022/11/3
 */
public interface StrategyPattern {

    /**
     * 获取类型。
     *
     * @return {@link SearchType}
     */
    SearchType type();

    /**
     * query data by condition and type.
     *
     * @return {@link java.util.List}
     */
    List<StrategyEntity> queryDataList();

    /**
     * save entity.
     *
     * @param entity {@link StrategyEntity}
     */
    StrategyEntity saveData(StrategyEntity entity);

}

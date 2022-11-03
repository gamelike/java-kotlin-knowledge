package web.design.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.design.model.po.StrategyEntity;

/**
 * @author violet
 * @since 2022/11/3
 */
public interface StrategyRepository extends JpaRepository<StrategyEntity, Long> {
}

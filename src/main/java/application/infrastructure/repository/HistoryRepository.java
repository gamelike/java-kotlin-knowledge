package application.infrastructure.repository;

import application.model.po.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoryRepository extends JpaRepository<History, Integer>, JpaSpecificationExecutor<History> {
}
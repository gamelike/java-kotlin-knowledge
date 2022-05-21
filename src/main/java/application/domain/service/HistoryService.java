package application.domain.service;

import application.model.po.History;

import java.util.List;

/**
 * @author violet.
 */
public interface HistoryService {

    List<History> getAllHistory();

    History saveHistory(History history);

    History deleteHistory(Integer id);

}

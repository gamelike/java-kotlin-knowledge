package application.domain.service.impl;

import application.domain.service.HistoryService;
import application.infrastructure.repository.HistoryRepository;
import application.model.po.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author violet.
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    @Override
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    @Override
    @Transactional
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History deleteHistory(Integer id) {
        return null;
    }
}

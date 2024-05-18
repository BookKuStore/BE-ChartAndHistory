package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.repository.HistoryRepository;
import ChartAndHistory.backend.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    @Override
    public History getHistoryById(long id) {
        return historyRepository.findById(id).orElse(null);
    }
}


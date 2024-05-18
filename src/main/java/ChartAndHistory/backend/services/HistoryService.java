package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import java.util.List;

public interface HistoryService {
    History saveHistory(History history);
    List<History> getAllHistories();
    History getHistoryById(long id);
}

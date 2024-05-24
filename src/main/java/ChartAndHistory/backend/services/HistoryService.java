package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.models.Cart;
import java.util.List;

public interface HistoryService {
    History saveHistory(List<Cart> paidCheckouts);
    List<History> getAllHistories();
    History getHistoryById(long id);
    History addCartToHistory(long historyId, Cart cart);
}

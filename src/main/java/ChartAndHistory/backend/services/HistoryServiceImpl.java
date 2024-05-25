package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.repository.HistoryRepository;
import ChartAndHistory.backend.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final CartRepository cartRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository, CartRepository cartRepository) {
        this.historyRepository = historyRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public History saveHistory(List<Cart> paidCheckouts) {
        for (Cart cart : paidCheckouts) {
            Long originalId = cart.getCartId();
            cartRepository.save(cart);
            Long newId = cart.getCartId();
            if (!originalId.equals(newId)) {
                // Log atau throw exception jika ID berubah
                System.out.println("ID Cart berubah dari " + originalId + " menjadi " + newId);
            }
        }
        History history = new History();
        history.setPaidCheckouts(paidCheckouts);
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

    @Override
    @Transactional
    public History addCartToHistory(long historyId, Cart cart) {
        History history = historyRepository.findById(historyId).orElse(null);
        if (history != null) {
            cartRepository.save(cart);  // save() handles both persist and merge
            List<Cart> paidCheckouts = history.getPaidCheckouts();
            paidCheckouts.add(cart);
            history.setPaidCheckouts(paidCheckouts);
            return historyRepository.save(history);
        }
        return null;
    }

    @Override
    @Transactional
    public void restoreHistory(History history) {
        for (Cart cart : history.getPaidCheckouts()) {
            cartRepository.save(cart);  // save() handles both persist and merge
        }
        historyRepository.save(history);
    }
}

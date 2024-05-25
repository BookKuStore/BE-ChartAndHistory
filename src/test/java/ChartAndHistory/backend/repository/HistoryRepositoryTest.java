package ChartAndHistory.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ChartAndHistory.backend.models.History;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void testSaveHistory() {
        History history = new History();
        historyRepository.save(history);

        assertTrue(history.getHistoryId() > 0);
    }

    @Test
    public void testFindAllHistories() {
        History history1 = new History();
        History history2 = new History();
        historyRepository.save(history1);
        historyRepository.save(history2);

        List<History> histories = historyRepository.findAll();

        assertEquals(2, histories.size());
    }

    @Test
    public void testFindHistoryById() {
        History history = new History();
        historyRepository.save(history);

        History foundHistory = historyRepository.findById(history.getHistoryId()).orElse(null);

        assertEquals(history, foundHistory);
    }

    @Test
    public void testDeleteHistoryById() {
        History history = new History();
        historyRepository.save(history);

        historyRepository.deleteById(history.getHistoryId());

        assertTrue(historyRepository.findById(history.getHistoryId()).isEmpty());
    }
}


package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.services.HistoryService;
import ChartAndHistory.backend.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveHistory() {
        History history = new History();
        when(historyRepository.save(history)).thenReturn(history);
        History savedHistory = historyService.saveHistory(history);
        assertEquals(history, savedHistory);
        verify(historyRepository, times(1)).save(history);
    }

    @Test
    void testGetAllHistories() {
        List<History> histories = Arrays.asList(new History(), new History());
        when(historyRepository.findAll()).thenReturn(histories);
        List<History> result = historyService.getAllHistories();
        assertEquals(histories, result);
        verify(historyRepository, times(1)).findAll();
    }

    @Test
    void testGetHistoryById() {
        History history = new History();
        when(historyRepository.findById(1L)).thenReturn(Optional.of(history));
        History result = historyService.getHistoryById(1L);
        assertEquals(history, result);
        verify(historyRepository, times(1)).findById(1L);
    }
}

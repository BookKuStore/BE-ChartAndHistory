package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.controllers.HistoryController;
import ChartAndHistory.backend.services.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HistoryControllerTest {

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHistory() {
        History history = new History();
        when(historyService.saveHistory(history)).thenReturn(history);
        ResponseEntity<History> response = historyController.createHistory(history);
        assertEquals(history, response.getBody());
        verify(historyService, times(1)).saveHistory(history);
    }

    @Test
    void testGetAllHistories() {
        List<History> histories = Arrays.asList(new History(), new History());
        when(historyService.getAllHistories()).thenReturn(histories);
        ResponseEntity<List<History>> response = historyController.getAllHistories();
        assertEquals(histories, response.getBody());
        verify(historyService, times(1)).getAllHistories();
    }

    @Test
    void testGetHistoryById() {
        History history = new History();
        when(historyService.getHistoryById(1L)).thenReturn(history);
        ResponseEntity<History> response = historyController.getHistoryById(1L);
        assertEquals(history, response.getBody());
        verify(historyService, times(1)).getHistoryById(1L);
    }
}

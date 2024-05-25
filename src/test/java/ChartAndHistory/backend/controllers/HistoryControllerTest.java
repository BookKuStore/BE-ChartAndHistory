package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.controllers.HistoryController;
import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.services.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

//    @Test
//    void testCreateHistory() {
//        List<Cart> carts = new ArrayList<>();
//        carts.add(new Cart());
//        History history = new History();
//        when(historyService.saveHistory(carts)).thenReturn(history);
//
//        ResponseEntity<History> responseEntity = historyController.createHistory(carts);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(history, responseEntity.getBody());
//    }

    @Test
    void testGetAllHistories() {
        List<History> histories = new ArrayList<>();
        histories.add(new History());
        when(historyService.getAllHistories()).thenReturn(histories);

        ResponseEntity<List<History>> responseEntity = historyController.getAllHistories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(histories, responseEntity.getBody());
    }

    @Test
    void testGetHistoryById() {
        long id = 1;
        History history = new History();
        when(historyService.getHistoryById(id)).thenReturn(history);

        ResponseEntity<History> responseEntity = historyController.getHistoryById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(history, responseEntity.getBody());
    }

//    @Test
//    void testAddCartToHistory() {
//        long id = 1;
//        History history = new History();
//        Cart cart = new Cart();
//        when(historyService.getHistoryById(id)).thenReturn(history);
//        when(historyService.addCartToHistory(id, cart)).thenReturn(history);
//
//        ResponseEntity<History> responseEntity = historyController.addCartToHistory(id, cart);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(history, responseEntity.getBody());
//    }

    @Test
    void testAddCartToHistoryNotFound() {
        long id = 1;
        Cart cart = new Cart();
        when(historyService.getHistoryById(id)).thenReturn(null);

        ResponseEntity<History> responseEntity = historyController.addCartToHistory(id, cart);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testRestoreHistory() {
        long id = 1;
        History history = new History();
        when(historyService.getHistoryById(id)).thenReturn(history);

        ResponseEntity<History> responseEntity = historyController.restoreHistory(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(history, responseEntity.getBody());
        verify(historyService, times(1)).restoreHistory(history);
    }

    @Test
    void testRestoreHistoryNotFound() {
        long id = 1;
        when(historyService.getHistoryById(id)).thenReturn(null);

        ResponseEntity<History> responseEntity = historyController.restoreHistory(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
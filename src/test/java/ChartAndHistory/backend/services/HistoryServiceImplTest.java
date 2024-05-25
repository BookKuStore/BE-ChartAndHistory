package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.repository.HistoryRepository;
import ChartAndHistory.backend.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.repository.CartRepository;
import ChartAndHistory.backend.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HistoryServiceImplTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveHistory_Success() {
        // Arrange
        List<Cart> paidCheckouts = new ArrayList<>();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        paidCheckouts.add(cart1);
        paidCheckouts.add(cart2);
        when(cartRepository.save(any())).thenReturn(cart1, cart2);
        when(historyRepository.save(any())).thenReturn(new History());

        // Act
        historyService.saveHistory(paidCheckouts);

        // Assert
        verify(cartRepository, times(2)).save(any());
        verify(historyRepository, times(1)).save(any());
    }

    @Test
    void getAllHistories_Success() {
        // Arrange
        List<History> historyList = new ArrayList<>();
        when(historyRepository.findAll()).thenReturn(historyList);

        // Act
        List<History> result = historyService.getAllHistories();

        // Assert
        assertSame(historyList, result);
    }

    @Test
    void getHistoryById_Exists_Success() {
        // Arrange
        long historyId = 1L;
        History history = new History();
        when(historyRepository.findById(historyId)).thenReturn(Optional.of(history));

        // Act
        History result = historyService.getHistoryById(historyId);

        // Assert
        assertSame(history, result);
    }

    @Test
    void getHistoryById_NotExists_ReturnsNull() {
        // Arrange
        long historyId = 1L;
        when(historyRepository.findById(historyId)).thenReturn(Optional.empty());

        // Act
        History result = historyService.getHistoryById(historyId);

        // Assert
        assertNull(result);
    }

//    @Test
//    void addCartToHistory_Success() {
//        // Arrange
//        long historyId = 1L;
//        History history = new History();
//        Cart cart = new Cart();
//        when(historyRepository.findById(historyId)).thenReturn(Optional.of(history));
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        // Act
//        History result = historyService.addCartToHistory(historyId, cart);
//
//        // Assert
//        assertSame(history, result);
//        verify(cartRepository, times(1)).save(cart);
//        verify(historyRepository, times(1)).save(history);
//    }

    @Test
    void addCartToHistory_HistoryNotFound_ReturnsNull() {
        // Arrange
        long historyId = 1L;
        Cart cart = new Cart();
        when(historyRepository.findById(historyId)).thenReturn(Optional.empty());

        // Act
        History result = historyService.addCartToHistory(historyId, cart);

        // Assert
        assertNull(result);
        verify(cartRepository, never()).save(cart);
        verify(historyRepository, never()).save(any());
    }

    @Test
    void restoreHistory_Success() {
        // Arrange
        History history = new History();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        history.setPaidCheckouts(List.of(cart1, cart2));
        when(cartRepository.save(any())).thenReturn(cart1, cart2);
        when(historyRepository.save(history)).thenReturn(history);

        // Act
        historyService.restoreHistory(history);

        // Assert
        verify(cartRepository, times(2)).save(any());
        verify(historyRepository, times(1)).save(history);
    }
}
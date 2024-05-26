import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryTest {

    private History history;

    @BeforeEach
    void setUp() {
        history = new History();
    }

//    @Test
//    void saveAndRestoreTest() {
//        List<Cart> paidCheckouts = new ArrayList<>();
//        Cart cart1 = new Cart();
//        Cart cart2 = new Cart();
//        paidCheckouts.add(cart1);
//        paidCheckouts.add(cart2);
//
//        // Save history state
//        History.HistoryMemento memento = history.save();
//        history.restore(memento);
//
//        assertEquals(paidCheckouts, history.getPaidCheckouts());
//    }
}
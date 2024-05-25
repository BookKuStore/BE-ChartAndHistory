package ChartAndHistory.backend.models;

import ChartAndHistory.backend.models.Cart;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "history_id")
    private List<Cart> paidCheckouts;

    public HistoryMemento save() {
        return new HistoryMemento(new ArrayList<>(paidCheckouts));
    }

    // Restore from Memento
    public void restore(HistoryMemento memento) {
        paidCheckouts = memento.getPaidCheckouts();
    }

    // Memento inner class
    public static class HistoryMemento {
        private final List<Cart> paidCheckouts;

        private HistoryMemento(List<Cart> paidCheckouts) {
            this.paidCheckouts = paidCheckouts;
        }

        private List<Cart> getPaidCheckouts() {
            return paidCheckouts;
        }
    }
}
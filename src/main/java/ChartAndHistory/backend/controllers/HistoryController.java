package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.services.HistoryService;
import ChartAndHistory.backend.caretaker.HistoryCaretaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryCaretaker historyCaretaker = new HistoryCaretaker();

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody List<Cart> paidCheckouts) {
        History savedHistory = historyService.saveHistory(paidCheckouts);
        historyCaretaker.saveState(savedHistory);
        return ResponseEntity.ok(savedHistory);
    }

    @GetMapping
    public ResponseEntity<List<History>> getAllHistories() {
        List<History> histories = historyService.getAllHistories();
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable long id) {
        History history = historyService.getHistoryById(id);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/{id}/add-cart")
    public ResponseEntity<History> addCartToHistory(@PathVariable long id, @RequestBody Cart cart) {
        History history = historyService.getHistoryById(id);
        if (history != null) {
            historyCaretaker.saveState(history);  // Save state before modification
            History updatedHistory = historyService.addCartToHistory(id, cart);
            return ResponseEntity.ok(updatedHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<History> restoreHistory(@PathVariable long id) {
        History history = historyService.getHistoryById(id);
        if (history != null) {
            historyCaretaker.restoreState(history);  // Restore the previous state
            historyService.restoreHistory(history);  // Persist restored state
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
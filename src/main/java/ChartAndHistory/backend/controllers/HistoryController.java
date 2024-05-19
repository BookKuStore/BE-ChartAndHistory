package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        History savedHistory = historyService.saveHistory(history);
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
}

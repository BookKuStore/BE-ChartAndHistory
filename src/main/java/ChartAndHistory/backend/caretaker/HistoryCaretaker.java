package ChartAndHistory.backend.caretaker;

import ChartAndHistory.backend.models.History;
import java.util.Stack;

public class HistoryCaretaker {
    private final Stack<History.HistoryMemento> mementoStack = new Stack<>();

    public void saveState(History history) {
        mementoStack.push(history.save());
    }

    public void restoreState(History history) {
        if (!mementoStack.isEmpty()) {
            history.restore(mementoStack.pop());
        }
    }
}
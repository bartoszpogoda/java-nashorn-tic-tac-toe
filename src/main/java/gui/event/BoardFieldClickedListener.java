package gui.event;

import java.util.EventListener;

public interface BoardFieldClickedListener extends EventListener {
    void boardFieldClicked(BoardFieldClicked event);
}

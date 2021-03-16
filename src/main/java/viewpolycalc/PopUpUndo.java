package viewpolycalc;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

public class PopUpUndo extends JPopupMenu {
    private final HashMap<String, Integer> filtter;
    public PopUpUndo() {
        filtter = new HashMap<>();
    }

    public void addInHistory(JMenuItem item) {
        if(!filtter.containsKey(item.getText())) {
            filtter.put(item.getText(), new Random().nextInt());
            add(item);
        }
    }
}

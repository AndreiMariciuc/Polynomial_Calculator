package viewpolycalc;

import javax.swing.*;
import java.awt.*;

public class ViewPolycalc extends JFrame {
    private final KeyboardPanel keyboardPane;
    private final InputOutputPanel inputOutputPanel;

    public ViewPolycalc(String title) {
        super(title);
        //init components
        keyboardPane = new KeyboardPanel();
        inputOutputPanel = new InputOutputPanel();
        //set layout
        setLayout(new GridLayout(2, 1));
        //add components
        add(inputOutputPanel);
        add(keyboardPane);
        //view sets!
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public InputOutputPanel getInputOutputPanel() {
        return inputOutputPanel;
    }

    public KeyboardPanel getKeyboardPane() {
        return keyboardPane;
    }
}

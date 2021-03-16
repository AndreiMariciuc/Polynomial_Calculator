package viewpolycalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputOutputPanel extends JPanel {

    private final JTextField input1;
    private final JTextField input2;
    private final JLabel output;

    private int select;

    public InputOutputPanel() {
        //input panel
        JPanel inputPanel = new JPanel();
        input1 = new JTextField("0");
        input2 = new JTextField("0");
        setLayout(new GridLayout(1, 2));
        inputPanel.setLayout(new GridLayout(2, 1));
        inputPanel.add(input1);
        inputPanel.add(input2);

        //help with selection
        implementFocus();

        //output
        output = new JLabel("Aici o sa primesti rezultatul!");
        //add to InputOutputPanel
        add(inputPanel);
        add(output);
    }

    private void implementFocus() {
        input1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                select = 1;
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        input2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                select = 2;
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void addKeyAdapter(KeyAdapter keyAdapter) {
        input1.addKeyListener(keyAdapter);
        input2.addKeyListener(keyAdapter);
    }

    public void addPopUpListener(MouseAdapter mouseAdapter) {
        output.addMouseListener(mouseAdapter);
    }

    public void addUndoListener(MouseAdapter mouseAdapter1, MouseAdapter mouseAdapter2) {
        input1.addMouseListener(mouseAdapter1);
        input2.addMouseListener(mouseAdapter2);
    }

    public void setOutput(String polyOutput) {
        output.setText(polyOutput);
    }

    public JTextField getInput1() {
        return input1;
    }

    public JTextField getInput2() {
        return input2;
    }

    public int getSelect() {
        return select;
    }

    public String getOutput() {
        return output.getText();
    }

    public void setInput1(String input1) {
        this.input1.setText(input1);
    }

    public void setInput2(String input2) {
        this.input2.setText(input2);
    }
}

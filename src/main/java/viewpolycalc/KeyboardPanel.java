package viewpolycalc;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class KeyboardPanel extends JPanel {
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;
    private final JButton button7;
    private final JButton button8;
    private final JButton button9;
    private final JButton button0;
    private final JButton buttonAdd;
    private final JButton buttonSub;
    private final JButton buttonMul;
    private final JButton buttonDiv;
    private final JButton buttonInteg;
    private final JButton buttonDeriv;
    private final JButton buttonExp;
    private final JButton buttonNec;
    private final JButton buttonPlus;
    private final JButton buttonMinus;
    private final JButton buttonDelete;

    public KeyboardPanel() {
        button0 = new JButton("0"); button1 = new JButton("1"); button2 = new JButton("2");
        button3 = new JButton("3"); button4 = new JButton("4"); button5 = new JButton("5");
        button6 = new JButton("6"); button7 = new JButton("7"); button8 = new JButton("8");
        button9 = new JButton("9"); buttonAdd = new JButton("add"); buttonSub = new JButton("sub");
        buttonMul = new JButton("mul"); buttonDiv = new JButton("div"); buttonDeriv = new JButton("deriv");
        buttonInteg = new JButton("integ"); buttonExp = new JButton("^"); buttonNec = new JButton("x");
        buttonPlus = new JButton("+"); buttonMinus = new JButton("-"); buttonDelete = new JButton("del");

        setLayout(new GridLayout(7, 3));
        setBorder(new BevelBorder(BevelBorder.RAISED));

        add(button0); add(button1); add(button2);
        add(button3); add(button4); add(button5);
        add(button6); add(button7); add(button8);
        add(button9); add(buttonAdd); add(buttonSub);
        add(buttonMul); add(buttonDiv); add(buttonDeriv);
        add(buttonInteg); add(buttonExp); add(buttonNec);
        add(buttonPlus); add(buttonMinus);  add(buttonDelete);
    }

    public void addOperationListener(ActionListener l) {
        buttonAdd.addActionListener(l);
        buttonSub.addActionListener(l);
        buttonDiv.addActionListener(l);
        buttonMul.addActionListener(l);
        buttonDeriv.addActionListener(l);
        buttonInteg.addActionListener(l);
    }

    public void addKeyboardListener(ActionListener l) {
        addListener(l, button1, button2, button3, button4, button5, button6, button7, button8);
        addListener(l, button9, button0, buttonAdd, buttonSub, buttonMinus, buttonPlus, buttonExp, buttonDelete);
        buttonNec.addActionListener(l);
    }

    private void addListener(ActionListener l, JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6, JButton button7, JButton button8) {
        button1.addActionListener(l);
        button2.addActionListener(l);
        button3.addActionListener(l);
        button4.addActionListener(l);
        button5.addActionListener(l);
        button6.addActionListener(l);
        button7.addActionListener(l);
        button8.addActionListener(l);
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public JButton getButton5() {
        return button5;
    }

    public JButton getButton6() {
        return button6;
    }

    public JButton getButton7() {
        return button7;
    }

    public JButton getButton8() {
        return button8;
    }

    public JButton getButton9() {
        return button9;
    }

    public JButton getButton0() {
        return button0;
    }

    public JButton getButtonAdd() {
        return buttonAdd;
    }

    public JButton getButtonSub() {
        return buttonSub;
    }

    public JButton getButtonMul() {
        return buttonMul;
    }

    public JButton getButtonDiv() {
        return buttonDiv;
    }

    public JButton getButtonInteg() {
        return buttonInteg;
    }

    public JButton getButtonDeriv() {
        return buttonDeriv;
    }

    public JButton getButtonExp() {
        return buttonExp;
    }

    public JButton getButtonNec() {
        return buttonNec;
    }

    public JButton getButtonPlus() {
        return buttonPlus;
    }

    public JButton getButtonMinus() {
        return buttonMinus;
    }

    public JButton getButtonDelete() {
        return buttonDelete;
    }
}

package controllerpolycalc;

import modelpolycalc.operation.ModelOperationPolycalc;
import modelpolycalc.Polynomial;
import exception.PolynomialException;
import viewpolycalc.*;

import javax.swing.*;
import java.awt.event.*;

public class ControllerPolycalc {
    private final KeyboardPanel KEYBOARD;
    private final InputOutputPanel INPUT_OUTPUT;
    private ModelOperationPolycalc modelOp;
    private final PopUpUndo UNDO_FIRST_TEXT;
    private final PopUpUndo UNDO_SECOND_TEXT;
    PopUpLaTeX latexMenu;

    public ControllerPolycalc(ViewPolycalc viewPolycalc, ModelOperationPolycalc modelOp) {
        //latex
        latexMenu = new PopUpLaTeX();
        //undo
        UNDO_FIRST_TEXT = new PopUpUndo();
        UNDO_SECOND_TEXT = new PopUpUndo();
        //view
        KEYBOARD = viewPolycalc.getKeyboardPane();
        INPUT_OUTPUT = viewPolycalc.getInputOutputPanel();
        //model
        this.modelOp = modelOp;
        //adaugare listeneri
        INPUT_OUTPUT.addKeyAdapter(new FormatInputKeyboard());
        INPUT_OUTPUT.addPopUpListener(new PopClickListener());
        INPUT_OUTPUT.addUndoListener(new UndoFirstClickListener(), new UndoSecondClickListener());
        KEYBOARD.addKeyboardListener(new KeyboardListener());
        KEYBOARD.addOperationListener(new OperationListener());
    }

    // calsa formatare input de la tastatura
    static class FormatInputKeyboard extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != 'x' && e.getKeyChar() != '^' &&
                    e.getKeyChar() != '+' && e.getKeyChar() != '-' && e.getKeyChar() != 8) {
                e.consume();
                JOptionPane.showMessageDialog(null, "Din pacate nu poti apasa <<tasta{cod}>> -> "
                        + e.getKeyChar() + "{" + (int) e.getKeyChar() + "}" + ", citeste documentatia pentru mai multe detalii!");
            }
        }
    }

    // calculator keyboard
    class KeyboardListener implements ActionListener {
        private void makeChange(String op) {
            if (INPUT_OUTPUT.getSelect() == 1)
                INPUT_OUTPUT.getInput1().setText(INPUT_OUTPUT.getInput1().getText() + op);
            else if (INPUT_OUTPUT.getSelect() == 2)
                INPUT_OUTPUT.getInput2().setText(INPUT_OUTPUT.getInput2().getText() + op);
            else
                JOptionPane.showMessageDialog(null, "Selecteaza mai intai un input!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == KEYBOARD.getButton0())
                makeChange("0");
            else if (e.getSource() == KEYBOARD.getButton1())
                makeChange("1");
            else if (e.getSource() == KEYBOARD.getButton2())
                makeChange("2");
            else if (e.getSource() == KEYBOARD.getButton3())
                makeChange("3");
            else if (e.getSource() == KEYBOARD.getButton4())
                makeChange("4");
            else if (e.getSource() == KEYBOARD.getButton5())
                makeChange("5");
            else if (e.getSource() == KEYBOARD.getButton6())
                makeChange("6");
            else if (e.getSource() == KEYBOARD.getButton7())
                makeChange("7");
            else if (e.getSource() == KEYBOARD.getButton8())
                makeChange("8");
            else if (e.getSource() == KEYBOARD.getButton9())
                makeChange("9");
            else if (e.getSource() == KEYBOARD.getButtonPlus())
                makeChange("+");
            else if (e.getSource() == KEYBOARD.getButtonMinus())
                makeChange("-");
            else if (e.getSource() == KEYBOARD.getButtonExp())
                makeChange("^");
            else if (e.getSource() == KEYBOARD.getButtonNec())
                makeChange("x");
            else if (e.getSource() == KEYBOARD.getButtonDelete()) {
                if (INPUT_OUTPUT.getSelect() == 1) {
                    if (INPUT_OUTPUT.getInput1().getText().length() > 0)
                        INPUT_OUTPUT.getInput1().setText(INPUT_OUTPUT.getInput1().getText().substring(0, INPUT_OUTPUT.getInput1().getText().length() - 1));
                    else
                        JOptionPane.showMessageDialog(null, "Nu mai poti sa stergi!");
                } else if (INPUT_OUTPUT.getSelect() == 2) {
                    if (INPUT_OUTPUT.getInput2().getText().length() > 0)
                        INPUT_OUTPUT.getInput2().setText(INPUT_OUTPUT.getInput2().getText().substring(0, INPUT_OUTPUT.getInput2().getText().length() - 1));
                    else
                        JOptionPane.showMessageDialog(null, "Nu mai poti sa stergi!");
                } else
                    JOptionPane.showMessageDialog(null, "Selecteaza mai intai un input!");
            }
        }
    }

    /**
     * clasa responsabila pentru salvarea in istorie a inputurilor
     * si pentru afisarea rezultatului in urma operatiei!
     */
    class OperationListener implements ActionListener {
        //adauga un item pentru lista de undo
        private JMenuItem getItemInHistory(String input, int op) {
            JMenuItem item = new JMenuItem(input);
            item.addActionListener(e1 -> {
                if (op == 1)
                    INPUT_OUTPUT.setInput1(item.getText());
                else
                    INPUT_OUTPUT.setInput2(item.getText());
            });
            return item;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String input1 = INPUT_OUTPUT.getInput1().getText();
            String input2 = INPUT_OUTPUT.getInput2().getText();
            try {
                modelOp = new ModelOperationPolycalc(new Polynomial(input1), new Polynomial(input2));
                if (e.getSource() == KEYBOARD.getButtonAdd()) {
                    UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                    UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                    INPUT_OUTPUT.setOutput(modelOp.add().toString());
                } else if (e.getSource() == KEYBOARD.getButtonSub()) {
                    UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                    UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                    INPUT_OUTPUT.setOutput(modelOp.sub().toString());
                } else if (e.getSource() == KEYBOARD.getButtonDeriv()) {
                    if (input1.length() > 0) {
                        UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                        INPUT_OUTPUT.setOutput(modelOp.deriv().toString());
                    } else if (input2.length() > 0) {
                        try {
                            UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                            modelOp = new ModelOperationPolycalc(new Polynomial(input2));
                            INPUT_OUTPUT.setOutput(modelOp.deriv().toString());
                        } catch (PolynomialException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Nu ai introdus nimic!");
                } else if (e.getSource() == KEYBOARD.getButtonInteg()) {
                    if (input1.length() > 0) {
                        INPUT_OUTPUT.setOutput(modelOp.integ().toString());
                        UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                    } else if (input2.length() > 0) {
                        try {
                            UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                            modelOp = new ModelOperationPolycalc(new Polynomial(input2));
                            INPUT_OUTPUT.setOutput(modelOp.integ().toString());
                        } catch (PolynomialException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Nu ai introdus nimic!");
                } else if (e.getSource() == KEYBOARD.getButtonMul()) {
                    UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                    UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                    INPUT_OUTPUT.setOutput(modelOp.mul().toString());
                } else if (e.getSource() == KEYBOARD.getButtonDiv()) {
                    try {
                        UNDO_FIRST_TEXT.addInHistory(getItemInHistory(input1, 1));
                        UNDO_SECOND_TEXT.addInHistory(getItemInHistory(input2, 2));
                        INPUT_OUTPUT.setOutput(modelOp.div().toString());
                    } catch (PolynomialException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            } catch (PolynomialException polynomialException) {
                JOptionPane.showMessageDialog(null, polynomialException.getMessage());
            }

        }
    }
    //clasa pentru lansarea LaTeX
    class PopClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e) {
            latexMenu.setMathExp(INPUT_OUTPUT.getOutput());
            latexMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    //clasa pentru lansarea popup pentru primul input
    class UndoFirstClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e) {
            UNDO_FIRST_TEXT.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    //clasa pentru lansarea popup pentru al doilea input
    class UndoSecondClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e) {
            UNDO_SECOND_TEXT.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}

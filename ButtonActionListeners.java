// ButtonActionListeners.java
package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Color;

public class ButtonActionListeners implements ActionListener {

    private CalculatorGUI gui;
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public ButtonActionListeners(CalculatorGUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        try {
            if (buttonText.equals("ON")) {
                onButtonActionPerformed();
            } else if (buttonText.equals("OFF")) {
                offButtonActionPerformed();
            } else if (buttonText.equals("C")) {
                clearButtonActionPerformed();
            } else if (buttonText.equals("DEL")) {
                deleteButtonActionPerformed();
            } else if (buttonText.equals("x²") || buttonText.equals("1/x")) {
                operatorButtonActionPerformed(buttonText);
            } else if (buttonText.equals("=")) {
                equalsButtonActionPerformed();
            } else if (isNumberButton(buttonText)) {
                numberButtonActionPerformed(buttonText);
            } else {
                operatorButtonActionPerformed(buttonText);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onButtonActionPerformed() {
        gui.setOn(true);
        gui.getDisplay().setEditable(true);
        gui.getDisplay().requestFocusInWindow();
        gui.getDisplay().setBackground(Color.WHITE);
        gui.getOnButton().setEnabled(false);
        gui.getOffButton().setEnabled(true);
        // Enable other buttons
    }

    private void offButtonActionPerformed() {
        gui.setOn(false);
        gui.getDisplay().setEditable(false);
        gui.getDisplay().setText("");
        gui.getDisplay().setBackground(new Color(240, 240, 240));
        gui.getOnButton().setEnabled(true);
        gui.getOffButton().setEnabled(false);
        // Disable other buttons
    }

    private void clearButtonActionPerformed() {
        gui.getDisplay().setText("");
        // Reset other variables
        firstNumber = 0;
        secondNumber = 0;
        operator = "";
    }

    private void deleteButtonActionPerformed() {
        String currentText = gui.getDisplay().getText();
        if (currentText.length() > 0) {
            gui.getDisplay().setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void operatorButtonActionPerformed(String operator) {
        if (gui.isOn()) {
            if (!gui.getDisplay().getText().isEmpty()) {
                firstNumber = Double.parseDouble(gui.getDisplay().getText());
                if (operator.equals("1/x")) {
                    // Handle 1/x operation separately
                    double result = 1 / firstNumber;
                    gui.getDisplay().setText(String.valueOf(result));
                    firstNumber = 0;
                    secondNumber = 0;
                    this.operator = "";
                } else if (operator.equals("x²")) {
                    // Handle 1/x operation separately
                    double result = firstNumber*firstNumber;
                    gui.getDisplay().setText(String.valueOf(result));
                    firstNumber = 0;
                    secondNumber = 0;
                    this.operator = "";
                }else{
                    this.operator = operator;
                    gui.getDisplay().setText("");
                }
            }
        }
    }

    private void equalsButtonActionPerformed() {
        if (gui.isOn()) {
            if (!gui.getDisplay().getText().isEmpty()) {
                secondNumber = Double.parseDouble(gui.getDisplay().getText());
                Operation operation = new Operation(firstNumber, secondNumber, operator);
                double result = operation.performOperation();
                gui.getDisplay().setText(String.valueOf(result));
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
            }
        }
    }

    private void numberButtonActionPerformed(String number) {
        if (gui.isOn()) {
            gui.getDisplay().setText(gui.getDisplay().getText() + number);
        }
    }

    private boolean isNumberButton(String buttonText) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        for (char number : numbers) {
            if (String.valueOf(number).equals(buttonText)) {
                return true;
            }
        }
        return false;
    }
}
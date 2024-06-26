// CalculatorGUI.java
package calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display;
    private JButton onButton, offButton, clearButton, deleteButton, squareButton, noButton;
    private JButton[] numberButtons, operatorButtons;
    private boolean isOn = false;
    private ButtonActionListeners buttonActionListeners;

    public CalculatorGUI() {
        super("Calculator");
        setSize(428, 500); // Increased frame size
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        // Create a separate panel for the display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Dialog", Font.BOLD, 20));
        display.setPreferredSize(new Dimension(400, 50)); // Set preferred size for display
        display.setBackground(Color.WHITE);
        display.setOpaque(true);
        display.setEditable(false); // Set editable to false initially
        displayPanel.add(display, BorderLayout.CENTER);
        getContentPane().add(displayPanel, BorderLayout.NORTH);

        // Create a separate panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        setOnButton(new JButton("ON"));
        getOnButton().setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(getOnButton());

        setOffButton(new JButton("OFF"));
        getOffButton().setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(getOffButton());

        clearButton = new JButton("C");
        clearButton.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(clearButton);

        deleteButton = new JButton("DEL");
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(deleteButton);
        
        squareButton = new JButton("x²");
        squareButton.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(squareButton);
        
        noButton = new JButton("1/x");
        noButton.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(noButton);
       

        buttonPanel.add(topPanel, BorderLayout.NORTH);

        JPanel numberPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        numberButtons = new JButton[12];
        char[] numbers = {'7', '8', '9', '4', '5', '6', '1', '2', '3', '0', '.', '='};
        for (int i = 0; i < 12; i++) {
            numberButtons[i] = new JButton(String.valueOf(numbers[i]));
            numberButtons[i].setFont(new Font("Dialog", Font.BOLD, 20));
            numberButtons[i].setPreferredSize(new Dimension(80, 40)); // Increased button size
            numberPanel.add(numberButtons[i]);
        }

        buttonPanel.add(numberPanel, BorderLayout.CENTER);

        JPanel operatorPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        operatorButtons = new JButton[] {
                new JButton("/"),
                new JButton("X"),
                new JButton("-"),
                new JButton("+")
        };

        for (JButton button : operatorButtons) {
            button.setFont(new Font("Dialog", Font.BOLD, 20));
            operatorPanel.add(button);
        }

        buttonPanel.add(operatorPanel, BorderLayout.EAST);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
        getOnButton().setEnabled(true);
        getOffButton().setEnabled(false);

        // Add action listeners to buttons
        buttonActionListeners = new ButtonActionListeners(this);
        for (JButton button : numberButtons) {
            button.addActionListener(buttonActionListeners);
        }
        for (JButton button : operatorButtons) {
            button.addActionListener(buttonActionListeners);
        }
        getOnButton().addActionListener(buttonActionListeners);
        getOffButton().addActionListener(buttonActionListeners);
        clearButton.addActionListener(buttonActionListeners);
        deleteButton.addActionListener(buttonActionListeners);
        squareButton.addActionListener(buttonActionListeners);
        noButton.addActionListener(buttonActionListeners);
    }

    public JTextField getDisplay() {
        return display;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalculatorGUI gui = new CalculatorGUI();
                gui.setVisible(true);
            }
        });
    }

	public JButton getOnButton() {
		return onButton;
	}

	public void setOnButton(JButton onButton) {
		this.onButton = onButton;
	}

	public JButton getOffButton() {
		return offButton;
	}

	public void setOffButton(JButton offButton) {
		this.offButton = offButton;
	}
}
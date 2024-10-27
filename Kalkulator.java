import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public Kalkulator() {
        // Set up frame
        setTitle("Aplikasi Kalkulator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Panel to hold the input field with pink background (as outer padding)
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 20, 360, 70);  // Larger bounds to make the outer pink background visible
        inputPanel.setBackground(new Color(255, 182, 193));  // Light pink background (outer layer)
        inputPanel.setLayout(null);  // We'll place the JTextField manually inside this panel
        add(inputPanel);

        // Input field with white background (inside the pink panel)
        inputField = new JTextField();
        inputField.setBounds(10, 10, 340, 50);  // Inner bounds to fit inside the pink panel
        inputField.setFont(new Font("Arial", Font.BOLD, 20));
        inputField.setBackground(Color.WHITE);  // White input field background
        inputField.setEditable(false);
        inputPanel.add(inputField);  // Add input field to the pink panel

        // Button labels
        String[] buttonLabels = {
                "8", "7", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                ".", "0", "C", "/",
                "B", "E", "=", "%"
        };

        // Create buttons with color and add them
        int x = 30, y = 120;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 70, 50);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setBackground(Color.LIGHT_GRAY);
            button.addActionListener(this);

            // Make "=" button stand out with different color
            if (buttonLabels[i].equals("=")) {
                button.setBackground(new Color(173, 216, 230));  // Light blue color
            }

            add(button);
            x += 90;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("C")) {
            inputField.setText("");
        } else if (command.equals("B")) {
            String text = inputField.getText();
            inputField.setText(text.isEmpty() ? "" : text.substring(0, text.length() - 1));
        } else if (command.equals("E")) {
            System.exit(0);
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
                case '%' -> result = num1 % num2;
            }
            inputField.setText(String.valueOf(result));
            num1 = result;
        } else {
            if (!inputField.getText().isEmpty()) {
                num1 = Double.parseDouble(inputField.getText());
                operator = command.charAt(0);
                inputField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new Kalkulator();
    }
}
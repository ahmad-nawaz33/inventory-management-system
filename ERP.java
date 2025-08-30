import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ERP extends JFrame {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Login");
        frame.setSize(270, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Background color
        Color lightBackground = new Color(245, 245, 220); 
        frame.getContentPane().setBackground(lightBackground);

        // Button color
        Color brown = new Color(181, 101, 29);

        // Create components
        JLabel label = new JLabel("Username:");
        JTextField field = new JTextField(15);

        JLabel label2 = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton button = new JButton("Login");
        button.setBackground(brown);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        JLabel outputLabel = new JLabel();
        outputLabel.setForeground(Color.RED);

        // Action listener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = field.getText();
                String password = new String(passwordField.getPassword());

                if (name.equalsIgnoreCase("kat") && password.equals("123")) {
                    frame.dispose();
                    new OptionsWindow();
                } else {
                    outputLabel.setText("Invalid Credentials");
                }
            }
        });

        // Add components to frame
        frame.add(label);
        frame.add(field);
        frame.add(label2);
        frame.add(passwordField);
        frame.add(button);
        frame.add(outputLabel);

        // Show frame
        frame.setVisible(true);
    }
}
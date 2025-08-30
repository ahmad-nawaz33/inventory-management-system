import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionsWindow extends JFrame {
    public OptionsWindow() {
        super("Options");

        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        JLabel label = new JLabel("Select an option:");
        label.setFont(new Font("Arial", Font.BOLD, 13));

        JButton invent = new JButton("Inventory");
        JButton sale = new JButton("Sales");
        JButton purchase = new JButton("Purchase");

        // Style buttons
        JButton[] buttons = { invent, sale, purchase };
        for (JButton btn : buttons) {
            btn.setBackground(brown);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(100, 30));
        }

        // Set layout and background
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(beige);
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(label);
        buttonPanel.add(invent);
        buttonPanel.add(sale);
        buttonPanel.add(purchase);

        // Image
        ImageIcon icon = new ImageIcon("kat.jpg");
        Image newImg = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(newImg));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));  // Add top margin

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(beige);
        mainPanel.add(imageLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Button listeners
        invent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Inventory window = new Inventory();
                window.setSize(1000, 400);
            }
        });

        sale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Sales window = new Sales();
                window.setSize(1200, 400);
            }
        });

        purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Purchase window = new Purchase();
                window.setSize(1000, 400);
            }
        });

        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Purchase extends JFrame {
    public Purchase() {
        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        JButton purchase = new JButton("History");
        JButton suppliers = new JButton("Suppliers");
        JButton back = new JButton("Back");

        // Style buttons
        JButton[] buttons = {purchase, suppliers, back};
        for (JButton btn : buttons) {
            btn.setBackground(brown);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(120, 30));
        }

        // Set layout and background
        setLayout(new FlowLayout());
        getContentPane().setBackground(beige);

        add(purchase);
        add(suppliers);
        add(back);

        purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                History window = new History();
                window.setSize(1000, 600);
            }
        });

        suppliers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Suppliers window = new Suppliers();
                window.setSize(1000, 400);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose(); // Close current window
                new OptionsWindow(); // Open OptionsWindow
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


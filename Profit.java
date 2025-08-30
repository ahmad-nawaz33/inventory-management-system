import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class Profit extends JFrame {
    public Profit() {
        super("Profit");

        // Define custom colors
        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        // Table data and model
        String[] cols = {"Time", "Profit"};
        Object[][] data = {
            {"Weekly", "$1000"},
            {"Monthly", "$10,000"},
            {"Annual", "$100,000"},
        };

        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model);
        table.setBackground(Color.WHITE);  // Optional for consistency
        JScrollPane scroll = new JScrollPane(table);

        // Row sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Back button styling
        JButton back = new JButton("Back");
        back.setBackground(brown);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);

        // Panel setup
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(beige);
        bottomPanel.add(back);

        // Set background of main content
        getContentPane().setBackground(beige);

        // Button action
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new Sales(); // Navigate back to Sales window
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
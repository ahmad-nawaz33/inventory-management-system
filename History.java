import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class History extends JFrame {
    public History() {
        super("Purchases");

        // Set background color
        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        String[] cols = { "Product", "Quantity", "Paid" };
        Object[][] data = {
            {"Benches", 100, "Yes"},
            {"Book shelfs", 25, "No"},
            {"Cabnets", 75, "Yes"},
            {"Counter", 40, "No"},
            {"Desks", 80, "Yes"},
            {"Lab Stools", 60, "No"},
            {"Meeting tables", 30, "Yes"},
            {"Office chairs", 100, "No"},
            {"Sofa", 50, "Yes"},
            {"Student chairs", 100, "No"}
        };

        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Search panel
        JButton search = new JButton("Search");
        JTextField searchfield = new JTextField(20);
        search.setBackground(brown);
        search.setForeground(Color.WHITE);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(beige);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchfield);
        searchPanel.add(search);

        // Add panel
        JButton add = new JButton("Add data");
        JTextField productfield = new JTextField(10);
        JTextField quantityfield = new JTextField(10);
        JTextField paidfield = new JTextField(10);
        add.setBackground(brown);
        add.setForeground(Color.WHITE);

        JPanel addpanel = new JPanel(new FlowLayout());
        addpanel.setBackground(beige);
        addpanel.add(new JLabel("Product"));
        addpanel.add(productfield);
        addpanel.add(new JLabel("Quantity"));
        addpanel.add(quantityfield);
        addpanel.add(new JLabel("Paid"));
        addpanel.add(paidfield);
        addpanel.add(add);

        // Bottom panel
        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        back.setBackground(brown);
        back.setForeground(Color.WHITE);
        delete.setBackground(brown);
        delete.setForeground(Color.WHITE);

        JPanel bottompanel = new JPanel(new FlowLayout());
        bottompanel.setBackground(beige);
        bottompanel.add(back);
        bottompanel.add(delete);

        // Action Listeners
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose(); // Close current window
                new Purchase(); // Assuming Purchase class exists
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    model.removeRow(row);
                }
            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = searchfield.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String P = productfield.getText().trim();
                String Q = quantityfield.getText().trim();
                String Pa = paidfield.getText().trim();

                if (P.isEmpty() || Q.isEmpty() || Pa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields: ( Product, Quantity, and Paid. )", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                model.addRow(new Object[] { P, Q, Pa });

                try {
                    FileWriter writer = new FileWriter("data.csv", true);
                    writer.write(P + "," + Q + "," + Pa + "\n");
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                productfield.setText("");
                quantityfield.setText("");
                paidfield.setText("");
            }
        });

        // Frame layout
        setLayout(new BorderLayout());
        getContentPane().setBackground(beige);
        add(scroll, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(3, 1));
        southPanel.add(searchPanel);
        southPanel.add(addpanel);
        southPanel.add(bottompanel);
        southPanel.setBackground(beige);
        add(southPanel, BorderLayout.SOUTH);

        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

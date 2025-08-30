import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class Inventory extends JFrame {
    public Inventory() {
        super("Inventory");

        // Changed colors only
        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        String[] cols = { "Item", "Quantity", "Price" };
        Object[][] data = {
            {"Benches", 100, "$300"},
            {"Book shelfs", 25 , "$750"},
            {"Cabnets", 75, "$800"},
            {"Counter", 40, "$400"},
            {"Desks", 80, "$600"},
            {"Lab Stools", 60, "$100"},
            {"Meeting tables", 30, "$750"},
            {"Office chairs", 100, "$250"},
            {"Sofa", 50, "$500"},
            {"Student chairs", 100, "$200"}
        };

        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model); 
        JScrollPane scroll = new JScrollPane(table);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton addBtn = new JButton("Add data");

        JTextField searchField = new JTextField(20);
        JTextField itemField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JTextField priceField = new JTextField(10);

        // Style buttons
        JButton[] buttons = { back, delete, searchBtn, addBtn };
        for (JButton btn : buttons) {
            btn.setBackground(brown);
            btn.setForeground(Color.WHITE);
        }

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(beige);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        // Add Panel
        JPanel addPanel = new JPanel(new FlowLayout());
        addPanel.setBackground(beige);
        addPanel.add(new JLabel("Item:"));
        addPanel.add(itemField);
        addPanel.add(new JLabel("Quantity:"));
        addPanel.add(quantityField);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(priceField);
        addPanel.add(addBtn);

        // Bottom panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(beige);
        controlPanel.add(back);
        controlPanel.add(delete);

        // Combine panels
        JPanel southPanel = new JPanel(new GridLayout(3, 1));
        southPanel.setBackground(beige);
        southPanel.add(searchPanel);
        southPanel.add(addPanel);
        southPanel.add(controlPanel);

        // Listeners
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new OptionsWindow(); // Ensure this class exists
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

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String item = itemField.getText().trim();
                String qty = quantityField.getText().trim();
                String price = priceField.getText().trim();

                if (item.isEmpty() || qty.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields: ( Item, Quantity, and Price. )", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    model.addRow(new Object[] { item, qty, price });

                    try {
                        FileWriter writer = new FileWriter("data.csv", true);
                        writer.write(item + "," + qty + "," + price + "\n");
                        writer.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    itemField.setText("");
                    quantityField.setText("");
                    priceField.setText("");
                }
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        getContentPane().setBackground(beige);
        add(scroll, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

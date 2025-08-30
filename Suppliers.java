import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Suppliers extends JFrame {

    public Suppliers() {
        super("Suppliers Record");

        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        String[] cols = {"Name", "Address", "Contact"};
        Object[][] data = {
            {"Sofa Supplier", "123 Street", "555-500"},
            {"Office Chairs Co.", "456 Avenue", "555-250"},
            {"Student Chairs Ltd.", "789 Road", "555-200"},
            {"Lab Stools Inc.", "101 Blvd", "555-100"},
            {"Book Shelfs LLC", "202 Drive", "555-750"},
            {"Cabinets & More", "303 Path", "555-800"},
            {"Desks Galore", "404 Lane", "555-600"},
            {"Benches Plus", "505 Terrace", "555-300"},
            {"Meeting Tables Corp.", "606 Court", "555-750"},
            {"Counter Suppliers", "707 Circle", "555-400"}
        };

        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        JButton search = new JButton("Search");
        JButton add = new JButton("Add data");

        JTextField searchField = new JTextField(15);
        JTextField nameField = new JTextField(10);
        JTextField addressField = new JTextField(10);
        JTextField contactField = new JTextField(10);

        // Button styling
        JButton[] buttons = {back, delete, search, add};
        for (JButton btn : buttons) {
            btn.setBackground(brown);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(beige);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(search);

        // Add data panel
        JPanel addPanel = new JPanel(new FlowLayout());
        addPanel.setBackground(beige);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(nameField);
        addPanel.add(new JLabel("Address:"));
        addPanel.add(addressField);
        addPanel.add(new JLabel("Contact:"));
        addPanel.add(contactField);
        addPanel.add(add);

        // Control panel
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(beige);
        actionPanel.add(back);
        actionPanel.add(delete);

        // Combined control panels
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        controls.setBackground(beige);
        controls.add(searchPanel);
        controls.add(addPanel);
        controls.add(actionPanel);

        // Action listeners
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new Purchase();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    model.removeRow(table.convertRowIndexToModel(row));
                }
            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String N = nameField.getText().trim();
                String A = addressField.getText().trim();
                String C = contactField.getText().trim();

                if (N.isEmpty() || A.isEmpty() || C.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields: ( Name, Address, and Contact. )", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                model.addRow(new Object[]{N, A, C});

                try {
                    FileWriter writer = new FileWriter("data.csv", true);
                    writer.write(N + "," + A + "," + C + "\n");
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                nameField.setText("");
                addressField.setText("");
                contactField.setText("");
            }
        });

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);

        getContentPane().setBackground(beige);
        setSize(750, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


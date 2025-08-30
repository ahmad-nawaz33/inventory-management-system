import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Sales extends JFrame {
    public Sales() {

        super("Sales");

        Color beige = new Color(245, 245, 220);
        Color brown = new Color(181, 101, 29);

        String[] cols = {"Item", "Quantity", "Date", "Sold"};
        Object[][] data = {
            {"Benches", 100, "10/5/25", "$300"},
            {"Book shelfs", 25, "12/5/25", "$750"},
            {"Cabnets", 75, "13/5/25", "$800"},
            {"Counter", 40, "14/5/25", "$400"},
            {"Desks", 80, "15/5/25", "$600"},
            {"Lab Stools", 60, "16/5/25", "$100"},
            {"Meeting tables", 30, "17/5/25", "$750"},
            {"Office chairs", 100, "18/5/25", "$250"},
            {"Sofa", 50, "19/5/25", "$500"},
            {"Student chairs", 100, "20/5/25", "$200"}
        };

        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        JButton profit = new JButton("Profit");
        JButton search = new JButton("Search");
        JTextField searchbar = new JTextField(20);
        JButton addbutton = new JButton("Add data");

        // Style buttons
        JButton[] buttons = {back, delete, profit, search, addbutton};
        for (JButton btn : buttons) {
            btn.setBackground(brown);
            btn.setForeground(Color.WHITE);
        }

        JPanel searchpanel = new JPanel(new FlowLayout());
        searchpanel.setBackground(beige);
        searchpanel.add(new JLabel("Search:"));
        searchpanel.add(searchbar);
        searchpanel.add(search);

        JPanel addpanel = new JPanel(new FlowLayout());
        addpanel.setBackground(beige);

        JTextField itemfield = new JTextField(10);
        JTextField quantityfield = new JTextField(10);
        JTextField datefield = new JTextField(10);
        JTextField soldfield = new JTextField(10);

        addpanel.add(new JLabel("Item:"));
        addpanel.add(itemfield);
        addpanel.add(new JLabel("Quantity:"));
        addpanel.add(quantityfield);
        addpanel.add(new JLabel("Date:"));
        addpanel.add(datefield);
        addpanel.add(new JLabel("Sold:"));
        addpanel.add(soldfield);
        addpanel.add(addbutton);

        JPanel bottompanel = new JPanel(new FlowLayout());
        bottompanel.setBackground(beige);
        bottompanel.add(back);
        bottompanel.add(delete);
        bottompanel.add(profit);

        JPanel southPanel = new JPanel(new GridLayout(3, 1));
        southPanel.setBackground(beige);
        southPanel.add(searchpanel);
        southPanel.add(addpanel);
        southPanel.add(bottompanel);

        getContentPane().setBackground(beige);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new OptionsWindow();
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

        profit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Profit window = new Profit();
                window.setSize(600, 600);
            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = searchbar.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        addbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String I = itemfield.getText().trim();
                String Q = quantityfield.getText().trim();
                String D = datefield.getText().trim();
                String S = soldfield.getText().trim();

                if (I.isEmpty() || Q.isEmpty() || D.isEmpty() || S.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields: ( Item, Quantity, Date, and Sold. )", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                model.addRow(new Object[]{I, Q, D, S});

                try {
                    FileWriter writer = new FileWriter("data.csv", true);
                    writer.write(I + "," + Q + "," + D + "," + S + "\n");
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                itemfield.setText("");
                quantityfield.setText("");
                datefield.setText("");
                soldfield.setText("");
            }
        });

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


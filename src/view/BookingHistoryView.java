package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingHistoryView extends JFrame {

    JTable table;

    public BookingHistoryView() {
        setTitle("SportEase - Booking History");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Booking ID", "Venue Name", "Date"};
        Object[][] data = {
                {1, "Football Ground A", "2025-01-10"},
                {2, "Basketball Court B", "2025-01-12"},
                {3, "Cricket Stadium C", "2025-01-15"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}

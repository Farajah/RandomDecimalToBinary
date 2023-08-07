import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DecimalToBinaryConverter extends JFrame {
    private final DefaultTableModel tableModel;

    public DecimalToBinaryConverter() {
        // Set up the JFrame
        super("Decimal to Binary Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 400);

        // Create the table model
        String[] columnNames = {"S/No.", "Decimal Number", "Binary Number", "Remarks"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Create the table and add it to a scroll pane
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the center of the JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Create the "Convert" button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertRandomNumbers();
            }
        });

        // Add the "Convert" button to the bottom of the JFrame
        add(convertButton, BorderLayout.SOUTH);
    }

    private void convertRandomNumbers() {
        // Clear the existing table
        tableModel.setRowCount(0);

        // Generate 50 random decimal numbers with at most three decimal points
        for (int i = 0; i < 50; i++) {
            double randomDecimal = Math.random() * 1000; // Random number between 0 and 1000
            double roundedDecimal = Math.round(randomDecimal * 1000.0) / 1000.0; // Round to three decimal points

            // Convert the decimal number to binary
            String binaryNumber = convertToBinary(roundedDecimal);

            // Determine if the binary number is exactly or approximately converted
            String remarks = isExactly(roundedDecimal, binaryNumber) ? "Exactly" : "Approximate";

            // Add the row to the table
            Object[] rowData = {i + 1, roundedDecimal, binaryNumber, remarks};
            tableModel.addRow(rowData);
        }
    }

    private String convertToBinary(double decimalNumber) {
        // Implement the logic to convert a decimal number to binary here
        StringBuilder binaryNumber = new StringBuilder();
        int integralPart = (int) decimalNumber;
        double fractionalPart = decimalNumber - integralPart;

        // Convert the integral part to binary
        while (integralPart > 0) {
            binaryNumber.insert(0, integralPart % 2);
            integralPart /= 2;
        }

        // Add the binary point
        binaryNumber.append(".");

        // Convert the fractional part to binary with up to three decimal points
        for (int i = 0; i < 3; i++) {
            fractionalPart *= 2;
            int bit = (int) fractionalPart;
            binaryNumber.append(bit);
            fractionalPart -= bit;
        }

        return binaryNumber.toString();
    }

    private boolean isExactly(double decimalNumber, String binaryNumber) {
        // Convert the binary number back to decimal and compare with the original decimal number
        double convertedBack = Double.parseDouble(binaryNumber);
        return decimalNumber == convertedBack;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DecimalToBinaryConverter().setVisible(true);
            }
        });
    }
}

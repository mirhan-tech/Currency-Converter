import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {

    
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;
    private JLabel rateLabel;
    private JLabel formulaLabel;

    
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(550, 350); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        exchangeRates = new HashMap<>();
        exchangeRates.put("INR", 1.0);
        exchangeRates.put("USD", 0.012);  
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("JPY", 1.6);
        exchangeRates.put("GBP", 0.009);
        exchangeRates.put("AUD", 0.017);
        exchangeRates.put("CAD", 0.016);
        exchangeRates.put("CHF", 0.011);
        exchangeRates.put("CNY", 0.085);
        exchangeRates.put("SEK", 0.12);
        exchangeRates.put("NZD", 0.018);
        exchangeRates.put("KWD", 0.0036);

        String[] currencies = exchangeRates.keySet().toArray(new String[0]);

        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(currencies);

        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(currencies);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Converted Amount: ");
        rateLabel = new JLabel("Exchange Rate: ");
        formulaLabel = new JLabel("Conversion Formula: ");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.EAST;
        add(fromLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(fromCurrency, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(toLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(toCurrency, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(amountField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(convertButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);
        gbc.gridy = 5;
        add(rateLabel, gbc);
        gbc.gridy = 6;
        add(formulaLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();
        String amountText = amountField.getText();

        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the amount to convert.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount < 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a numerical value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double rateFrom = exchangeRates.get(from);
        double rateTo = exchangeRates.get(to);
        double convertedAmount = amount * (rateTo / rateFrom);
        resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        rateLabel.setText(String.format("Exchange Rate: 1 %s = %.4f %s", from, rateTo / rateFrom, to));
        formulaLabel.setText(String.format("Formula: %.2f * (%.4f / %.4f)", amount, rateTo, rateFrom));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}

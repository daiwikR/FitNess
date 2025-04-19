package fitnesstracker.view;

import fitnesstracker.model.BMICalculator;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BMICalculatorView extends JPanel {
    private JTextField heightField;
    private JTextField weightField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JLabel categoryLabel;
    private JComboBox<String> unitComboBox;
    
    private final DecimalFormat df = new DecimalFormat("#.##");

    public BMICalculatorView() {
        initComponents();
        layoutComponents();
        registerEvents();
    }

    private void initComponents() {
        heightField = new JTextField(10);
        weightField = new JTextField(10);
        calculateButton = new JButton("Calculate BMI");
        resultLabel = new JLabel("BMI: ");
        categoryLabel = new JLabel("Category: ");
        
        // Font styles
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Unit selection
        String[] units = {"Metric (m, kg)", "Imperial (in, lbs)"};
        unitComboBox = new JComboBox<>(units);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel.add(new JLabel("Units:"));
        inputPanel.add(unitComboBox);
        
        inputPanel.add(new JLabel("Height:"));
        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        heightPanel.add(heightField);
        heightPanel.add(new JLabel("(m or in)"));
        inputPanel.add(heightPanel);
        
        inputPanel.add(new JLabel("Weight:"));
        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        weightPanel.add(weightField);
        weightPanel.add(new JLabel("(kg or lbs)"));
        inputPanel.add(weightPanel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calculateButton);
        
        // Result panel
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultPanel.add(resultLabel);
        resultPanel.add(categoryLabel);
        
        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
    }

    private void registerEvents() {
        calculateButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                
                // Convert if using imperial units
                if (unitComboBox.getSelectedIndex() == 1) {
                    // Convert inches to meters
                    height = height * 0.0254;
                    // Convert pounds to kilograms
                    weight = weight * 0.45359237;
                }
                
                if (height <= 0 || weight <= 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Height and weight must be positive values.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                double bmi = BMICalculator.calculateBMI(height, weight);
                String category = BMICalculator.getBMICategory(bmi);
                
                resultLabel.setText("BMI: " + df.format(bmi));
                categoryLabel.setText("Category: " + category);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter valid numbers for height and weight.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
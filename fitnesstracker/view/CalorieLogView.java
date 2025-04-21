package fitnesstracker.view;

import fitnesstracker.controller.CalorieController;
import fitnesstracker.model.CalorieLog;
import fitnesstracker.model.CalorieLog.MealEntry;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalorieLogView extends JPanel {
    private CalorieController controller;
    private DefaultListModel<MealEntry> mealListModel;
    private JList<MealEntry> mealJList;
    private JTextField mealNameField;
    private JTextField caloriesField;
    private JButton addButton;
    private JButton removeButton;
    private JLabel totalCaloriesLabel;

    public CalorieLogView() {
        controller = new CalorieController(new CalorieLog());
        initComponents();
        layoutComponents();
        registerEvents();
        updateTotalCalories();
    }

    private void initComponents() {
        mealListModel = new DefaultListModel<>();
        mealJList = new JList<>(mealListModel);
        mealJList.setBorder(BorderFactory.createTitledBorder("Meal Entries"));
        
        mealNameField = new JTextField(15);
        caloriesField = new JTextField(8);
        addButton = new JButton("Add Meal");
        removeButton = new JButton("Remove Selected");
        totalCaloriesLabel = new JLabel("Total Calories: 0");
        totalCaloriesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Add initial data if any
        for (MealEntry entry : controller.getMealEntries()) {
            mealListModel.addElement(entry);
        }
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Meal Name:"));
        inputPanel.add(mealNameField);
        inputPanel.add(new JLabel("Calories:"));
        inputPanel.add(caloriesField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        
        // Total calories panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(totalCaloriesLabel);
        
        // Combine input and total in the top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.WEST);
        topPanel.add(totalPanel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(mealJList), BorderLayout.CENTER);
    }

    private void registerEvents() {
        addButton.addActionListener(e -> {
            String mealName = mealNameField.getText().trim();
            String caloriesStr = caloriesField.getText().trim();
            
            if (mealName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a meal name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int calories = Integer.parseInt(caloriesStr);
                if (calories < 0) {
                    JOptionPane.showMessageDialog(this, "Calories cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Add meal to the controller
                controller.addMeal(mealName, calories);
                
                // Update the list
                refreshMealList();
                
                // Clear inputs
                mealNameField.setText("");
                caloriesField.setText("");
                
                updateTotalCalories();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for calories.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        removeButton.addActionListener(e -> {
            MealEntry selected = mealJList.getSelectedValue();
            if (selected != null) {
                controller.removeMeal(selected);
                refreshMealList();
                updateTotalCalories();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a meal to remove.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void refreshMealList() {
        mealListModel.clear();
        for (MealEntry entry : controller.getMealEntries()) {
            mealListModel.addElement(entry);
        }
    }
    
    private void updateTotalCalories() {
        int total = controller.getTotalCalories();
        totalCaloriesLabel.setText("Total Calories: " + total);
    }
}
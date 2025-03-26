package fitnesstracker.view;

import fitnesstracker.model.Reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class NotificationPanel extends JPanel {
    private DefaultListModel<Reminder> reminderListModel;
    private JList<Reminder> reminderJList;
    private JTextField messageField;
    private JTextField reminderDateTimeField;
    private JButton addReminderButton;
    private JButton removeReminderButton;
    private List<Reminder> reminders;

    public NotificationPanel() {
        this.reminders = new ArrayList<>();
        initComponents();
        layoutComponents();
        registerEvents();
    }
    
    public NotificationPanel(List<Reminder> reminders) {
        this.reminders = reminders;
        initComponents();
        layoutComponents();
        registerEvents();
    }
    
    private void initComponents() {
        reminderListModel = new DefaultListModel<>();
        for (Reminder r : reminders) {
            reminderListModel.addElement(r);
        }
        reminderJList = new JList<>(reminderListModel);
        messageField = new JTextField(10);
        reminderDateTimeField = new JTextField(15);
        addReminderButton = new JButton("Add Reminder");
        removeReminderButton = new JButton("Remove Selected");
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Message:"));
        inputPanel.add(messageField);
        inputPanel.add(new JLabel("DateTime (YYYY-MM-DDTHH:MM):"));
        inputPanel.add(reminderDateTimeField);
        inputPanel.add(addReminderButton);
        inputPanel.add(removeReminderButton);
        add(new JScrollPane(reminderJList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
    
    private void registerEvents() {
        addReminderButton.addActionListener(e -> {
            String msg = messageField.getText().trim();
            String dt = reminderDateTimeField.getText().trim();
            if (msg.isEmpty() || dt.isEmpty()) {
                JOptionPane.showMessageDialog(NotificationPanel.this,
                    "Enter a message and valid date/time.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                LocalDateTime reminderTime = LocalDateTime.parse(dt);
                Reminder newReminder = new Reminder(msg, reminderTime);
                reminders.add(newReminder);
                reminderListModel.addElement(newReminder);
                messageField.setText("");
                reminderDateTimeField.setText("");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(NotificationPanel.this,
                    "Invalid format. Use YYYY-MM-DDTHH:MM", "Parsing Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        removeReminderButton.addActionListener(e -> {
            Reminder selected = reminderJList.getSelectedValue();
            if (selected != null) {
                reminders.remove(selected);
                reminderListModel.removeElement(selected);
            } else {
                JOptionPane.showMessageDialog(NotificationPanel.this, "Select a reminder.");
            }
        });
    }
    
    public List<Reminder> getReminders() {
        return reminders;
    }
}

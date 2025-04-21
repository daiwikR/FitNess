package fitnesstracker.view;

import fitnesstracker.model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleView extends JPanel {

    private DefaultListModel<Schedule> scheduleListModel;
    private JList<Schedule> scheduleJList;

    private JTextField dateTimeField; // user enters "YYYY-MM-DDTHH:MM"
    private JButton addScheduleButton;
    private JButton removeScheduleButton;

    private List<Schedule> schedules;

    public ScheduleView() {
        this.schedules = new ArrayList<>();
        initComponents();
        layoutComponents();
        registerEvents();
    }

    public ScheduleView(List<Schedule> schedules) {
        this.schedules = schedules;
        initComponents();
        layoutComponents();
        registerEvents();
    }

    private void initComponents() {
        scheduleListModel = new DefaultListModel<>();
        for (Schedule s : schedules) {
            scheduleListModel.addElement(s);
        }

        scheduleJList = new JList<>(scheduleListModel);

        dateTimeField = new JTextField("2025-03-30T09:00", 15);
        addScheduleButton = new JButton("Add Schedule");
        removeScheduleButton = new JButton("Remove Selected");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Input panel for date/time
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Date/Time (YYYY-MM-DDTHH:MM):"));
        inputPanel.add(dateTimeField);
        inputPanel.add(addScheduleButton);
        inputPanel.add(removeScheduleButton);

        add(new JScrollPane(scheduleJList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void registerEvents() {
        addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTimeStr = dateTimeField.getText().trim();
                try {
                    LocalDateTime ldt = LocalDateTime.parse(dateTimeStr);
                    Schedule newSchedule = new Schedule(ldt);
                    schedules.add(newSchedule);
                    scheduleListModel.addElement(newSchedule);

                    // Clear the field or reset it
                    dateTimeField.setText("");
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(
                        ScheduleView.this,
                        "Invalid date/time format. Please use YYYY-MM-DDTHH:MM",
                        "Parsing Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        removeScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Schedule selected = scheduleJList.getSelectedValue();
                if (selected != null) {
                    schedules.remove(selected);
                    scheduleListModel.removeElement(selected);
                } else {
                    JOptionPane.showMessageDialog(
                        ScheduleView.this,
                        "No schedule selected.",
                        "Selection Error",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }
}

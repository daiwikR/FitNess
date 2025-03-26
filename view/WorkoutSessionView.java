package fitnesstracker.view;

import fitnesstracker.controller.WorkoutSessionController;
import fitnesstracker.model.Exercise;
import fitnesstracker.model.SetCounter;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.model.WorkoutSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class WorkoutSessionView extends JPanel {
    private WorkoutSessionController sessionController;
    private JButton startButton, endButton;
    private JButton incSetButton, decSetButton;
    private JButton incRepButton, decRepButton;

    // List for selecting an exercise
    private DefaultListModel<Exercise> exerciseListModel;
    private JList<Exercise> exerciseJList;

    // List to display progress (sets/reps)
    private DefaultListModel<String> progressListModel;
    private JList<String> progressList;

    public WorkoutSessionView(WorkoutPlan workoutPlan) {
        sessionController = new WorkoutSessionController(workoutPlan);
        initComponents();
        layoutComponents();
        registerEvents();
    }

    private void initComponents() {
        startButton = new JButton("Start Workout");
        endButton = new JButton("End Workout");
        incSetButton = new JButton("Increment Set");
        decSetButton = new JButton("Decrement Set");
        incRepButton = new JButton("Increment Rep");
        decRepButton = new JButton("Decrement Rep");

        // Populate exercise list from the session progress keys
        exerciseListModel = new DefaultListModel<>();
        for (Exercise e : sessionController.getSession().getProgress().keySet()) {
            exerciseListModel.addElement(e);
        }
        exerciseJList = new JList<>(exerciseListModel);
        exerciseJList.setBorder(BorderFactory.createTitledBorder("Exercises"));

        progressListModel = new DefaultListModel<>();
        progressList = new JList<>(progressListModel);
        progressList.setBorder(BorderFactory.createTitledBorder("Progress"));
        updateProgressList();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.add(startButton);
        topPanel.add(endButton);
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(exerciseJList));
        centerPanel.add(new JScrollPane(progressList));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(incSetButton);
        bottomPanel.add(decSetButton);
        bottomPanel.add(incRepButton);
        bottomPanel.add(decRepButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void registerEvents() {
        startButton.addActionListener(e -> {
            sessionController.startWorkout();
            JOptionPane.showMessageDialog(WorkoutSessionView.this, "Workout started!");
        });

        endButton.addActionListener(e -> {
            sessionController.endWorkout();
            JOptionPane.showMessageDialog(WorkoutSessionView.this, "Workout ended!");
            updateProgressList();
        });

        incSetButton.addActionListener(e -> {
            Exercise selected = exerciseJList.getSelectedValue();
            if (selected != null) {
                sessionController.incrementSet(selected);
                updateProgressList();
            } else {
                JOptionPane.showMessageDialog(WorkoutSessionView.this, "Select an exercise.");
            }
        });
        
        decSetButton.addActionListener(e -> {
            Exercise selected = exerciseJList.getSelectedValue();
            if (selected != null) {
                sessionController.decrementSet(selected);
                updateProgressList();
            } else {
                JOptionPane.showMessageDialog(WorkoutSessionView.this, "Select an exercise.");
            }
        });

        incRepButton.addActionListener(e -> {
            Exercise selected = exerciseJList.getSelectedValue();
            if (selected != null) {
                sessionController.incrementRep(selected);
                updateProgressList();
            } else {
                JOptionPane.showMessageDialog(WorkoutSessionView.this, "Select an exercise.");
            }
        });
        
        decRepButton.addActionListener(e -> {
            Exercise selected = exerciseJList.getSelectedValue();
            if (selected != null) {
                sessionController.decrementRep(selected);
                updateProgressList();
            } else {
                JOptionPane.showMessageDialog(WorkoutSessionView.this, "Select an exercise.");
            }
        });
    }

    public void updateProgressList() {
        progressListModel.clear();
        WorkoutSession session = sessionController.getSession();
        for (Map.Entry<Exercise, SetCounter> entry : session.getProgress().entrySet()) {
            Exercise exercise = entry.getKey();
            SetCounter counter = entry.getValue();
            progressListModel.addElement(exercise.getName() + " - " + counter.toString());
        }
    }
    
    // Expose the session (used by the graph panel)
    public WorkoutSession getSession() {
        return sessionController.getSession();
    }
}

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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class WorkoutSessionView extends JPanel {
    private WorkoutSessionController sessionController;
    private JButton startButton, endButton;
    private JButton incSetButton, decSetButton;
    private JButton incRepButton, decRepButton;

    // Timer components
    private JLabel timerLabel;
    private Timer swingTimer;
    private LocalDateTime timerStartTime;
    private boolean timerRunning = false;

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
        
        // Initialize timer label
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Initialize the Swing Timer (updates every second)
        swingTimer = new Timer(1000, e -> updateTimer());

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
        
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(endButton);
        topPanel.add(buttonPanel, BorderLayout.NORTH);
        
        // Add timer to top panel
        topPanel.add(timerLabel, BorderLayout.CENTER);
        
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
            startTimer();
            JOptionPane.showMessageDialog(WorkoutSessionView.this, "Workout started!");
        });

        endButton.addActionListener(e -> {
            sessionController.endWorkout();
            stopTimer();
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
    
    /**
     * Starts the workout timer
     */
    private void startTimer() {
        timerStartTime = LocalDateTime.now();
        timerRunning = true;
        swingTimer.start();
    }
    
    /**
     * Stops the workout timer
     */
    private void stopTimer() {
        timerRunning = false;
        swingTimer.stop();
    }
    
    /**
     * Updates the timer display every second
     */
    private void updateTimer() {
        if (timerRunning) {
            Duration duration = Duration.between(timerStartTime, LocalDateTime.now());
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            
            timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        }
    }

    public void refreshExerciseList() {
        // Store currently selected exercise if any
        Exercise selectedExercise = exerciseJList.getSelectedValue();
        
        // Clear and repopulate the exercise list
        exerciseListModel.clear();
        for (Exercise e : sessionController.getSession().getProgress().keySet()) {
            exerciseListModel.addElement(e);
        }
        
        // Restore selection if the exercise still exists
        if (selectedExercise != null && exerciseListModel.contains(selectedExercise)) {
            exerciseJList.setSelectedValue(selectedExercise, true);
        }
        
        // Update the progress list as well
        updateProgressList();
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

    public WorkoutSessionController getSessionController() {
    return sessionController;
}
}
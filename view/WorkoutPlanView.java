package fitnesstracker.view;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.WorkoutPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutPlanView extends JPanel {

    private WorkoutPlan workoutPlan;
    private DefaultListModel<Exercise> exerciseListModel;
    private JList<Exercise> exerciseJList;

    private JTextField exerciseNameField;
    private JTextField exerciseDescField;
    private JTextField exerciseMuscleField;
    private JButton addButton;
    private JButton removeButton;

    public WorkoutPlanView(WorkoutPlan plan) {
        this.workoutPlan = plan;
        initComponents();
        layoutComponents();
        registerEvents();
    }

    private void initComponents() {
        exerciseListModel = new DefaultListModel<>();
        // Populate the list model with exercises from the workout plan
        for (Exercise e : workoutPlan.getExercises()) {
            exerciseListModel.addElement(e);
        }

        exerciseJList = new JList<>(exerciseListModel);

        exerciseNameField = new JTextField(10);
        exerciseDescField = new JTextField(10);
        exerciseMuscleField = new JTextField(10);

        addButton = new JButton("Add Exercise");
        removeButton = new JButton("Remove Selected");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Top panel for entering new Exercise details
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Exercise Name:"));
        inputPanel.add(exerciseNameField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(exerciseDescField);
        inputPanel.add(new JLabel("Muscle Group:"));
        inputPanel.add(exerciseMuscleField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(new JLabel("Workout Plan: " + workoutPlan.getPlanName()), BorderLayout.NORTH);
        add(new JScrollPane(exerciseJList), BorderLayout.CENTER);

        // A panel at the bottom to hold input + buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void registerEvents() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = exerciseNameField.getText().trim();
                String desc = exerciseDescField.getText().trim();
                String muscle = exerciseMuscleField.getText().trim();

                if (!name.isEmpty() && !desc.isEmpty() && !muscle.isEmpty()) {
                    Exercise newExercise = new Exercise(name, desc, muscle);
                    
                    // Add exercise to the workout plan
                    workoutPlan.addExercise(newExercise);
                    exerciseListModel.addElement(newExercise);

                    // Clear input fields
                    exerciseNameField.setText("");
                    exerciseDescField.setText("");
                    exerciseMuscleField.setText("");
                    
                    // Update any active workout sessions
                    updateActiveWorkoutSessions();
                } else {
                    JOptionPane.showMessageDialog(
                        WorkoutPlanView.this,
                        "Please fill all fields before adding an exercise.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exercise selected = exerciseJList.getSelectedValue();
                if (selected != null) {
                    workoutPlan.removeExercise(selected);
                    exerciseListModel.removeElement(selected);
                    updateActiveWorkoutSessions();
                } else {
                    JOptionPane.showMessageDialog(
                        WorkoutPlanView.this,
                        "Please select an exercise to remove.",
                        "Selection Error",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }


    private void updateActiveWorkoutSessions() {
        // Find all open frames
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {
                JFrame jframe = (JFrame) frame;
                // Look for tabbed panes containing WorkoutSessionView
                for (Component comp : jframe.getContentPane().getComponents()) {
                    if (comp instanceof JTabbedPane) {
                        JTabbedPane tabbedPane = (JTabbedPane) comp;
                        // Check each tab for WorkoutSessionView
                        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                            Component tabComp = tabbedPane.getComponentAt(i);
                            if (tabComp instanceof WorkoutSessionView) {
                                WorkoutSessionView sessionView = (WorkoutSessionView) tabComp;
                                // Check if this session is using our workout plan
                                if (sessionView.getSession().getWorkoutPlan() == workoutPlan) {
                                    // Need to add the syncWithWorkoutPlan method to WorkoutSessionController
                                    // and actually implement it in that class
                                    sessionView.getSessionController().syncWithWorkoutPlan();
                                    sessionView.refreshExerciseList();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

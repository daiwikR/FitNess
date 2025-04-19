package fitnesstracker;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.Reminder;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.model.WorkoutSession;

import fitnesstracker.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ThemeManager.setTheme(ThemeManager.LIGHT_THEME);

        SwingUtilities.invokeLater(() -> {
            // Create a sample workout plan with exercises (e.g. Push Pull Legs)
            WorkoutPlan workoutPlan = new WorkoutPlan("Push Pull Legs");
            workoutPlan.addExercise(new Exercise("Push Ups 1", "Basic chest exercise", "Chest"));
            workoutPlan.addExercise(new Exercise("Pull Ups", "Back exercise", "Back"));
            workoutPlan.addExercise(new Exercise("Squats", "Leg exercise", "Legs"));

            // Create the WorkoutSessionView
            WorkoutSessionView workoutSessionView = new WorkoutSessionView(workoutPlan);
            // Create the ProgressGraphPanel using the session from workoutSessionView
            ProgressGraphPanel progressGraphPanel = new ProgressGraphPanel(workoutSessionView.getSession());
            
            // Create the NotificationPanel with a sample reminder (for demo, reminder fires in 10 seconds)
            ArrayList<Reminder> reminders = new ArrayList<>();
            reminders.add(new Reminder("Drink Water!", LocalDateTime.now().plusSeconds(10)));
            NotificationPanel notificationPanel = new NotificationPanel(reminders);
            
            // Create the WorkoutPlanManagerView
            WorkoutPlanManagerView workoutPlanManagerView = new WorkoutPlanManagerView();

            ScheduleView scheduleView = new ScheduleView();

            CalorieLogView calorieLogView = new CalorieLogView();

            BMICalculatorView bmiCalculatorView = new BMICalculatorView();


            // Build the main window with tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Plan Manager", workoutPlanManagerView);
            tabbedPane.addTab("Workout Session", workoutSessionView);
            tabbedPane.addTab("Progress Graph", progressGraphPanel);
            tabbedPane.addTab("Reminders", notificationPanel);
            tabbedPane.addTab("Schedule Workout", scheduleView);

            tabbedPane.addTab("Calorie Log", calorieLogView);
            tabbedPane.addTab("BMI Calculator", bmiCalculatorView);
            
            // Add a change listener to the tabbed pane to update the progress graph
            tabbedPane.addChangeListener(e -> {
                // When switching to the Progress Graph tab, update it with current session data
                if (tabbedPane.getSelectedComponent() == progressGraphPanel) {
                    progressGraphPanel.setSession(workoutSessionView.getSession());
                    progressGraphPanel.refresh();
                }
            });
            
            // Also modify WorkoutPlanManagerView to update the progress graph
            workoutPlanManagerView.addPropertyChangeListener("planChanged", evt -> {
                progressGraphPanel.setSession(workoutSessionView.getSession());
                progressGraphPanel.refresh();
            });

            JFrame frame = new JFrame("Fitness Tracker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            
            
            // Create a theme toggle button
            JButton themeButton = new JButton("Dark Mode");
            themeButton.addActionListener(e -> {
                if (ThemeManager.getCurrentThemeName().equals(ThemeManager.LIGHT_THEME)) {
                    ThemeManager.setTheme(ThemeManager.DARK_THEME);
                    themeButton.setText("Light Mode");
                } else {
                    ThemeManager.setTheme(ThemeManager.LIGHT_THEME);
                    themeButton.setText("Dark Mode");
                }

            });
            
            // Add the theme toggle to a panel at the top
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(themeButton);
            
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
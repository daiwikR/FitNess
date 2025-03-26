package fitnesstracker;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.Reminder;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.model.WorkoutSession;
import fitnesstracker.view.NotificationPanel;
import fitnesstracker.view.ProgressGraphPanel;
import fitnesstracker.view.WorkoutPlanManagerView;
import fitnesstracker.view.WorkoutSessionView;
import fitnesstracker.view.WorkoutPlanView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a sample workout plan with exercises (e.g. Push Pull Legs)
            WorkoutPlan workoutPlan = new WorkoutPlan("Push Pull Legs");
            workoutPlan.addExercise(new Exercise("Push Ups", "Basic chest exercise", "Chest"));
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
            // Optionally start the notifier if you want automatic system notifications:
            // ReminderNotifier notifier = new ReminderNotifier(reminders);
            // notifier.start();
            
            // Create the WorkoutPlanManagerView
            WorkoutPlanManagerView workoutPlanManagerView = new WorkoutPlanManagerView();

            // Build the main window with tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Plan Manager", workoutPlanManagerView);
            tabbedPane.addTab("Workout Session", workoutSessionView);
            tabbedPane.addTab("Progress Graph", progressGraphPanel);
            tabbedPane.addTab("Reminders", notificationPanel);

            JFrame frame = new JFrame("Fitness Tracker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}

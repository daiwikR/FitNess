package fitnesstracker.view;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.SetCounter;
import fitnesstracker.model.WorkoutSession;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ProgressGraphPanel extends JPanel {
    private WorkoutSession session;

    public ProgressGraphPanel(WorkoutSession session) {
        this.session = session;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Map<Exercise, SetCounter> progress = session.getProgress();
        int numExercises = progress.size();
        if (numExercises == 0) return;
        int barWidth = width / (numExercises * 2);
        int i = 0;
        for (Map.Entry<Exercise, SetCounter> entry : progress.entrySet()) {
            Exercise exercise = entry.getKey();
            SetCounter counter = entry.getValue();
            int totalReps = counter.getReps(); // using reps as progress
            int barHeight = Math.min(totalReps * 5, height - 50);
            int x = (2 * i + 1) * barWidth;
            int y = height - barHeight - 30;
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, barWidth, barHeight);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, barWidth, barHeight);
            g2.drawString(exercise.getName(), x, height - 10);
            i++;
        }
    }
}

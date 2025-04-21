package fitnesstracker.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WorkoutSession {
    private WorkoutPlan workoutPlan;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<Exercise, SetCounter> progress; // maps each exercise to its counter

    public WorkoutSession(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
        this.progress = new HashMap<>();
        for (Exercise e : workoutPlan.getExercises()) {
            progress.put(e, new SetCounter());
        }
    }

    public void startSession() {
        this.startTime = LocalDateTime.now();
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Map<Exercise, SetCounter> getProgress() {
        return progress;
    }

    public void addExerciseToProgress(Exercise exercise) {
        progress.put(exercise, new SetCounter());
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }
}

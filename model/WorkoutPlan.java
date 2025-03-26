package fitnesstracker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan {
    private String planName;
    private List<Exercise> exercises;

    public WorkoutPlan(String planName) {
        this.planName = planName;
        this.exercises = new ArrayList<>();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    @Override
    public String toString() {
        return planName;
    }
}

package fitnesstracker.controller;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.WorkoutPlan;

import java.util.List;

public class WorkoutController {
    private WorkoutPlan workoutPlan;

    public WorkoutController(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public void addExercise(String name, String description, String muscleGroup) {
        Exercise exercise = new Exercise(name, description, muscleGroup);
        workoutPlan.addExercise(exercise);
    }

    public void removeExercise(Exercise exercise) {
        workoutPlan.removeExercise(exercise);
    }

    public List<Exercise> getExercises() {
        return workoutPlan.getExercises();
    }

    public String getPlanName() {
        return workoutPlan.getPlanName();
    }

    public void setPlanName(String newName) {
        workoutPlan.setPlanName(newName);
    }
}

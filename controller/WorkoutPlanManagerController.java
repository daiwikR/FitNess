package fitnesstracker.controller;

import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.model.WorkoutPlanManager;

import java.util.List;

public class WorkoutPlanManagerController {
    private WorkoutPlanManager manager;

    public WorkoutPlanManagerController() {
        manager = new WorkoutPlanManager();
    }

    public void addWorkoutPlan(String planName) {
        WorkoutPlan plan = new WorkoutPlan(planName);
        manager.addPlan(plan);
    }

    public void deleteWorkoutPlan(WorkoutPlan plan) {
        manager.removePlan(plan);
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return manager.getPlans();
    }
}

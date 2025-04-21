package fitnesstracker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanManager {
    private List<WorkoutPlan> plans;

    public WorkoutPlanManager() {
        plans = new ArrayList<>();
    }

    public void addPlan(WorkoutPlan plan) {
        plans.add(plan);
    }

    public void removePlan(WorkoutPlan plan) {
        plans.remove(plan);
    }

    public List<WorkoutPlan> getPlans() {
        return plans;
    }
}

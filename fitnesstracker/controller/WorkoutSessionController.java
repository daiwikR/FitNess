package fitnesstracker.controller;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.SetCounter;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.model.WorkoutSession;

public class WorkoutSessionController {
    private WorkoutSession session;

    public WorkoutSessionController(WorkoutPlan workoutPlan) {
        session = new WorkoutSession(workoutPlan);
    }

    public void startWorkout() {
        session.startSession();
    }

    public void endWorkout() {
        session.endSession();
    }

    public void incrementSet(Exercise exercise) {
        SetCounter counter = session.getProgress().get(exercise);
        if (counter != null) {
            counter.incrementSet();
        }
    }
    
    public void decrementSet(Exercise exercise) {
        SetCounter counter = session.getProgress().get(exercise);
        if (counter != null) {
            counter.decrementSet();
        }
    }

    public void incrementRep(Exercise exercise) {
        SetCounter counter = session.getProgress().get(exercise);
        if (counter != null) {
            counter.incrementRep();
        }
    }
    
    public void decrementRep(Exercise exercise) {
        SetCounter counter = session.getProgress().get(exercise);
        if (counter != null) {
            counter.decrementRep();
        }
    }

    public void syncWithWorkoutPlan() {
        // Get the workout plan from the session
        WorkoutPlan workoutPlan = session.getWorkoutPlan();
        
        // Add any new exercises from the plan to the session
        for (Exercise exercise : workoutPlan.getExercises()) {
            if (!session.getProgress().containsKey(exercise)) {
                session.addExerciseToProgress(exercise);
            }
        }
    }

    public void changeWorkoutPlan(WorkoutPlan newPlan) {
        this.session = new WorkoutSession(newPlan);
    }

    public WorkoutSession getSession() {
        return session;
    }
}

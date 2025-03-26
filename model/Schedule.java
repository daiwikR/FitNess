package fitnesstracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private LocalDateTime workoutTime;

    public Schedule(LocalDateTime workoutTime) {
        this.workoutTime = workoutTime;
    }

    public LocalDateTime getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(LocalDateTime workoutTime) {
        this.workoutTime = workoutTime;
    }

    public String getFormattedWorkoutTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return workoutTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Scheduled Workout at: " + getFormattedWorkoutTime();
    }
}

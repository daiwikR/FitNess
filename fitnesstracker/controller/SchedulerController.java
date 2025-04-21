package fitnesstracker.controller;

import fitnesstracker.model.Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SchedulerController {
    private List<Schedule> schedules;

    public SchedulerController() {
        this.schedules = new ArrayList<>();
    }

    public SchedulerController(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(LocalDateTime dateTime) {
        Schedule schedule = new Schedule(dateTime);
        schedules.add(schedule);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}

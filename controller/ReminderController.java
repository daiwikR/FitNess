package fitnesstracker.controller;

import fitnesstracker.model.Reminder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderController {
    private List<Reminder> reminders;

    public ReminderController() {
        this.reminders = new ArrayList<>();
    }

    public ReminderController(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public void addReminder(String message, LocalDateTime dateTime) {
        Reminder reminder = new Reminder(message, dateTime);
        reminders.add(reminder);
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
}

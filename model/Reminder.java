package fitnesstracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reminder {
    private String message;
    private LocalDateTime reminderTime;
    private boolean notified = false;  // added to track if notification was fired

    public Reminder(String message, LocalDateTime reminderTime) {
        this.message = message;
        this.reminderTime = reminderTime;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
    
    public boolean isNotified() {
        return notified;
    }
    
    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public String getFormattedReminderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return reminderTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Reminder: " + message + " at " + getFormattedReminderTime();
    }
}

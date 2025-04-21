// package fitnesstracker.controller;

// import fitnesstracker.model.Reminder;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// public class ReminderController {
//     private List<Reminder> reminders;

//     public ReminderController() {
//         this.reminders = new ArrayList<>();
//     }

//     public ReminderController(List<Reminder> reminders) {
//         this.reminders = reminders;
//     }

//     public void addReminder(String message, LocalDateTime dateTime) {
//         Reminder reminder = new Reminder(message, dateTime);
//         reminders.add(reminder);
//     }

//     public void removeReminder(Reminder reminder) {
//         reminders.remove(reminder);
//     }

//     public List<Reminder> getReminders() {
//         return reminders;
//     }
// }



package fitnesstracker.controller;

import fitnesstracker.model.Reminder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReminderController {
    private List<Reminder> reminders;
    private ReminderNotifier notifier;

    public ReminderController(ReminderNotifier notifier) {
        this.reminders = new ArrayList<>();
        this.notifier = notifier;
    }

    public void addReminder(String message, LocalDateTime dateTime) {
        Reminder reminder = new Reminder(message, dateTime);
        reminders.add(reminder);

        try (FileWriter writer = new FileWriter("logs/test1.txt", true)) {
             writer.write("HI\n");
        } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }


        notifier.addRemindering(reminder); // Schedules the reminder notification
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
}

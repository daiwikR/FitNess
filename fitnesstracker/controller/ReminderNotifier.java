// package fitnesstracker.controller;

// import fitnesstracker.model.Reminder;

// import java.awt.*;
// import java.awt.TrayIcon.MessageType;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Timer;
// import java.util.TimerTask;

// public class ReminderNotifier {
//     private List<Reminder> reminders;
//     private Timer timer;

//     public ReminderNotifier(List<Reminder> reminders) {
//         this.reminders = reminders;
//         timer = new Timer(true);
//     }

//     public void start() {
//         timer.scheduleAtFixedRate(new TimerTask() {
//             @Override
//             public void run() {
//                 checkReminders();
//             }
//         }, 0, 60000); // check every minute
//     }

//     private void checkReminders() {
//         LocalDateTime now = LocalDateTime.now();
//         for (Reminder r : reminders) {
//             if (!r.isNotified() && now.isAfter(r.getReminderTime())) {
//                 showNotification(r);
//                 r.setNotified(true);
//             }
//         }
//     }

//     private void showNotification(Reminder r) {
//         if (SystemTray.isSupported()) {
//             try {
//                 SystemTray tray = SystemTray.getSystemTray();
//                 Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // use an actual image file if available
//                 TrayIcon trayIcon = new TrayIcon(image, "Fitness Tracker Reminder");
//                 trayIcon.setImageAutoSize(true);
//                 trayIcon.setToolTip("Reminder");
//                 tray.add(trayIcon);
//                 trayIcon.displayMessage("Reminder", r.getMessage(), MessageType.INFO);
//                 // Remove the tray icon after a delay
//                 Thread.sleep(5000);
//                 tray.remove(trayIcon);
//             } catch (Exception ex) {
//                 System.out.println("Reminder: " + r.getMessage());
//             }
//         } else {
//             System.out.println("Reminder: " + r.getMessage());
//         }
//     }
// }

package fitnesstracker.controller;

import fitnesstracker.model.Reminder;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ReminderNotifier {
    private ScheduledExecutorService scheduler;

    public ReminderNotifier() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void addRemindering(Reminder reminder) {
        // Calculate delay in milliseconds until the reminder time
        long delay = Duration.between(LocalDateTime.now(), reminder.getReminderTime()).toMillis();

        // Write debug output to a file
        try (FileWriter writer = new FileWriter("C:/Users/bhave/Desktop/Sem 6/OOAD/Daiwik_proj/fitnesstracker/controller/test.txt", true)) { // Append mode
            writer.write("LocalDateTime.now(): " + LocalDateTime.now() + "\n");
            writer.write("reminder.getReminderTime(): " + reminder.getReminderTime() + "\n");
            writer.write("Reminder in " + delay + " milliseconds\n");
            writer.write("--------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace(); // Log error in case file writing fails
        }

        if (delay < 0) {
            delay = 0; // If the time is already past, schedule immediately
        }

        scheduler.schedule(() -> {
            showNotification(reminder);
            reminder.setNotified(true);
        }, delay, TimeUnit.MILLISECONDS);
    }

    private void showNotification(Reminder r) {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // Replace with a valid image file if needed
                TrayIcon trayIcon = new TrayIcon(image, "Fitness Tracker Reminder");
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip("Reminder");
                tray.add(trayIcon);
                trayIcon.displayMessage("Reminder", r.getMessage(), MessageType.INFO);

                // Pause to let the notification show, then remove icon
                Thread.sleep(5000);
                tray.remove(trayIcon);
            } catch (Exception ex) {
                System.out.println("Reminder: " + r.getMessage());
            }
        } else {
            System.out.println("Reminder: " + r.getMessage());
        }
    }

    // Optionally call this when shutting down the app
    public void shutdown() {
        scheduler.shutdown();
    }
}

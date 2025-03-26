package fitnesstracker.controller;

import fitnesstracker.model.Reminder;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderNotifier {
    private List<Reminder> reminders;
    private Timer timer;

    public ReminderNotifier(List<Reminder> reminders) {
        this.reminders = reminders;
        timer = new Timer(true);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkReminders();
            }
        }, 0, 60000); // check every minute
    }

    private void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        for (Reminder r : reminders) {
            if (!r.isNotified() && now.isAfter(r.getReminderTime())) {
                showNotification(r);
                r.setNotified(true);
            }
        }
    }

    private void showNotification(Reminder r) {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // use an actual image file if available
                TrayIcon trayIcon = new TrayIcon(image, "Fitness Tracker Reminder");
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip("Reminder");
                tray.add(trayIcon);
                trayIcon.displayMessage("Reminder", r.getMessage(), MessageType.INFO);
                // Remove the tray icon after a delay
                Thread.sleep(5000);
                tray.remove(trayIcon);
            } catch (Exception ex) {
                System.out.println("Reminder: " + r.getMessage());
            }
        } else {
            System.out.println("Reminder: " + r.getMessage());
        }
    }
}


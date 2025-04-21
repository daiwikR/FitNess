package fitnesstracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalorieLog {
    // Nested class to hold individual meal entries
    public static class MealEntry {
        private String mealName;
        private int calories;
        private LocalDateTime entryTime;

        public MealEntry(String mealName, int calories, LocalDateTime entryTime) {
            this.mealName = mealName;
            this.calories = calories;
            this.entryTime = entryTime;
        }

        public String getMealName() {
            return mealName;
        }

        public int getCalories() {
            return calories;
        }

        public LocalDateTime getEntryTime() {
            return entryTime;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return mealName + " (" + calories + " cal) at " + entryTime.format(formatter);
        }
    }

    private List<MealEntry> mealEntries;

    public CalorieLog() {
        mealEntries = new ArrayList<>();
    }

    public void addMealEntry(String mealName, int calories, LocalDateTime entryTime) {
        mealEntries.add(new MealEntry(mealName, calories, entryTime));
    }

    public void removeMealEntry(MealEntry entry) {
        mealEntries.remove(entry);
    }

    public int getTotalCalories() {
        int total = 0;
        for (MealEntry entry : mealEntries) {
            total += entry.getCalories();
        }
        return total;
    }

    public List<MealEntry> getMealEntries() {
        return mealEntries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Calorie Log:\n");
        for (MealEntry entry : mealEntries) {
            sb.append(entry.toString()).append("\n");
        }
        sb.append("Total Calories: ").append(getTotalCalories());
        return sb.toString();
    }
}

package fitnesstracker.controller;

import fitnesstracker.model.CalorieLog;
import fitnesstracker.model.CalorieLog.MealEntry;

import java.time.LocalDateTime;
import java.util.List;

public class CalorieController {
    private CalorieLog calorieLog;

    public CalorieController(CalorieLog calorieLog) {
        this.calorieLog = calorieLog;
    }

    public void addMeal(String mealName, int calories) {
        // Timestamp with current time or pass a custom time if needed
        calorieLog.addMealEntry(mealName, calories, LocalDateTime.now());
    }

    public void removeMeal(MealEntry entry) {
        calorieLog.removeMealEntry(entry);
    }

    public int getTotalCalories() {
        return calorieLog.getTotalCalories();
    }

    public List<MealEntry> getMealEntries() {
        return calorieLog.getMealEntries();
    }
}

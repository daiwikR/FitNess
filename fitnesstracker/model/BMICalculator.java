package fitnesstracker.model;

public class BMICalculator {

    // Calculate BMI given height in meters and weight in kilograms
    public static double calculateBMI(double height, double weight) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }
        return weight / (height * height);
    }

    // Return a simple BMI category based on the BMI value
    public static String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}

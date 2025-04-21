package fitnesstracker.model;

public class Exercise {
    private String name;
    private String description;
    private String muscleGroup;

    public Exercise(String name, String description, String muscleGroup) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    @Override
    public String toString() {
        return name + " (" + muscleGroup + ")";
    }
}

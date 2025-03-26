package fitnesstracker.model;

public class SetCounter {
    private int sets;
    private int reps;

    public SetCounter() {
        this.sets = 0;
        this.reps = 0;
    }

    public void incrementSet() {
        sets++;
    }
    
    public void decrementSet() {
        if (sets > 0) sets--;
    }

    public void incrementRep() {
        reps++;
    }
    
    public void decrementRep() {
        if (reps > 0) reps--;
    }

    public void reset() {
        sets = 0;
        reps = 0;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    @Override
    public String toString() {
        return "Sets: " + sets + ", Reps: " + reps;
    }
}

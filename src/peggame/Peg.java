package peggame;

public class Peg {
    private boolean isPresent;

    public Peg() {
        this.isPresent = true;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void remove() {
        this.isPresent = false;
    }
}

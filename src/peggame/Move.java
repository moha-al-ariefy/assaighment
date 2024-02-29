package peggame;

public class Move {
    private final Location from;
    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    private final Location to;

    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

}

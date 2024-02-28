package peggame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Peg> pegs;

    public Player(String name) {
        this.name = name;
        this.pegs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Peg> getPegs() {
        return pegs;
    }

    public void addPeg(Peg peg) {
        this.pegs.add(peg);
    }

    public void removePeg(Peg peg) {
        this.pegs.remove(peg);
    }
}

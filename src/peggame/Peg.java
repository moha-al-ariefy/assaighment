package peggame;

public class Peg {
    private boolean isPresent;
    private Player owner;  
    private location location;  

    public Peg(Player owner, location location) {
        this.isPresent = true;
        this.owner = owner;
        this.location = location;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public Player getOwner() {  
        return owner;
    }

    public location getLocation() {  
        return location;
    }

    public void remove() {
        this.isPresent = false;
    }
}

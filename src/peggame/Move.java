package peggame;

public class Move {
    private location start;
    private location end;
    
    public location getStart() {
        return start;
    }

    public location getEnd() {
        return end;
    }
    public Move(location start, location end) {
        this.start = start;
        this.end = end;
    }


}

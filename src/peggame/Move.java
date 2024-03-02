package peggame;

public class move {
    location from;
    location to;

    public move(location from,location to){
        this.from = from;
        this.to = to;
    }

    public location getFrom() {
        return from;
    }
    public location getTo() {
        return to;
    }

    public String toString(){
        return "it will move from :"+from+" to :"+to;
       }
    }


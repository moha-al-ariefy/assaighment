package peggame;

public class move {
    location from; // tow location objects from and to
    location to;

    public move(location from,location to){ // the constructor
        this.from = from;
        this.to = to;
    }
 // the getters
    public location getFrom() {
        return from;
    }
    public location getTo() {
        return to;
    }

    public String toString(){ // tostring method saying from where to where it moved
        return "it will go from :"+from+" to :"+to;
       }

       @Override
       public boolean equals(Object obj) { //equals and has code methods 
           if (this == obj) { // if they are exactly the same thing(in the location memory)
               return true;
           }
           if (obj == null || getClass() != obj.getClass()) { // comparing the classes first 
               return false; // if they are not the same false directly 
           }
           move other = (move) obj; /* This line casts obj to a move object. 
           This is safe because the previous line returned false 
           if obj wasn’t a move.
           */
           return from.equals(other.from) && to.equals(other.to); // the final thing where we comapre the from and to between the objects
       }
   
       @Override
       public int hashCode() {
           int result = from != null ? from.hashCode() : 0;
           /*
            This line initializes the result variable.
            If from is not null, it calls the hashCode method on from and assigns the result to result.
            If from is null, it assigns 0 to result.
            */
           result = 31 * result + (to != null ? to.hashCode() : 0);
            /* 
            This line updates result. It multiplies result by 31 and adds the hash code of to 
            if to is not null, or 0 if . 
            */
           return result;

       }
   }


    


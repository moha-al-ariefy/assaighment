 package peggame;

import java.util.Objects;

import javax.print.DocFlavor.STRING;

public class location {
    //the location class which is one of the 3 main parts that point at the peg location
   private int row;

   // geters 
   public int getRow() {
    return row;
}

public int getCol() {
    return col;
}

private int col; //col and rows (i dont remmber why col is down here)
    
   public location(int row, int col){ // the constructer for seting the loaction
    this.row = row; 
    this.col = col;
   }

   public String toString(){ // to string method to return the loaction in row and col
    return "{row:"+row+", coluim:"+col+"}";
   }

       @Override
    public boolean equals(Object obj) {

        if (this == obj) { //the equals method between rows and cols but first comparing the classes
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        location location = (location) obj;
        return  col == location.col && row == location.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col); // the hash method we are asked to implemnt
    }
}

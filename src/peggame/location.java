 package peggame;

import java.util.Objects;

import javax.print.DocFlavor.STRING;

public class location {
   private int row;
   public int getRow() {
    return row;
}

public int getCol() {
    return col;
}

private int col; 
    
   public location(int row, int col){
    this.row = row;
    this.col = col;
   }

   public String toString(){
    return "{row:"+row+", coluim:"+col+"}";
   }

       @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        location location = (location) obj;
        return row == location.row && col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

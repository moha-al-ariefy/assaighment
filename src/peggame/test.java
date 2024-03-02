package peggame;

import java.io.IOException;

public class test {
    public static void main(String[] args) {
        Zasquare test1 = new Zasquare(5);
        System.out.println(test1);

        try {
            PegGame test2 = fileparser.parseFile("src/peggame/game1.txt");
            fileparser.testBoard(test2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

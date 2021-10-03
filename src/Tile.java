/**
 * represents a single spot on the maze
 */

public class Tile {
    private char symbol;

    public Tile(char sym){
        symbol = sym;
    }

    public char getSymbom(){
        return symbol;
    }

    public char render(ColorValue v){
        if(symbol == v.asButton() || symbol == v.asDoor()){
            return '.';
        }
        return symbol;
    }


    @Override
    public String toString(){
        return String.valueOf(symbol);
    }

}

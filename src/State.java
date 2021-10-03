

public class State {
    private ColorValue c;
    private Point p;

    public State (ColorValue c, Point p){
        this.c = c;
        this.p = p;
    }

    public ColorValue getColorValue(){
        return c;
    }
    public Point getPoint(){
        return p;
    }


}

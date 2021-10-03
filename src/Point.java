public class Point {
    private int row, col;

    public Point(int x, int y) {
        row = y;
        col = x;
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    /**
     * return a new point to the north of the current point
     * @return
     */
    public Point north(){
        return new Point(col, row - 1);
    }

    public Point south(){                   //return a new point south of the current point
        return new Point(col, row + 1);
    }

    public Point east(){  //return a new point east of the current point
        return new Point(col + 1, row);
    }

    public Point west(){  //return a new point west of the current point
        return new Point(col - 1, row);
    }

    public String toString(){
        return "(" + row + ", " + col + ")";
    }

}

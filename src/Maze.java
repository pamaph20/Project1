import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    //store dimensions
    private int numColors, height, width;

    //2d array list to store all tiles
    //outer array list stores rows
    //inner arraylist stores cols
    private ArrayList<ArrayList<Tile>> map;

    //3d array list to store whether we reached a tile in a given color
    //color -- row -- col
    private ArrayList<ArrayList<ArrayList<Boolean>>> reached;

    //reachable collectrion
    private ArrayDeque<State> reachableCollection;

    private State start,finish;


    public Maze(Scanner in){
        numColors = in.nextInt();
        height = in.nextInt();
        width = in.nextInt();

        //read the rest of the line to avoid junk
        in.nextLine();

        //allocate enough space for height rows
        map = new ArrayList<>(height);

        //helper variable for tracking how many rows we have read
        int row = 0;
        while(in.hasNextLine()){
            //we have processed rows
            if(row >= height){
                break;
            }

            String line = in.nextLine();

            //check if comment and skip
            if(line.length() >= 2 &&  line.charAt(0) == '/' &&line.charAt(1) == '/'){
                continue;
            }
            //we know we have a line of tiles to process

            ArrayList<Tile> rowOfTiles = new ArrayList<>(width);
            for(int i = 0; i < line.length(); i++){
                char sym = line.charAt(i);
                if(sym == '@'){
                    start = new State(ColorValue.fromIndex(0), new Point(row, i));
                    //error checking for one state
                }
                if(sym == '?'){
                    finish = new State(ColorValue.fromIndex(0), new Point(row, i));
                }
                rowOfTiles.add(new Tile(line.charAt(i)));
            }

            //add row to map
            map.add(rowOfTiles);
            row++;
        }
    }

    public void search(Config c){
        //step 1
        // fill in our reached AL with false
        reached = new ArrayList<>(numColors + 1);

        for(int x = 0; x < numColors + 1; x++){
            // make an array list to store rows for this color
            ArrayList<ArrayList<Boolean>> rowList = new ArrayList<>(height);
            for(int row = 0; row < height; row++){
                //array list for column values
                ArrayList<Boolean> colList = new ArrayList<>(width);
                for(int col = 0; col < width; col++){
                    colList.add(false);
                }
                rowList.add(colList);
            }
            reached.add(rowList);
        }

        //initialize our reachable collection
        reachableCollection = new ArrayDeque<>();

        //step 2
        markReached(start);
        reachableCollection.addFirst(start);

        //step 3

        while(!reachableCollection.isEmpty()){
            int total = 0;
            State curr;
            if(c.isQueueMode()){
                curr = reachableCollection.removeLast();
                total++;

            }else{
                curr = reachableCollection.removeFirst();
                total++;
            }
            System.out.println(total + ": processing (" + curr.getColorValue() + "," + curr.getPoint() + ")");



            //check north
            State north = new State(curr.getColorValue(), curr.getPoint().north());
            if(canBeReached(north)){
                markReached(north);
                reachableCollection.addFirst(north);
                System.out.println("adding " +  "(" + curr.getColorValue() + "," + curr.getPoint() + ")")    ;

            }
            //check south
            State south = new State(curr.getColorValue(), curr.getPoint().south());
            if(canBeReached(south)){
                markReached(south);
                reachableCollection.addFirst(south);
                System.out.println("adding " +  "(" + curr.getColorValue() + "," + curr.getPoint() + ")")    ;
            }
            //check east
            State east = new State(curr.getColorValue(), curr.getPoint().east());
            if(canBeReached(east)){
                markReached(east);
                reachableCollection.addFirst(east);
                System.out.println("adding " +  "(" + curr.getColorValue() + "," + curr.getPoint() + ")")    ;
            }
            //check west
            State west = new State(curr.getColorValue(), curr.getPoint().west());
            if(canBeReached(west)){
                markReached(west);
                reachableCollection.addFirst(west);
                System.out.println("adding " +  "(" + curr.getColorValue() + "," + curr.getPoint() + ")")    ;
            }
        }
    }

    private void markReached(State st) {
        int colorIdx = st.getColorValue().asIndex();
        Point p = st.getPoint();
        reached.get(colorIdx).get(p.getRow()).set(p.getCol(), true);
    }

    private boolean canBeReached(State st) {
        int colorIdx = st.getColorValue().asIndex();
        Point p = st.getPoint();

        if (p.getCol() < 0 || p.getCol() >= width || p.getRow() < 0 || p.getRow() >= height) {
            //outside the bounds
            return false;
        }

        //already reached?
        if (reached.get(colorIdx).get(p.getRow()).get(p.getCol())) {
            return true;
        }


        Tile curr = map.get(p.getRow()).get(p.getCol());
        if (curr.equals('#')) {
            return false;
        }
        if(curr.equals('A') || curr.equals('B')){
            return false;
        }

        return true;

    }

    public void printMap(){
        //numcolors + 1 : all closed + each of the colors
        for(int i = 0; i < numColors + 1; i++) {
            ColorValue curr = ColorValue.fromIndex(i);
            System.out.print("// color " + curr + "\n");
            for (ArrayList<Tile> row : map) {
                for (Tile col : row) {
                    System.out.print(col.render(curr));
                }
                //row is output
                System.out.print("\n");

            }
        }
    }
}

import java.awt.Color;

/**
 * Creates a food object within a cell object.
 */
public class Food extends Cell {
    private static int row;
    private static int col;


    public Food() {
        super(row, col);
        this.setFillColor(new Color(255,165,188));
        this.setStrokeColor(new Color(255,165,188));
    }

}
   

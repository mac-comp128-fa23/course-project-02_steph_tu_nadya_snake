import java.awt.Color;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

/**
 * Creates a food object within a cell object.
 */
public class Food extends Cell {
    private static int row;
    private static int col;
    private Image strawberryImage;
    private GraphicsGroup graphics;


    public Food() {
        // super(row, col);
        // this.strawberryImage = new Image("straw.png");
        // this.strawberryImage.setScale(0.5);
        // this.graphics.add(stra);
        super(row, col);
        this.setFillColor(new Color(255,165,188));
        this.setStrokeColor(new Color(255,165,188));
    }

}
   

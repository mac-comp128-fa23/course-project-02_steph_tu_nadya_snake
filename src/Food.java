// import java.awt.Color;
import edu.macalester.graphics.Image;

/**
 * Creates a food object within a cell object.
 */
public class Food extends Cell {
    private static int row;
    private static int col;
    private Image strawberryImage;


    public Food() {
        super(row, col);
        this.strawberryImage = new Image("straw.png");
        this.strawberryImage.setScale(0.5);
        // this.setBackground(this.strawberryImage);
    }

}
   

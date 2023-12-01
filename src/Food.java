import java.awt.Color;
import java.util.Random;

public class Food extends Cell {
    private static int row;
    private static int col;
    private Color color;
    private int colorCode;
    private Random random;
    
    //random score and/or size for food (random color to differentiate too)
    
    public Food() {
        super(row, col);
        colorCode = random.nextInt(4) + 1;

        if (colorCode == 1) {
            //color is pinkish
            color = new Color(243, 168, 188);
        } else if (colorCode == 2) {
            //color is orangeish
            color = new Color(245, 173, 148);
        } else if (colorCode == 3) {
            //color is yellowish
            color = new Color(255, 241, 166);
        } else if (colorCode == 4) {
            //color is greenish
            color = new Color(180, 249, 165);
        } else {
            //color is blueish
            color = new Color(158, 231, 245);
        }
    }

    public Color getColor() {
        return color;
    }
}

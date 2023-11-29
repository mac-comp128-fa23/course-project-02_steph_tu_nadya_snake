import java.awt.Color;
import java.util.Random;

public class Food {
    private Color color;
    private int points;
    private Random random;
    
    //random score and/or size for food (random color to differentiate too)
    
    public Food() {
        //points range from 1-5
        points = random.nextInt(4) + 1;

        //color can either be purple or pink 
        if (random.nextBoolean()) {
            color = new Color(255, 179, 203);
        } else {
            color = new Color(207, 157, 224);
        }

    }

    public int getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }
}

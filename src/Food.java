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

        if (points == 1) {
            //color is pinkish
            color = new Color(243, 168, 188);
        } else if (points == 2) {
            //color is orangeish
            color = new Color(245, 173, 148);
        } else if (points == 3) {
            //color is yellowish
            color = new Color(255, 241, 166);
        } else if (points == 4) {
            //color is greenish
            color = new Color(180, 249, 165);
        } else {
            //color is blueish
            color = new Color(158, 231, 245);
        }

    }

    public int getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }
}

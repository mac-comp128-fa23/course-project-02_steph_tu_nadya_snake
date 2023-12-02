import java.awt.Color;
import java.util.Random;

public class Food extends Cell {
    private static int row;
    private static int col;
    private Random random;
        
    public Food() {
        super(row, col);
        this.setFillColor(new Color(255,165,188));
    
    }

    // public void setColor() {
    //     int colorCode = this.random.nextInt(4) + 1;

    //     if (colorCode == 1) {
    //         //color is pinkish
    //         this.color = new Color(243, 168, 188);
    //     } else if (colorCode == 2) {
    //         //color is orangeish
    //         this.color = new Color(245, 173, 148);
    //     } else if (colorCode == 3) {
    //         //color is yellowish
    //         this.color = new Color(255, 241, 166);
    //     } else if (colorCode == 4) {
    //         //color is greenish
    //         this.color = new Color(180, 249, 165);
    //     } else {
    //         //color is blueish
    //         this.color = new Color(158, 231, 245);
    //     }
    // }
}
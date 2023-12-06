import java.awt.Color;

public class Rock extends Cell {
    private static int row;
    private static int col;
        
    public Rock() {
        super(row, col);
        this.setFillColor(Color.black);
        this.setStrokeColor(Color.BLACK);
    }
}
import java.awt.Color;

public class Rock extends Cell {
    private static int row;
    private static int col;
        
    public Rock() {
        super(row, col);
        this.setFillColor(new Color(0,146,168));
        this.setStrokeColor(new Color(0,146,168));
    }
}
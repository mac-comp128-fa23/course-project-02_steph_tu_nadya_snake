import java.awt.Color;

public class Rock extends Cell {
    private static int row;
    private static int col;
        
    public Rock() {
        super(row, col);
        this.setFillColor(new Color(132,151,133));
        this.setStrokeColor(new Color(132,151,133));
    }
}
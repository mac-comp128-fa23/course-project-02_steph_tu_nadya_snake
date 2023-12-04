import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Cell extends Rectangle {
    private int row;
    private int col;
    private String type;
    private Cell next;

    public final Color GRID1 = new Color(248,251,247);
    public final Color GRID2 = new Color(246,238,248);

    public Cell(int row, int col) {
        super(50 + 25 * row, 75 + 25 * col, 25, 25);

        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Cell getNext() {
        return this.next;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public void setLocation() {
        this.setPosition(50 + 25 * this.row, 75 + 25 * this.col);
    }

    public void resetCell() {
        if ((this.row + this.col) % 2 == 0) {
            this.setFillColor(GRID1);
            this.setStrokeColor(Color.WHITE);
        } else {
            this.setFillColor(GRID2);
            this.setStrokeColor(Color.WHITE);
        }
    }
}
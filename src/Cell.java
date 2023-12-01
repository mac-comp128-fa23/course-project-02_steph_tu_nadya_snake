import edu.macalester.graphics.Rectangle;

public class Cell extends Rectangle {
    private int row;
    private int col;
    private String type;
    private Cell next;

    public Cell(int row, int col) {
        super(50, 75, 25, 25);

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
}
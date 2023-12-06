import edu.macalester.graphics.GraphicsGroup;
import java.util.LinkedList;
import java.awt.Color;

public class Snake extends GraphicsGroup{

    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;
    private Cell tail;
    private Cell[][] cells;

    public final Color SNAKE_COLOR = new Color(176,230,175);
    public final Color SNAKE_HEAD = new Color(127,206,131);
    
    public Snake(Cell[][] cells, int initialLength) {
        this.cells = cells;

        this.head = new Cell(this.cells.length / 2, this.cells[0].length / 2);
        this.tail = this.head;
        this.snakeBody.push(this.head);

        for (int i = 0; i < initialLength - 1; i++) {
            Cell newTail = new Cell(this.tail.getRow(), this.tail.getCol() - 1);
            this.tail.setNext(newTail);
            this.snakeBody.push(newTail);
            this.tail = newTail;
        }
    }

    public void addToTail(){
        Cell newTail = new Cell(this.tail.getRow(),this.tail.getCol() - 1);
        this.tail.setNext(newTail);
        this.snakeBody.push(newTail);
        this.tail = newTail;
    }

    public void removeFromTail(){
        if (this.snakeBody.size() >= 1) {
            this.snakeBody.pop();
            Cell newTail = this.snakeBody.peek();
            this.tail.setNext(newTail);
        }
    }

    public int getLength(){
        return this.snakeBody.size();
    }

    public LinkedList<Cell> getSnakeBody() {
        return this.snakeBody;
    }

    public Cell getHead() {
        return this.head;
    }

    public Cell getTail() {
        return this.tail;
    }
}
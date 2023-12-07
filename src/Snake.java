import edu.macalester.graphics.GraphicsGroup;
import java.util.LinkedList;
import java.awt.Color;

/**
 * Snake object represented as a linked stack and visualized within the cells of the board.
 */
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
            Cell newTail = new Cell(this.tail.getRow(), this.tail.getCol() + 1);
            newTail.setPrevious(this.tail);
            this.snakeBody.push(newTail);
            this.tail = newTail;
        }
    }

    /**
     * Add a new body segment to the tail of the snake. This adds a cell into
     * the linked-stack using the push operation.
     */
    public void addToTail(){
        Cell newTail = new Cell(this.tail.getRow(),this.tail.getCol() - 1);
        newTail.setPrevious(this.tail);
        this.snakeBody.push(newTail);
        this.tail = newTail;
    }

    /**
     * Removes a body segment off the tail of the snake. This removes the top of the
     * linked-stack using the pop operation.
     */
    public void removeFromTail(){
        if (this.snakeBody.size() >= 1) {
            this.tail = this.tail.getPrevious();
            this.snakeBody.pop();
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
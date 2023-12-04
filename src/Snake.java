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
        cells[this.head.getRow()][this.head.getCol()].setType("snake");

        for (int i = 0; i < initialLength - 1; i++) {
            Cell newTail = new Cell(this.tail.getRow(), this.tail.getCol() - 1);
            this.tail.setNext(newTail);
            this.snakeBody.push(newTail);
            this.tail = newTail;
            this.cells[this.tail.getRow()][this.tail.getCol()].setType("snake");
        }
    }


    public void addToTail(Cell newTail){
        this.snakeBody.push(newTail);
        // update
        this.head.setNext(newTail);
        newTail.setType("snake");
    }

    public void removeFromTail(){
        if (this.snakeBody.size() >= 1) {
            this.snakeBody.pop();
            Cell newTail = this.snakeBody.peek();
            this.head.setNext(newTail);
            this.head.setType("snake");
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
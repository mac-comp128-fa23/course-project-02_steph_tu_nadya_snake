import java.awt.Color;
import java.util.LinkedList;

import edu.macalester.graphics.GraphicsGroup;

public class Snake extends GraphicsGroup{

    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;
    private Cell[][] cells;

    public final Color SNAKE_COLOR = new Color(176,230,175);
    public final Color SNAKE_HEAD = new Color(127,206,131);
    
    public Snake(Cell pos, Cell[][] cells, int initialLength) {
        this.head = pos;
        this.snakeBody.push(this.head);
        this.head.setType("snake");
        this.cells = cells;

        // intial length
        for (int i = 1; i < initialLength; i++) {
            Cell newTail = new Cell(pos.getRow(), pos.getCol() - i);
            this.snakeBody.push(newTail);
            newTail.setType("snake");
        }
    }


    public void draw() {
        //changes to make the head darker
        for (int i = 0; i < this.snakeBody.size(); i++) {
            Cell cell = this.snakeBody.get(i);
            if (i == 0) {
                cell.setFillColor(SNAKE_HEAD);
                cell.setStrokeColor(new Color(199,237,198));
            } else {
                cell.setFillColor(SNAKE_COLOR); 
                cell.setStrokeColor(new Color(199,237,198));
            }
            cell.setFilled(true);
            this.cells[cell.getRow()][cell.getCol()] = cell;
        }
    }


    public void move(Cell nextCell){
        Cell tail = this.snakeBody.pop(); // remove the tail
        tail.setType("board"); //
        this.head = nextCell;
        this.head.setType("snake");
        this.snakeBody.push(this.head);
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


    public int getTailLength(){
        return this.snakeBody.size() - 1;
    }

    public LinkedList<Cell> getSnakeBody() {
        return this.snakeBody;
    }

    public Cell getHead() {
        return this.head;
    }

    public Cell getNext() {
        return this.head.getNext();
    }

}
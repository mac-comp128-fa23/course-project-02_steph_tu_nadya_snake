import java.awt.Color;
import java.util.LinkedList;

import edu.macalester.graphics.GraphicsGroup;

public class Snake extends GraphicsGroup{
    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;
    private Cell[][] cells;
    
     

    public Snake(Cell pos, Cell[][] cells) {
        this.head = pos;
        this.snakeBody.push(this.head);
        this.head.setType("snake");
        this.cells = cells;
    }


    public void draw() {
        for (Cell cell : this.snakeBody) {
            cell.setFillColor(Color.GREEN);
            cell.setFilled(true);
            this.cells[cell.getRow()][cell.getCol()] = cell;
        }
    }


    public void move(Cell nextCell)
    {
        Cell tail = this.snakeBody.peek();
        tail.setType("board");
 
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

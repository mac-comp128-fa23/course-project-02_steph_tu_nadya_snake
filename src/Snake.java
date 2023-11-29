import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;

    public Snake(Cell pos) {
        this.head = pos;
        this.snakeBody.push(this.head);
    }

    
    public void addToTail(Cell newTail){
        this.snakeBody.push(newTail);
    }
   

    public void removeFromTail(){
        if (this.snakeBody.size() > 1) {
            this.snakeBody.pop();
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

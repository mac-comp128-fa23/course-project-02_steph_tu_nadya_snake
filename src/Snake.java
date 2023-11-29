import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;

    public Snake(Cell pos) {
        this.head = pos;
        this.snakeBody.push(this.head);
        this.head.setType("snake");
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

    public void move(){

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

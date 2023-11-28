import java.util.LinkedList;

import edu.macalester.graphics.Point;

public class Snake {
    private LinkedList<Point> snakeBody = new LinkedList<>();
    private Point tail;
    private Point head;
    private int intialLength = 1;


    public Snake(Point pos) {
        this.head = pos;
        this.tail = pos;
        this.snakeBody.push(this.head);

    }

}

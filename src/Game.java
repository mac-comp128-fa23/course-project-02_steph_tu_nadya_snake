import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class Game {
    private CanvasWindow window;
    private Board board;

    public Game() {
        this.window = new CanvasWindow("Snake", 600, 600);

        this.board = new Board();
        Cell[][] cells = this.board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setLocation();
                if ((i + j) % 2 == 0) {
                    cells[i][j].setFillColor(Color.LIGHT_GRAY);
                    this.window.add(cells[i][j]);
                } else {
                    cells[i][j].setFillColor(Color.GRAY);
                    this.window.add(cells[i][j]);
                }
            }
        }

        this.window.add(this.board.getFood());
        Snake snake = this.board.getSnake();
        snake.draw(); 
        this.window.add(snake);
}
    

   

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game();
    }
}

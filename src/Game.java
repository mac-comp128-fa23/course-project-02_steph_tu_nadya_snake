import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class Game {
    private CanvasWindow window;
    private Board board;
    private Snake snake;
    public final Color GRID1 = new Color(248,251,247);
    public final Color GRID2 = new Color(246,238,248);

    public Game() {
        this.window = new CanvasWindow("Snake", 600, 600);

        this.board = new Board();
        Cell[][] cells = this.board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setLocation();
                if ((i + j) % 2 == 0) {
                    cells[i][j].setFillColor(GRID1);
                    cells[i][j].setStrokeColor(Color.WHITE);
                    this.window.add(cells[i][j]);
                } else {
                    cells[i][j].setFillColor(GRID2);
                    cells[i][j].setStrokeColor(Color.WHITE);
                    this.window.add(cells[i][j]);
                }
            }
        }

        this.window.add(this.board.getFood());
        this.snake = this.board.getSnake();
        this.snake.draw(); 
        this.window.add(this.snake);

}


   

    public void generateBackground() {
        
    }

    public void run() {
        this.window.animate(() -> {
            // Move the snake in the direction it's facing
            this.snake.move(this.snake.getNext());

            this.snake.draw();
        });
    }



    public static void main(String[] args) {
        new Game();
    }
}
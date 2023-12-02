import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

public class Game {

    

    //arrow up = ModifierKey.UP_ARROW
    //arrow right = ModifierKey.RIGHT_ARROW
    //arrow down = ModifierKey.DOWN_ARROW
    //arrow left = ModifierKey.LEFT_ARROW

    private CanvasWindow window;

    private Board board;
    private Snake snake;

    public final Color GRID1 = new Color(248,251,247);
    public final Color GRID2 = new Color(246,238,248);

    private GraphicsText title;
    private GraphicsText authorText;
    private GraphicsText description;
    private GraphicsText scoreText;

    public Game() {
        this.window = new CanvasWindow("Snake", 600, 600);
        window.setBackground(new Color(244,197,227));

        title = new GraphicsText("Snake!");
        title.setFont("Georgia", FontStyle.BOLD, 32);
        title.setFillColor(Color.WHITE);
        window.add(title, 440, 65);

        authorText = new GraphicsText("Made by Steph, Nadya & Tu ♥");
        authorText.setFont("Georgia", FontStyle.ITALIC, 15);
        authorText.setFillColor(Color.WHITE);
        window.add(authorText, 12, 593);

        description = new GraphicsText("Press any key to start.");
        description.setFont("Georgia", FontStyle.PLAIN, 15);
        description.setFillColor(Color.WHITE);
        window.add(description, 50, 68);

        scoreText = new GraphicsText("Score: null");
        scoreText.setFont("Georgia", FontStyle.BOLD, 24);
        scoreText.setFillColor(Color.WHITE);
        window.add(scoreText, 50, 45);

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
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

public class Game {
    private CanvasWindow window;
    private Snake snake;
    private Food food;
    private Cell cell;
    private Board board;
    private Button exitButton;
    private GraphicsText scoreText;

    public Game() {
        window = new CanvasWindow("Snake", 600, 600);
    }

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game();
    }
}

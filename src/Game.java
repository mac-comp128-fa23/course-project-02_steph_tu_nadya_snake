import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow window;
    private int score;

    public Game() {
        window = new CanvasWindow("Snake", 600, 600);
    }

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game();
    }
}

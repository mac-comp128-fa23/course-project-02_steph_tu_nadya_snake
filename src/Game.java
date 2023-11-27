import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow window;

    public Game(int width, int height) {
        window = new CanvasWindow("Snake", width, height);
    }

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game(600, 700);
    }
}

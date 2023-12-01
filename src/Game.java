import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow window;

    public Game() {
        window = new CanvasWindow("Snake", 600, 600);
        window.add(new Board());
    }

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game();
    }
}

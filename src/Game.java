import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;

public class Game {
    private CanvasWindow window;
    private Board board;

    public Game() {
        window = new CanvasWindow("Snake", 600, 600);

        board = new Board();
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setLocation();
                if ((i + j) % 2 == 0) {
                    cells[i][j].setFillColor(Color.LIGHT_GRAY);
                    window.add(cells[i][j]);
                } else {
                    cells[i][j].setFillColor(Color.GRAY);
                    window.add(cells[i][j]);
                }
            }
        }

        window.add(board.getFood());
    }

    public void generateBackground() {
        
    }

    public static void main(String[] args) {
        new Game();
    }
}

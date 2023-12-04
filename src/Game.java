import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.FontStyle;
import java.util.LinkedList;
import java.awt.Color;

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
    public final Color SNAKE_HEAD = new Color(127,206,131);
    public final Color SNAKE_COLOR = new Color(176,230,175);

    private GraphicsText title;
    private GraphicsText authorText;
    private GraphicsText description;
    private GraphicsText scoreText;

    private static double radius = 0;
    private static double degree = 0;

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
        drawBoard(this.board.getCells());

        this.window.add(this.board.getFood());
        
        this.snake = this.board.getSnake();
        drawSnake(this.snake.getSnakeBody());
        run();
    }

    public void drawBoard(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setLocation();
                cells[i][j].resetCell();
                this.window.add(cells[i][j]);
            }
        }
    }

    public void drawSnake(LinkedList<Cell> snakeBody) {
        for (int i = 0; i < snakeBody.size(); i++) {
            Cell cell = snakeBody.get(i);
            cell.setLocation();
            if (i == 0) {
                cell.setFillColor(SNAKE_HEAD);
                cell.setStrokeColor(new Color(199,237,198));
            } else {
                cell.setFillColor(SNAKE_COLOR); 
                cell.setStrokeColor(new Color(199,237,198));
            }
            this.window.add(cell);;
        }
    }

    public void run() {
        double step = 0.015;
        radius = 150;

        window.animate(() -> {
            degree += step;
            double thisR = radius+Math.sin(8*degree)*25;

            double x = Math.sin(degree)*thisR + 150;
            double y = Math.cos(degree)*thisR + 150;

            snakeMove(x,y);
        });
    }

    protected void snakeMove(double x, double y) {
        Cell current = this.snake.getHead();
        Cell next = current.getNext();
        while (next != null) {
            current.setPosition(next.getX(), next.getY());
            current = next;
            next = current.getNext();
        }
        this.snake.getTail().setPosition(x,y);        
    }

    public static void main(String[] args) {
        new Game();
    }
}
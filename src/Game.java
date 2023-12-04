import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
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
    
    // private static double radius = 0;
    // private static double degree = 0;
    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;

   static char direction = 'U';
   private double[] x = new double[50];
   private double[] y = new double[75];

    public Game() {
        this.window = new CanvasWindow("Snake", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.window.setBackground(new Color(244,197,227));

        this.title = new GraphicsText("Snake!");
        this.title.setFont("Georgia", FontStyle.BOLD, 32);
        this.title.setFillColor(Color.WHITE);
        this.window.add(this.title, 440, 65);

        this.authorText = new GraphicsText("Made by Steph, Nadya & Tu ♥");
        this.authorText.setFont("Georgia", FontStyle.ITALIC, 15);
        this.authorText.setFillColor(Color.WHITE);
        this.window.add(this.authorText, 12, 593);

        this.description = new GraphicsText("Press any key to start.");
        this.description.setFont("Georgia", FontStyle.PLAIN, 15);
        this.description.setFillColor(Color.WHITE);
        this.window.add(this.description, 50, 68);

        this.scoreText = new GraphicsText("Score: null");
        this.scoreText.setFont("Georgia", FontStyle.BOLD, 24);
        this.scoreText.setFillColor(Color.WHITE);
        this.window.add(this.scoreText, 50, 45);

        //key events
        this.window.onKeyDown(event -> {
            if (event.getKey() == Key.UP_ARROW) {
                direction = 'U';
            } else if (event.getKey() == Key.DOWN_ARROW) {
                direction = 'D';
            } else if (event.getKey() == Key.LEFT_ARROW) {
                direction = 'L';
            } else if (event.getKey() == Key.RIGHT_ARROW) {
                direction = 'R';
            }
            // Passed Test
            System.out.println("Direction: " + direction);
        });

        this.board = new Board();
        this.drawBoard(this.board.getCells());
        this.window.add(this.board.getFood());
        this.snake = this.board.getSnake();
        this.drawSnake(this.snake.getSnakeBody());
        this.run();
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
                cell.setFillColor(this.SNAKE_HEAD);
                cell.setStrokeColor(new Color(199,237,198));
            } else {
                cell.setFillColor(this.SNAKE_COLOR); 
                cell.setStrokeColor(new Color(199,237,198));
            }
            this.window.add(cell);;
        }
    }


    protected void snakeMove(double[] x, double[] y) {  
        // Move the body segments
        for (int i = this.snake.getLength() - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
    
        // Move the head based on the current direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }
    }


    public void run() {
        // double step = 0.015;
        // radius = 150;

        this.window.animate(() -> {
            // degree += step;
            // double thisR = radius+Math.sin(8*degree)*25;

            // double x = Math.sin(degree)*thisR + 150;
            // double y = Math.cos(degree)*thisR + 150;

            this.snakeMove(this.x,this.y);
        });
    }

    public static void main(String[] args) {
        new Game();
    }
}
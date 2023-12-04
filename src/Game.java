import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.FontStyle;
import java.util.LinkedList;
import java.awt.Color;

public class Game {

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
    
    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;

    static char direction = 'U';
    private double[] x = new double[50];
    private double[] y = new double[75];

    private Boolean gameStarted = false;

    public Game() {

        this.window = new CanvasWindow("Snake", SCREEN_WIDTH, SCREEN_HEIGHT);

        //key events
        this.window.onKeyDown(event -> {
            if ((event.getKey() == Key.SPACE) && !this.gameStarted) {
                this.gameStarted = true;
                direction = 'U';
            } else if ((event.getKey() == Key.UP_ARROW) && this.gameStarted) {
                direction = 'U';
            } else if ((event.getKey() == Key.DOWN_ARROW) && this.gameStarted) {
                direction = 'D';
            } else if ((event.getKey() == Key.LEFT_ARROW) && this.gameStarted) {
                direction = 'L';
            } else if ((event.getKey() == Key.RIGHT_ARROW) && this.gameStarted) {
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
        this.reset();
        this.run();

        this.setGraphics();

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

    public void snakeMove(int rowChange, int colChange) { 
        Cell current = this.snake.getHead();
        Cell next = current.getNext();
        while (next != null) {
            current.setPosition(next.getX(), next.getY());
            current.setLocation();
            current = next;
            next = current.getNext();
        }
        this.snake.getTail().setRow(this.snake.getTail().getRow() + rowChange);
        this.snake.getTail().setCol(this.snake.getTail().getCol() + colChange);
        this.snake.getTail().setLocation();
    }

    public void snakeMoving() {
        int change = (int) Math.random() * 1;
        int rowChange = (int) Math.random() * 2;
        int colChange = (int) Math.random() * 2;
        
        Cell current = this.snake.getHead();
        Cell next = current.getNext();
        while (next != null) {
            current.setPosition(next.getX(), next.getY());
            current = next;
            next = current.getNext();
        }
        if (change == 0) {
            this.snake.getTail().setRow(this.snake.getTail().getRow() + rowChange - 1);
        } else {
            this.snake.getTail().setCol(this.snake.getTail().getCol() + colChange - 1);
        }
        this.snake.getTail().setLocation();
    }

    public void run() {
        this.window.animate(() -> {
            snakeMoving();
            // switch (direction) {
            //     case 'U':
            //         this.snakeMove(-1,0);
            //         break;
            //     case 'D':
            //         this.snakeMove(1,0);
            //         break;
            //     case 'R':
            //         this.snakeMove(0,1);
            //         break;
            //     case 'L':
            //         this.snakeMove(0,-1);
            //         break;
            // }
        });
    }

    public void reset() {
        this.gameStarted = false;
    }

    
    public void updateScoreText() {
        this.scoreText.setText("Score: " + Integer.toString(this.board.getScore()));
    }

    public void setGraphics() {

        this.window.setBackground(new Color(244,197,227));

        this.title = new GraphicsText("Snake!");
        this.title.setFont("Georgia", FontStyle.BOLD, 32);
        this.title.setFillColor(Color.WHITE);
        this.window.add(this.title, 440, 65);

        this.authorText = new GraphicsText("Made by Steph, Nadya & Tu ♥");
        this.authorText.setFont("Georgia", FontStyle.ITALIC, 15);
        this.authorText.setFillColor(Color.WHITE);
        this.window.add(this.authorText, 12, 593);

        this.description = new GraphicsText("Press spacebar to start.");
        this.description.setFont("Georgia", FontStyle.PLAIN, 15);
        this.description.setFillColor(Color.WHITE);
        this.window.add(this.description, 50, 68);

        this.scoreText = new GraphicsText("Score: " + Integer.toString(this.board.getScore()));
        this.scoreText.setFont("Georgia", FontStyle.BOLD, 24);
        this.scoreText.setFillColor(Color.WHITE);
        this.window.add(this.scoreText, 50, 45);

    }

    public static void main(String[] args) {
        new Game();
    }

}
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
    
    static char direction = 'U';
   
    private Boolean inGame = false;

    public Game() {

        this.window = new CanvasWindow("Snake", SCREEN_WIDTH, SCREEN_HEIGHT);

        setupBoard();
            
        this.reset();
        this.run();

        this.setGraphics();

        //key events
        this.window.onKeyDown(event -> {
            if ((event.getKey() == Key.SPACE) && !inGame) {
                this.inGame = true;
                direction = 'U';
            } else if ((event.getKey() == Key.UP_ARROW) && (inGame) && (direction != 'D')) {
                direction = 'U';
            } else if ((event.getKey() == Key.DOWN_ARROW) && (inGame) && (direction != 'U')) {
                direction = 'D';
            } else if ((event.getKey() == Key.LEFT_ARROW) && inGame) {
                direction = 'L';
            } else if ((event.getKey() == Key.RIGHT_ARROW) && inGame) {
                direction = 'R';
            }
            System.out.println("Direction: " + direction);
        });
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

    public void snakeMove() { 

        Cell current = this.snake.getHead();
        Cell next = current.getNext();
        while (next != null) {
            current.setPosition(next.getX(), next.getY());
            current = next;
            next = current.getNext();
        }

        System.out.println(inGame);
        if (inGame) {
            switch (direction) {
                case 'U':
                    current.setCol(current.getCol() - 1);
                    break;
                case 'D':
                    current.setCol(current.getCol() + 1);
                    break;
                case 'L':
                    current.setRow(current.getRow() - 1);
                    break;
                case 'R':
                    current.setRow(current.getRow() + 1);
                    break;    
            }
            current.setLocation();

            //set this here if you want to change speed of snake movement:
            window.pause(120);
            endGame();
        }
    }

    public void endGame() {
        Cell head = this.snake.getHead();
        Cell current = this.snake.getHead().getNext();
        // hits itself
        while (current != null) {
            if (head.getCol() == current.getCol() && head.getRow() == current.getRow()) {
                this.inGame = false;
            }
            current = current.getNext();
        }
        // hit walls
        if (head.getCol() > 20 || head.getRow() > 20) {
            this.inGame = false;
        }
        if (head.getCol() < 0 || head.getRow() < 0) {
            this.inGame = false;
        }
        // die
        if (this.snake.getLength() == 0) {
            this.inGame = false;
        }
    }

    public void run() {
        this.window.animate(() -> {
            //while (this.inGame) {
                this.snakeMove();
            //}
        });
    }

    public void reset() {
        this.inGame = false;
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

    public void setupBoard() {
        this.board = new Board();
        this.drawBoard(this.board.getCells());
        this.window.add(this.board.getFood());
        this.snake = this.board.getSnake();
        this.drawSnake(this.snake.getSnakeBody());
    }

    public static void main(String[] args) {
        new Game();
    }

}
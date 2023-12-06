import java.awt.Color;
import java.util.LinkedList;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class Game {

    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 600;
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
    private GraphicsText losingText;
    private GraphicsText losingText2;
    private GraphicsText restartText;

    private Image losingImage;

    private Rectangle losingRectangle;

    static char direction = 'U';
    private Boolean inGame = false;
    private int score = 0;

    public Game() {

        this.window = new CanvasWindow("Snake", SCREEN_WIDTH, SCREEN_HEIGHT);

        this.setupBoard();
        this.setGraphics();

        //key events
        this.window.onKeyDown(event -> {
            if ((event.getKey() == Key.SPACE) && !this.inGame) {
                this.inGame = true;
                direction = 'U';

            } else if ((event.getKey() == Key.UP_ARROW || event.getKey() == Key.W) 
            && (this.inGame) && (direction != 'D')) {
                direction = 'U';

            } else if ((event.getKey() == Key.DOWN_ARROW || event.getKey() == Key.S) 
            && (this.inGame) && (direction != 'U')) {
                direction = 'D';

            } else if ((event.getKey() == Key.LEFT_ARROW || event.getKey() == Key.A) 
            && this.inGame) {
                direction = 'L';

            } else if ((event.getKey() == Key.RIGHT_ARROW || event.getKey() == Key.D) 
            && this.inGame) {
                direction = 'R';
            }
        });

        this.window.onKeyDown(event -> {
            if (event.getKey() == Key.SHIFT && (!this.inGame)) {
                this.reset();
            }
        });

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
            if (i == snakeBody.size() - 1) {
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
        this.eatFood();
        this.hitRock();
        this.endGame();
        if (this.inGame) {
            Cell current = this.snake.getTail();
            Cell previous = current.getPrevious();
            while (previous != null) {
                current.setPosition(previous.getX(), previous.getY());
                current = previous;
                previous = current.getPrevious();
            }
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
            this.window.pause(100);
        }
    }

    public void endGame() {
        // hit walls
        Cell head = this.snake.getHead();
        
        //  hitting walls
        boolean hitWalls = head.getX() < 75 || head.getX() > 500 || 
        head.getY() < 100 || head.getY() > 525;

        // snake length is zero
        boolean snakeLengthZero = this.snake.getLength() == 0;

        // score is negative
        boolean score = this.score < 0;

        // snake hits self
        boolean snakeHits = false;
        for(Cell bodyPart: this.snake.getSnakeBody()){
            if(head!= bodyPart && head.getX() == bodyPart.getX() && head.getY() == bodyPart.getY()){
                snakeHits = true;
                break;
            } 
        }
        if (hitWalls || snakeLengthZero|| snakeHits || score) {
            this.window.add(this.losingRectangle);
            this.window.add(this.losingText);
            this.window.add(this.losingText2);
            this.window.add(this.losingImage);
            this.window.add(this.restartText);
            this.window.draw();
            this.inGame = false;
        }      
    }

    public void reset() {
        this.inGame = false;
        this.score = 0;
        this.window.removeAll();  // Clear the window before setting up new graphics
        this.setupBoard();
        this.setGraphics();
    }

    public void eatFood() {
        Cell head = this.snake.getHead();
        Food food = this.board.getFood();
        if (head.getX() - food.getX() == 0 && head.getY() - food.getY() == 0) {
            this.board.generateFood();
            this.score++;
            this.updateScoreText();    
            this.snake.addToTail();
            this.snake.getTail().setFillColor(this.SNAKE_COLOR); 
            this.snake.getTail().setStrokeColor(new Color(199,237,198));
            this.window.add(this.snake.getTail());
        }
    }

    public void hitRock() {
        Cell head = this.snake.getHead();
        Rock rock1 = this.board.getRock1();
        Rock rock2 = this.board.getRock2();
        if (head.getX() == rock1.getX() && head.getY() == rock1.getY()) {
            this.board.generateRock1();
            this.score--;
            this.updateScoreText();
            this.window.remove(this.snake.getTail());
            this.snake.removeFromTail();
        }
        if (head.getX() == rock2.getX() && head.getY() == rock2.getY()) {
            this.board.generateRock2();
            this.score--;
            this.updateScoreText();
            this.window.remove(this.snake.getTail());
            this.snake.removeFromTail();
        }
    }

    public void run() {
        SoundHandler.runMusic("res/down.wav");
        this.window.animate(() -> {
            this.snakeMove();
        });
    }

    public void updateScoreText() {
        this.scoreText.setText("Score: " + Integer.toString(this.score));
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

        this.scoreText = new GraphicsText("Score: " + Integer.toString(this.score));
        this.scoreText.setFont("Georgia", FontStyle.BOLD, 24);
        this.scoreText.setFillColor(Color.WHITE);
        this.window.add(this.scoreText, 50, 45);

        this.losingRectangle = new Rectangle(0, 0, 350, 350);
        this.losingRectangle.setCenter(300, 330);
        this.losingRectangle.setFillColor(new Color(208,255,240));
        this.losingRectangle.setStrokeColor(new Color(208,255,240));

        this.losingText = new GraphicsText("GAME OVER!");
        this.losingText.setFont("Georgia", FontStyle.BOLD, 32);
        this.losingText.setFillColor(new Color(196,186,244));
        this.losingText.setCenter(300,205);

        this.losingText2 = new GraphicsText("better luck next time lol");
        this.losingText2.setFont("Georgia", FontStyle.ITALIC, 24);
        this.losingText2.setFillColor(new Color(196, 186, 244));
        this.losingText2.setCenter(300, 235);

        this.losingImage = new Image(0, 0);
        this.losingImage.setMaxHeight(200);
        this.losingImage.setMaxWidth(200);
        this.losingImage.setImagePath("dead.jpg");
        this.losingImage.setCenter(300, 355);

        this.restartText = new GraphicsText("press 'shift' to restart");
        this.restartText.setFont("Georgia", FontStyle.PLAIN, 18);
        this.restartText.setFillColor(new Color(196, 186, 244));
        this.restartText.setCenter(300, 470);
    }

    public void setupBoard() {
        this.board = new Board();
        this.drawBoard(this.board.getCells());
        this.window.add(this.board.getFood());
        this.window.add(this.board.getRock1());
        this.window.add(this.board.getRock2());
        this.snake = this.board.getSnake();
        this.drawSnake(this.snake.getSnakeBody());
    }

    public static void main(String[] args) {
        new Game();
    }

}
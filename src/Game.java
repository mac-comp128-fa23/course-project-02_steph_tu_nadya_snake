import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.Image;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import java.awt.Color;

public class Game {

    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 600;
    private CanvasWindow window;

    private GraphicsText title;
    private GraphicsText authorText;
    private GraphicsText description;
    private GraphicsText scoreText;
    private GraphicsText losingText;
    private GraphicsText losingText2;
    private Image losingImage;

    public final Color GRID1 = new Color(248,251,247);
    public final Color GRID2 = new Color(246,238,248);
    private Board board;

    public final Color SNAKE_HEAD = new Color(127,206,131);
    public final Color SNAKE_COLOR = new Color(176,230,175);
    private Snake snake;
    
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
            } else if ((event.getKey() == Key.UP_ARROW) && (this.inGame) && (direction != 'D')) {
                direction = 'U';
            } else if ((event.getKey() == Key.DOWN_ARROW) && (this.inGame) && (direction != 'U')) {
                direction = 'D';
            } else if ((event.getKey() == Key.LEFT_ARROW) && this.inGame) {
                direction = 'L';
            } else if ((event.getKey() == Key.RIGHT_ARROW) && this.inGame) {
                direction = 'R';
            }
            System.out.println("Direction: " + direction);
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
        this.eatFood();
        this.endGame();
        if (this.inGame) {
            Cell current = this.snake.getHead();
            Cell next = current.getNext();
            while (next != null) {
                current.setPosition(next.getX(), next.getY());
                current = next;
                next = current.getNext();
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
            this.window.pause(120);
        }
    }

    public void endGame() {
        // hit walls
        Cell head = this.snake.getHead();
        
        //  hitting walls
        boolean hitWalls = head.getX() <= 100 || head.getX() >= 475 || head.getY() <= 125 || head.getY() >= 500;

        // snake length is zero
        boolean snakeLengthZero = this.snake.getLength() == 0;

        if (hitWalls || snakeLengthZero) {
            this.handleGameOver();
        }
    }
        // Cell head = this.snake.getHead();
        // if (head.getX() <= 100 || head.getX() >= 475) {
        //     this.inGame = false;
        //     this.window.add(this.losingText);
        //     this.window.add(this.losingText2);
        //     this.window.add(this.losingImage);
        // }
        // if (head.getY() <= 125 || head.getY() >= 500) {
        //     this.inGame = false;
        //     this.window.add(this.losingText);
        //     this.window.add(this.losingText2);
        //     this.window.add(this.losingImage);
        // }
        // // die
        // if (this.snake.getLength() == 0) {
        //     this.inGame = false;
        //     this.window.add(this.losingText);
        //     this.window.add(this.losingText2);
        //     this.window.add(this.losingImage);
        // }
    // }


    private void handleGameOver() {
        // this.window.add(this.losingText);
        this.inGame = false;
        int earnedPoints = 0; // define a method to calculate points
        boolean playAgain = this.showGameOverDialog(earnedPoints);
        if (playAgain) {
            System.out.println("play again");
            this.reset();
        } else {
            this.inGame = false;
            this.window.add(this.losingText);
            this.window.closeWindow();
        }
    }
    

    // private int calculatePoints() {
        
    //     // You need to implement the logic to calculate points based on the game state
    //     // For now, let's assume you have a variable named points
    //     return points;
    // }
    
    private boolean showGameOverDialog(int earnedPoints) {
        return JOptionPane.showConfirmDialog(null,
                "Game Over! You earned " + earnedPoints + " points!\nPlay again?",
                "Game Over!", JOptionPane.YES_NO_OPTION, 0) == 0;
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
            this.snake.addToTail();
            this.board.generateFood();
            this.score++; 
        }
    }

    public void run() {
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
        this.losingImage.setImagePath("cat3.jpeg");
        this.losingImage.setCenter(300, 355);

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
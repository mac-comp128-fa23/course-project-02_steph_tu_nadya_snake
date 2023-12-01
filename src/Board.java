import java.awt.Color;

public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    private Snake snake;
    private Food food;
    private int score;
    public boolean endGame;

    public Board() {
        cells = new Cell[ROW][COL]; 
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setType("empty");
            }
        }

        Cell snakeHead = new Cell(0, 0);
        snake = new Snake(snakeHead);
        food = new Food();
        endGame = false;
        generateFood();
        food.setFillColor(Color.pink);
        food.setFilled(true);
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Food getFood() {
        return this.food;
    }

    public int getScore() {
        return this.score;
    }

    public void eatFood() {
        if (snake.getHead().getCol() == food.getCol() && snake.getHead().getRow() == food.getRow()) {
            score = score + 1;
            generateFood();
        }
    }

    public void endGame() {
        Cell head = snake.getHead();
        Cell current = snake.getHead().getNext();
        // hits itself
        while (current != null) {
            if (head.getCol() == current.getCol() && head.getRow() == current.getRow()) {
                endGame = true;
            }
            current = current.getNext();
        }
        // hit walls
        if (head.getCol() > 20 || head.getRow() > 20) {
            endGame = true;
        }
        if (head.getCol() < 0 || head.getRow() < 0) {
            endGame = true;
        }
        // die
        if (snake.getTailLength() == 0) {
            endGame = true;
        }
    }

    public void generateFood(){
        boolean newFood = false;
        while (!newFood && !endGame) {
            int row = (int) (Math.random() * ROW);
            int col = (int) (Math.random() * COL);
            if (cells[row][col].getType() != "snake" && cells[row][col].getType() != "food") {
                food.setCol(col);
                food.setRow(row);
                food.setLocation();
                food.setType("food");
                newFood = true;
            }
        }
    }
}

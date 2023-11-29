import java.util.Iterator;

public class Board {
    private Cell[][] cells;
    final int ROW = 20;
    final int COL = 20;
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

        Cell snakeHead = new Cell(10, 10);
        snake = new Snake(snakeHead);
        food = new Food();
        endGame = false;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public void eatFood() {
        //if (snake.getHead().getCol() - food.) {
            score = score + 1;
            generateFood();
    }

    public void hit() {
        Cell current = snake.getHead();
        //while ()
        endGame = true;
    }

    public void generateFood(){
        boolean newFood = false;
        while (!newFood) {
            int row = (int) (Math.random() * ROW);
            int col = (int) (Math.random() * COL);
            if (cells[row][col].getType() != "snake" && cells[row][col].getType() != "food") {
                cells[row][col].setType("food");
                newFood = true;
            }
        }
    }
}

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
        this.cells = new Cell[this.ROW][this.COL]; 
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = new Cell(i, j);
                this.cells[i][j].setType("empty");
            }
        }

        Cell snakeHead = new Cell(this.ROW / 2, this.COL / 2); 
        this.snake = new Snake(snakeHead, this.cells, 3);
        this.food = new Food();
        this.endGame = false;
        this.generateFood();
        this.food.setFillColor(Color.pink);
        this.food.setFilled(true);
        this.snake.draw();

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
        if (this.snake.getHead().getCol() == this.food.getCol() && this.snake.getHead().getRow() == this.food.getRow()) {
            this.score = this.score + 1;
            this.generateFood();
        }
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void endGame() {
        Cell head = this.snake.getHead();
        Cell current = this.snake.getHead().getNext();
        // hits itself
        while (current != null) {
            if (head.getCol() == current.getCol() && head.getRow() == current.getRow()) {
                this.endGame = true;
            }
            current = current.getNext();
        }
        // hit walls
        if (head.getCol() > 20 || head.getRow() > 20) {
            this.endGame = true;
        }
        if (head.getCol() < 0 || head.getRow() < 0) {
            this.endGame = true;
        }
        // die
        if (this.snake.getTailLength() == 0) {
            this.endGame = true;
        }
    }

    public void generateFood(){
        boolean newFood = false;
        while (!newFood && !this.endGame) {
            int row = (int) (Math.random() * this.ROW);
            int col = (int) (Math.random() * this.COL);
            if (this.cells[row][col].getType() != "snake" && this.cells[row][col].getType() != "food") {
                this.food.setCol(col);
                this.food.setRow(row);
                this.food.setLocation();
                this.food.setType("food");
                newFood = true;
            }
        }
    }
}
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

        this.food = new Food();
        this.generateFood();

        this.snake = new Snake(this.cells, 3);

        this.score = 0;
        this.endGame = false;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Food getFood() {
        return this.food;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getEndGame() {
        return this.endGame;
    }

    public void generateFood(){
        this.food.resetCell();
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
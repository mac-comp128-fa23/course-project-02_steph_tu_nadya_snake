public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    private Snake snake;
    private Food food;

    public Board() {
        this.cells = new Cell[this.ROW][this.COL];
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = new Cell(i, j);
                this.cells[i][j].setType("empty");
            }
        }

        this.snake = new Snake(this.cells[10][10]); //generates the snake in this cell
        this.food = new Food();
    }

    public void moveSnake() {}

    public Cell[][] getCells() {
        return this.cells;
    }

    public void eatFood() {
        //if snake eats current food
        this.generateFood();
    }

    public void generateFood(){
        int row = (int) (Math.random() * this.ROW);
        int col = (int) (Math.random() * this.COL);
        if (this.cells[row][col].getType() != "snake" && this.cells[row][col].getType() != "food") {
            this.cells[row][col].setType("food");
        }
    }
}

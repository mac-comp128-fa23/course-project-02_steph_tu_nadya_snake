public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    
    private Food food;
    private Snake snake;

    public Board() {
        this.cells = new Cell[this.ROW][this.COL]; 
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }

        this.food = new Food();
        this.generateFood();
        this.snake = new Snake(this.cells, 3);
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

    public void generateFood(){
        int row = (int) (Math.random() * this.ROW);
        int col = (int) (Math.random() * this.COL);
        this.food.setCol(col);
        this.food.setRow(row);
        this.food.setLocation();
    }
}
public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    private Snake snake;
    private Food food;

    public Board() {
        cells = new Cell[ROW][COL];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setType("empty");
            }
        }

        snake = new Snake();
        food = new Food();
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public void eatFood() {
        //if snake eats current food
        generateFood();
    }

    public void generateFood(){
        int row = (int) (Math.random() * ROW);
        int col = (int) (Math.random() * COL);
        if (cells[row][col].getType() != "snake" && cells[row][col].getType() != "food") {
            cells[row][col].setType("food");
        }
    }
}

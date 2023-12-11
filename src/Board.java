import javax.swing.plaf.synth.SynthSplitPaneUI;

/**
 * Representation of Snake's gameboard.
 */
public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    
    private Food food;
    private Rock rock1;
    private Rock rock2;
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
        this.rock1 = new Rock();
        this.rock2 = new Rock();
        this.generateRock1();
        this.generateRock2();
        this.snake = new Snake(this.cells, 3);
    }

    /**
     * Generates a rock cell type in a random spot on the board.
     */
    public void generateRock1(){
        this.cells[this.rock1.getRow()][this.rock1.getCol()].setType("");
        int row = (int) (Math.random() * (this.ROW - 1));
        int col = (int) (Math.random() * (this.COL - 1));
        boolean newRock = false;
        while (!newRock) {
            row = (int) (Math.random() * (this.ROW - 1));
            col = (int) (Math.random() * (this.COL - 1));
            if (row != 0 && col != 0 && row != this.rock2.getRow() && col != this.rock2.getCol() && this.cells[row][col].getType() != "rock" && this.cells[row][col].getType() != "food") {        
                this.rock1.setCol(col);
                this.rock1.setRow(row);
                this.rock1.setLocation();
                this.rock1.setType("rock");
                newRock = true;
            }
        }
    }

    /**
     * Generates a second rock cell type in a random spot on the board.
     */
    public void generateRock2(){
        this.cells[this.rock2.getRow()][this.rock2.getCol()].setType("");
        int row = (int) (Math.random() * (this.ROW - 1));
        int col = (int) (Math.random() * (this.COL - 1));
        boolean newRock = false;
        while (!newRock) {
            row = (int) (Math.random() * (this.ROW - 1));
            col = (int) (Math.random() * (this.COL - 1));
            if (row != 0 && col != 0 && row != this.rock1.getRow() && col != this.rock1.getCol() && this.cells[row][col].getType() != "rock" && this.cells[row][col].getType() != "food") {        
                this.rock2.setCol(col);
                this.rock2.setRow(row);
                this.rock2.setLocation();
                this.rock2.setType("rock");
                newRock = true;
            }
        }
    }

    /**
     * Generates a food cell type in a random spot on the board.
     */
    public void generateFood(){
        this.cells[this.food.getRow()][this.food.getCol()].setType("");
        int row = (int) (Math.random() * (this.ROW - 1));
        int col = (int) (Math.random() * (this.COL - 1));
        boolean newFood = false;
        while (!newFood) {
            row = (int) (Math.random() * (this.ROW - 1));
            col = (int) (Math.random() * (this.COL - 1));
            if (row != 0 && col != 0 && this.cells[row][col].getType() != "rock") {
                this.food.setCol(col);
                this.food.setRow(row);
                this.food.setLocation();
                this.food.setType("food");
                newFood = true;
            }
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Food getFood() {
        return this.food;
    }

    public Rock getRock1() {
        return this.rock1;
    }

    public Rock getRock2() {
        return this.rock2;
    }

    public Snake getSnake() {
        return this.snake;
    }

}
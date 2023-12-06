public class Board {
    final int ROW = 20;
    final int COL = 20;
    private Cell[][] cells;
    
    private Food food;
    private Rock rock;
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
        this.rock = new Rock();
        this.generateRock();
        this.snake = new Snake(this.cells, 3);
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Food getFood() {
        return this.food;
    }

    public Rock getRock() {
        return this.rock;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void generateRock(){
        int row = (int) (Math.random() * this.ROW);
        int col = (int) (Math.random() * this.COL);
        boolean newRock = false;
        while (!newRock) {
            if (this.cells[row][col].getType() != "rock") {        
                this.rock.setCol(col);
                this.rock.setRow(row);
                this.rock.setLocation();
                this.rock.setType("rock");
                newRock = true;
            }
        }
    }

    public void generateFood(){
        int row = (int) (Math.random() * this.ROW);
        int col = (int) (Math.random() * this.COL);
        boolean newFood = false;
        while (!newFood) {
            if (this.cells[row][col].getType() != "rock") {
                this.food.setCol(col);
                this.food.setRow(row);
                this.food.setLocation();
                this.food.setType("food");
                newFood = true;
            }
        }
    }
}
package gameoflife;

public class Board {
    // === === === === === FIELDS === === === === ===//
    private int width;
    private int height;
    private int[][] board;

    // === === === === === CONSTRUCTOR === === === === ===//
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
    }

    // === === === === === GETTERS/SETTERS === === === === ===//
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    // === === === === === METHODS === === === === ===//
    public void print() {
        for (int[] row : board) {
            String visualRow = "|";

            for (int cell : row) {
                if (cell == 0) {
                    visualRow += ".";
                } else {
                    visualRow += "*";
                }
            }

            visualRow += "|";
            System.out.println(visualRow);
        }
        System.out.println("---\n");
    }

    public void setAlive(int x, int y) {
        this.setCellState(x, y, 1);
    }

    public void setDead(int x, int y) {
        this.setCellState(x, y, 0);
    }

    public int totalAliveNeighbors(int x, int y) {
        int total = 0;

        // Top
        total += isCellAlive(x - 1, y - 1);
        total += isCellAlive(x, y - 1);
        total += isCellAlive(x + 1, y - 1);
        // Middle
        total += isCellAlive(x - 1, y);
        total += isCellAlive(x + 1, y);
        // Bottom
        total += isCellAlive(x - 1, y + 1);
        total += isCellAlive(x, y + 1);
        total += isCellAlive(x + 1, y + 1);

        return total;
    }

    public int isCellAlive(int x, int y) {
        if (y < 0 || y >= height) {
            return 0;
        }

        if (x < 0 || x >= width) {
            return 0;
        }

        return this.board[y][x];
    }

    public void setCellState(int x, int y, int state) {
        if (y < 0 || y >= height) {
            return;
        }

        if (x < 0 || x >= width) {
            return;
        }

        this.board[y][x] = state;
    }

    public void next() {
        int xindex = 0;
        int yindex = 0;

        int[][] nextBoard = new int[height][width];

        for (int[] row : board) {
            for (int cell : row) {
                int aliveNeighbors = totalAliveNeighbors(xindex, yindex);
                int currentCell = nextBoard[yindex][xindex]; // Next board's current cell

                if (isCellAlive(xindex, yindex) == 1) {
                    if (aliveNeighbors < 2) {
                        currentCell = 0;
                    } else if (aliveNeighbors > 1 && aliveNeighbors < 4) {
                        currentCell = 1;
                    } else if (aliveNeighbors > 3) {
                        currentCell = 0;
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        currentCell = 1;
                    }
                }

                nextBoard[yindex][xindex] = currentCell;
                xindex += 1;
            }
            yindex += 1;
            xindex = 0;
        }

        this.board = nextBoard;
    }

}

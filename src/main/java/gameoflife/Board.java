package gameoflife;

public class Board {
    // === === === === === FIELDS === === === === ===//
    private int width;
    private int height;
    private int[][] board;
    private int generationcount;
    private int dead;
    private int alive;

    // === === === === === CONSTRUCTOR === === === === ===//
    public Board(int width, int height, int count) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
        this.generationcount = count;
        this.dead = 0;
        this.alive = 1;
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

    public int getGenerationcount() {
        return generationcount;
    }

    public void setGenerationcount(int generationCount) {
        this.generationcount = generationCount;
    }

    // === === === === === METHODS === === === === ===//
    public void print() {
        for (int[] row : board) {
            String visualRow = "|";

            for (int cell : row) {
                if (cell == dead) {
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
        this.setCellState(x, y, alive);
    }

    public void setDead(int x, int y) {
        this.setCellState(x, y, dead);
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
            return dead;
        }

        if (x < 0 || x >= width) {
            return dead;
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

                if (isCellAlive(xindex, yindex) == alive) {
                    if (aliveNeighbors < 2) {
                        currentCell = dead;
                    } else if (aliveNeighbors > 1 && aliveNeighbors < 4) {
                        currentCell = alive;
                    } else if (aliveNeighbors > 3) {
                        currentCell = dead;
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        currentCell = alive;
                    }
                }

                nextBoard[yindex][xindex] = currentCell;
                xindex += 1;
            }
            yindex += 1;
            xindex = 0;
        }
        this.generationcount += 1;
        this.board = nextBoard;
    }

    public static Board copyBoard(Board board) {
        Board copyBoard =
                new Board(board.getWidth(), board.getHeight(), board.getGenerationcount());

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                copyBoard.setCellState(x, y, board.isCellAlive(x, y));
            }
        }

        return copyBoard;
    }

}

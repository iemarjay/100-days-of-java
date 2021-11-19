import java.util.Arrays;

public class GameOfLife {
    private final int[][] grid;
    private final int rows;
    private final int columns;
    public final static int DEAD_CELL = 0;
    public final static int LIVING_CELL = 1;

    public static void main(String[] args) {
    }

    public GameOfLife(int rows, int columns) {
        grid = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;

        setInitialGrid(rows, columns);
    }

    public GameOfLife(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public void setInitialGrid(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = (int) Math.round(Math.random());
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public int liveNeighborsCount(int y, int x) {
        int liveNeighborsCount = 0;

        for (int col = x - 1; col <= (x + 1); col += 1) {

            for (int row = y - 1; row <= (y + 1); row += 1) {

                if (!((col == x) && (row == y)) && cellIsInGrid(col, row)) {
                    liveNeighborsCount += grid[row][col];
                }
            }
        }

        return liveNeighborsCount;
    }

    private boolean cellIsInGrid(int y, int x) {
        return y >= 0 && x >= 0 && y < rows && x < columns;
    }

    public boolean cellIsAlive(int y, int x) {
        return grid[y][x] == LIVING_CELL;
    }

    public GameOfLife nextGen() {
        int[][] nextGenerationGrid = new int[rows][columns];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (cellIsAlive(y, x) && (liveNeighborsCount(y, x) < 2 || liveNeighborsCount(y, x) > 3)) {
                    nextGenerationGrid[y][x] = DEAD_CELL;
                } else if (!cellIsAlive(y, x) && liveNeighborsCount(y, x) == 3) {
                    nextGenerationGrid[y][x] = LIVING_CELL;
                } else if (cellIsAlive(y, x)) {
                    nextGenerationGrid[y][x] = LIVING_CELL;
                }
            }
        }

        return new GameOfLife(nextGenerationGrid);
    }

}

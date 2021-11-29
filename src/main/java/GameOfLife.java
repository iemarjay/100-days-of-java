import java.util.Arrays;

public class GameOfLife {
    private final int[][] grid;
    private final int rows;
    private final int columns;
    public final static int DEAD_CELL = 0;
    public final static int LIVING_CELL = 1;

    private int[][] nextGenerationGrid;


    public static void main(String[] args) {

    }

    public GameOfLife(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public GameOfLife nextGen() {
        this.nextGenerationGrid = new int[rows][columns];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (isAliveNotLonelyAndNotOverCrowded(y, x)) {
                    killCellOnNextGrid(y, x);
                } else if (isDeadButHasThreeLivingNeighbors(y, x) || cellIsAlive(y, x)) {
                    wakeCellOnNextGrid(y, x);
                }
            }
        }

        return new GameOfLife(nextGenerationGrid);
    }

    public int[][] getGrid() {
        return grid;
    }

    private boolean cellIsAlive(int y, int x) {
        return grid[y][x] == LIVING_CELL;
    }

    private int liveNeighborsCount(int y, int x) {
        int liveNeighborsCount = 0;

        for (int col = x - 1; col <= (x + 1); col += 1) {
            for (int row = y - 1; row <= (y + 1); row += 1) {
                if (!(col == x && row == y) && cellIsInGrid(col, row)) {
                    liveNeighborsCount += grid[row][col];
                }
            }
        }

        return liveNeighborsCount;
    }

    private boolean cellIsInGrid(int y, int x) {
        return y >= 0 && x >= 0 && y < rows && x < columns;
    }

    private void wakeCellOnNextGrid(int y, int x) {
        nextGenerationGrid[y][x] = LIVING_CELL;
    }

    private void killCellOnNextGrid(int y, int x) {
        nextGenerationGrid[y][x] = DEAD_CELL;
    }

    private boolean isDeadButHasThreeLivingNeighbors(int y, int x) {
        return !cellIsAlive(y, x) && liveNeighborsCount(y, x) == 3;
    }

    private boolean isAliveNotLonelyAndNotOverCrowded(int y, int x) {
        return cellIsAlive(y, x) && (liveNeighborsCount(y, x) < 2 || liveNeighborsCount(y, x) > 3);
    }

}

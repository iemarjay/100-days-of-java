import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GameOfLifeTest {

    @Test
    public void cellsWithLesserThanTwoLiveNeighborsDies() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(cellIsAlive(next.getGrid(), 1, 0)).isFalse();

        assertThat(cellIsAlive(next.getGrid(), 1, 2)).isFalse();
    }

    @Test
    public void cellsWithMoreThanThreeLiveNeighborsDies() {
        int [][] grid = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(cellIsAlive(next.getGrid(), 1, 1)).isFalse();
        assertThat(cellIsAlive(next.getGrid(), 1, 2)).isFalse();
    }


    @Test
    public void cellsWithTwoOrThreeLiveNeighborsLives() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(cellIsAlive(next.getGrid(), 2, 2)).isTrue();
        assertThat(cellIsAlive(next.getGrid(), 1, 0)).isTrue();
    }


    @Test
    public void deadCellsWithExactlyThreeNeighborsLives() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(cellIsAlive(next.getGrid(), 2, 2)).isTrue();
        assertThat(cellIsAlive(next.getGrid(), 1, 0)).isTrue();
    }

    private static boolean cellIsAlive(int[][] grid, int y, int x) {
        return grid[y][x] == 1;
    }

}

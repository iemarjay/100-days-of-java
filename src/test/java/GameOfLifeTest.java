import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GameOfLifeTest {
    @Test
    public void canGetNeighborsCount() {
        int [][] grid = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife instance = new GameOfLife(grid);

        assertThat(instance.liveNeighborsCount(1,1)).isEqualTo(5);
        assertThat(instance.liveNeighborsCount(0,1)).isEqualTo(3);
        assertThat(instance.liveNeighborsCount(0,0)).isEqualTo(3);
        assertThat(instance.liveNeighborsCount(1,2)).isEqualTo(4);
    }

    @Test
    public void cellsWithLesserThanTwoLiveNeighborsDies() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(next.cellIsAlive(1, 0)).isFalse();

        assertThat(next.cellIsAlive(1, 2)).isFalse();
    }

    @Test
    public void cellsWithMoreThanThreeLiveNeighborsDies() {
        int [][] grid = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(next.cellIsAlive(1, 1)).isFalse();
        assertThat(next.cellIsAlive(1, 2)).isFalse();
    }


    @Test
    public void cellsWithTwoOrThreeLiveNeighborsLives() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(next.cellIsAlive(2, 2)).isTrue();
        assertThat(next.cellIsAlive(1, 0)).isTrue();
    }


    @Test
    public void cellsNeighborsLives() {
        int [][] grid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 1},
        };

        GameOfLife next = new GameOfLife(grid).nextGen();

        assertThat(next.cellIsAlive(2, 2)).isTrue();
        assertThat(next.cellIsAlive(1, 0)).isTrue();
    }

}

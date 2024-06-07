package core.game_panels.dungeon.room.room_utilities.roomfactory;

import core.game_panels.dungeon.mechanics.collision.CollisionChecker;
import core.game_panels.dungeon.room.tile.Tile;
import core.game_panels.dungeon.room.tile.TileGrid;
import core.utilities.Factory;

class CollisionCheckerFactory extends Factory<CollisionChecker>{

    CollisionChecker getCollisionChecker(TileGrid tileGrid) {
        return new CollisionChecker(getCollisionArray(tileGrid.getN(), tileGrid.getM(), tileGrid.getTileGrid()));
    }

    private boolean[][] getCollisionArray(int N, int M, Tile[][][] tileGrid) {
        boolean[][] collision = new boolean[N][M];
        for (int i = 0; i < tileGrid.length; i++) {
            if (i == 2) {
                continue;
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (tileGrid[i][r][c] == null) {
                        continue;
                    }
                    collision[r][c] |= tileGrid[i][r][c].getCollision();
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (tileGrid[2][r][c] == null) {
                    continue;
                }
                collision[r][c] = tileGrid[2][r][c].getCollision();
            }
        }
        return collision;
    }
}
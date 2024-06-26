package game.dungeon.room_factory;

import java.util.Arrays;
import java.util.HashSet;

import game.dungeon.room.tile.Tile;
import game.dungeon.room.tile.TileGrid;
import game.game_components.Factory;
import game.utilities.ImageUtilities;

class TileGridFactory extends Factory<TileGrid> {
    private static final String name = "dungeonTileSet";
    private static final int layers = 5;
    private static final int tileN = 18;
    private static final int tileM = 16;
    private static final Tile tileMap[][] = new Tile[tileN][tileM];
    private static final HashSet<Integer> collisionTiles = new HashSet<>(
            Arrays.asList(0, 1, 2, 3, 4, 18, 20, 21, 22, 36, 37, 38, 54, 55, 56, 57, 58, 72, 74, 75, 76, 90, 91, 92, 93,
                    94, 108, 109, 110, 126, 127, 128, 129, 130, 144, 145, 146, 147, 148, 162, 163, 164, 214, 215, 286,
                    287));

    TileGridFactory() {
        for (int r = 0; r < tileN; r++) {
            for (int c = 0; c < tileM; c++) {
                tileMap[r][c] = getTile(r, c);
            }
        }
    }

    TileGrid createGrid(RoomFileData file) {
        return new TileGrid(createGrid(file.getN(), file.getM(), file.getTileGrid()));
    }

    private Tile[][][] createGrid(int N, int M, int arr[][]) {
        int arr2[][][] = new int[layers][N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                arr2[0][r][c] = 204;
            }
        }
        for (int i = 1; i < layers; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    arr2[i][r][c] = -1;
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                layer0(arr, arr2[0], r, c);
                layer1(arr, arr2[1], r, c);
                layer2(arr, arr2[2], r, c);
                layer3(arr, arr2[3], r, c);
                layer4(arr, arr2[4], r, c);
            }
        }
        Tile[][][] res = new Tile[layers][N][M];
        for (int i = 0; i < layers; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (arr2[i][r][c] != -1) {
                        res[i][r][c] = tileMap[arr2[i][r][c] % tileN][arr2[i][r][c] / tileN];
                    } else {
                        res[i][r][c] = null;
                    }
                }
            }
        }
        return res;
    }

    private static final int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final int direct2[][] = { { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

    private Tile getTile(int r, int c) {
        // the r and c are backwards because I messed something up and it would take 6
        // hours to fix so i'm keeping it like this
        return new Tile(ImageUtilities.getImage("tiles", name, c, r), collisionTiles.contains(tileN * c + r));
    }

    private static final HashSet<Integer> layer0 = new HashSet<>(Arrays.asList(5, 6));

    private void layer0(int[][] arr, int[][] arr2, int r, int c) {
        if (!layer0.contains(arr[r][c])) {
            return;
        }
        int cnt = 0;
        for (int d[] : direct) {
            if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                cnt++;
            } else if (layer0.contains(arr[r + d[0]][c + d[1]])) {
                cnt++;
            }
        }
        int cnt2 = 0;
        for (int d[] : direct2) {
            if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                cnt2++;
            } else if (layer0.contains(arr[r + d[0]][c + d[1]])) {
                cnt2++;
            }
        }
        if (cnt == 4) {
            if (cnt2 == 4) {
                arr2[r][c] = 217;
            } else if (cnt2 == 3) {
                if (!layer0.contains(arr[r + 1][c - 1])) {
                    arr2[r][c] = 202;
                } else if (!layer0.contains(arr[r + 1][c + 1])) {
                    arr2[r][c] = 201;
                } else if (!layer0.contains(arr[r - 1][c - 1])) {
                    arr2[r][c] = 220;
                } else if (!layer0.contains(arr[r - 1][c + 1])) {
                    arr2[r][c] = 219;
                }
            } else if (cnt2 == 2) {
                if (!layer0.contains(arr[r - 1][c - 1]) && !layer0.contains(arr[r - 1][c + 1])) {
                    arr2[r][c] = 288 - 18;
                } else if (!layer0.contains(arr[r + 1][c - 1]) && !layer0.contains(arr[r + 1][c + 1])) {
                    arr2[r][c] = 270 - 18;
                } else if (!layer0.contains(arr[r - 1][c + 1]) && !layer0.contains(arr[r + 1][c + 1])) {
                    arr2[r][c] = 272 - 18;
                } else if (!layer0.contains(arr[r - 1][c - 1]) && !layer0.contains(arr[r + 1][c - 1])) {
                    arr2[r][c] = 273 - 18;
                }
            }
        }
        if (cnt == 3) {
            if (!layer0.contains(arr[r - 1][c])) {
                arr2[r][c] = 199;
            } else if (!layer0.contains(arr[r + 1][c])) {
                arr2[r][c] = 235;
            } else if (!layer0.contains(arr[r][c - 1])) {
                arr2[r][c] = 216;
            } else if (!layer0.contains(arr[r][c + 1])) {
                arr2[r][c] = 218;
            }
        } else if (cnt == 2) {
            if (cnt2 == 4) {

            } else {
                if (!layer0.contains(arr[r][c - 1]) && !layer0.contains(arr[r - 1][c])) {
                    arr2[r][c] = 198;
                } else if (!layer0.contains(arr[r][c + 1]) && !layer0.contains(arr[r - 1][c])) {
                    arr2[r][c] = 200;
                } else if (!layer0.contains(arr[r][c - 1]) && !layer0.contains(arr[r + 1][c])) {
                    arr2[r][c] = 234;
                } else if (!layer0.contains(arr[r][c + 1]) && !layer0.contains(arr[r + 1][c])) {
                    arr2[r][c] = 236;
                }
            }
        } else if (cnt == 1) {
            if (layer0.contains(arr[r - 1][c])) {
                arr2[r][c] = 253;
            } else if (layer0.contains(arr[r + 1][c])) {
                arr2[r][c] = 271;
            } else if (layer0.contains(arr[r][c - 1])) {
                arr2[r][c] = 273;
            } else if (layer0.contains(arr[r][c + 1])) {
                arr2[r][c] = 272;
            }
        }
    }

    private static final HashSet<Integer> layer1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 6));

    private void layer1(int[][] arr, int[][] arr2, int r, int c) {
        if (layer1.contains(arr[r][c])) {
            int cnt = 0;
            for (int d[] : direct) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt++;
                } else if (layer1.contains(arr[r + d[0]][c + d[1]]) || arr[r + d[0]][c + d[1]] == 0) {
                    cnt++;
                }
            }
            int cnt2 = 0;
            for (int d[] : direct2) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt2++;
                } else if (layer1.contains(arr[r + d[0]][c + d[1]]) || arr[r + d[0]][c + d[1]] == 0) {
                    cnt2++;
                }
            }
            if (arr[r][c] == 6) {
                layer0(arr, arr2, r, c);
                return;
            }
            if (cnt == 4) {
                if (cnt2 == 4) {
                    arr2[r][c] = 19;
                } else if (cnt2 == 3) {
                    if (!layer1.contains(arr[r + 1][c - 1])) {
                        arr2[r][c] = 58;
                    } else if (!layer1.contains(arr[r - 1][c - 1])) {
                        arr2[r][c] = 22;
                    } else if (!layer1.contains(arr[r + 1][c + 1])) {
                        arr2[r][c] = 57;
                    } else if (!layer1.contains(arr[r - 1][c + 1])) {
                        arr2[r][c] = 21;
                    }
                }
            } else if (cnt == 3) {
                if (!layer1.contains(arr[r - 1][c])) {
                    arr2[r][c] = 55;
                } else if (!layer1.contains(arr[r + 1][c])) {
                    arr2[r][c] = 91;
                    arr[r + 1][c] = -1;
                    arr2[r + 1][c] = 109;
                } else if (!layer1.contains(arr[r][c - 1])) {
                    if (!layer1.contains(arr[r - 1][c - 1])) {
                        arr2[r][c] = 72;
                    } else {
                        arr2[r][c] = 76;
                    }
                } else if (!layer1.contains(arr[r][c + 1])) {
                    if (!layer1.contains(arr[r - 1][c + 1])) {
                        arr2[r][c] = 74;
                    } else {
                        arr2[r][c] = 75;
                    }
                }
            } else if (cnt == 2) {
                if (layer1.contains(arr[r - 1][c]) && layer1.contains(arr[r][c + 1])) {
                    if (!layer1.contains(arr[r - 1][c - 1])) {
                        arr2[r][c] = 90;
                    } else {
                        arr2[r][c] = 94;
                    }
                    arr[r + 1][c] = -1;
                    arr2[r + 1][c] = 108;
                } else if (layer1.contains(arr[r - 1][c]) && layer1.contains(arr[r][c - 1])) {
                    if (!layer1.contains(arr[r - 1][c + 1])) {
                        arr2[r][c] = 92;
                    } else {
                        arr2[r][c] = 93;
                    }
                    arr[r + 1][c] = -1;
                    arr2[r + 1][c] = 110;
                } else if (layer1.contains(arr[r + 1][c]) && layer1.contains(arr[r][c + 1])) {
                    arr2[r][c] = 54;
                } else if (layer1.contains(arr[r + 1][c]) && layer1.contains(arr[r][c - 1])) {
                    arr2[r][c] = 56;
                }
            }
        }
    }

    private static final HashSet<Integer> layer2 = new HashSet<>(Arrays.asList(1, 2, 4, 5, 6));
    private static final int[] smallRocks = { 6, 24, 42 };

    private void layer2(int[][] arr, int[][] arr2, int r, int c) {
        if (arr2[r][c] != -1) {
            return;
        }
        if (arr[r][c] == 2) {
            if (layer1.contains(arr[r - 1][c]) && !layer1.contains(arr[r + 1][c])) {
                arr2[r][c - 1] = 176;
                arr2[r][c] = 177;
                arr2[r][c + 1] = 178;
                arr2[r + 1][c - 1] = 194;
                arr2[r + 1][c] = 195;
                arr2[r + 1][c + 1] = 196;
            } else if (!layer1.contains(arr[r - 1][c]) && layer1.contains(arr[r + 1][c])) {
                arr2[r - 1][c - 1] = 140;
                arr2[r - 1][c] = 141;
                arr2[r - 1][c + 1] = 142;
                arr2[r][c - 1] = 158;
                arr2[r][c] = 159;
                arr2[r][c + 1] = 160;
            } else if (layer1.contains(arr[r][c - 1]) && !layer1.contains(arr[r][c + 1])) {
                arr2[r - 2][c] = 214;
                arr2[r - 1][c] = 232;
                arr2[r][c] = 250;
                arr2[r + 1][c] = 250;
                arr2[r + 2][c] = 268;
                arr2[r + 3][c] = 286;
            } else if (!layer1.contains(arr[r][c - 1]) && layer1.contains(arr[r][c + 1])) {
                arr2[r - 2][c] = 215;
                arr2[r - 1][c] = 233;
                arr2[r][c] = 251;
                arr2[r + 1][c] = 251;
                arr2[r + 2][c] = 269;
                arr2[r + 3][c] = 287;
            }
            return;
        }
        if (arr[r][c] == 4) {
            arr2[r][c - 1] = 137;
            arr2[r][c] = 138;
            arr2[r][c + 1] = 139;
            arr2[r + 1][c - 1] = 155;
            arr2[r + 1][c] = 156;
            arr2[r + 1][c + 1] = 157;
            return;
        }
        if (layer2.contains(arr[r][c])) {
            int cnt = 0;
            for (int d[] : direct) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt++;
                } else if (arr[r + d[0]][c + d[1]] == 1 || arr[r + d[0]][c + d[1]] == -1) {
                    cnt++;
                } else if (arr[r + d[0]][c + d[1]] == 2 || arr[r + d[0]][c + d[1]] == 4) {
                    return;
                }
            }
            for (int d[] : direct2) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt++;
                } else if (arr[r + d[0]][c + d[1]] == 1 || arr[r + d[0]][c + d[1]] == -1) {
                    cnt++;
                } else if (arr[r + d[0]][c + d[1]] == 2) {
                    return;
                }
            }
            double rand = Math.random();
            if (arr[r][c] == 1) {
                if (cnt == 8 && arr[r + 2][c] == 1) {
                    rand -= 0.5;
                } else {
                    return;
                }
            }
            rand += cnt / 15.0;
            if (rand < 0.05) {
                if (arr[r][c] == 5 && arr[r][c] == arr[r + 1][c] && arr[r][c] == arr[r + 1][c - 1]
                        && arr[r][c] == arr[r][c - 1]) {
                    if (arr2[r][c] == -1 && arr2[r - 1][c] == -1 && arr2[r][c + 1] == -1 && arr2[r - 1][c + 1] == -1) {
                        arr2[r - 1][c] = 150;
                        arr2[r - 1][c + 1] = 151;
                        arr2[r][c] = 168;
                        arr2[r][c + 1] = 169;
                    }
                }
            } else if (rand > 0.95) {
                double rand2 = Math.random();
                for (int i = 0; i < smallRocks.length; i++) {
                    if (rand2 > 1 - ((double) i + 1) / smallRocks.length) {
                        if (arr[r][c] != 1) {
                            arr2[r][c] = smallRocks[i];
                        } else {
                            arr2[r][c] = smallRocks[i];
                        }
                        break;
                    }
                }
            }
        }
    }

    private final HashSet<Integer> layer3 = new HashSet<>(Arrays.asList(0, 3));

    private void layer3(int[][] arr, int[][] arr2, int r, int c) {
        if (arr[r][c] == 3) {
            int cnt = 0;
            for (int d[] : direct) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt++;
                } else if (layer3.contains(arr[r + d[0]][c + d[1]]) || arr[r + d[0]][c + d[1]] == 0) {
                    cnt++;
                }
            }
            int cnt2 = 0;
            for (int d[] : direct2) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt2++;
                } else if (layer3.contains(arr[r + d[0]][c + d[1]]) || arr[r + d[0]][c + d[1]] == 0) {
                    cnt2++;
                }
            }
            if (cnt == 4) {
                if (cnt2 == 4) {
                    arr2[r][c] = 19;
                } else if (cnt2 == 3) {
                    if (!layer3.contains(arr[r + 1][c - 1])) {
                        arr2[r][c] = 4;
                    } else if (!layer3.contains(arr[r - 1][c - 1])) {
                        arr2[r][c] = 22;
                    } else if (!layer3.contains(arr[r + 1][c + 1])) {
                        arr2[r][c] = 3;
                    } else if (!layer3.contains(arr[r - 1][c + 1])) {
                        arr2[r][c] = 21;
                    }
                }
            } else if (cnt == 3) {
                if (!layer3.contains(arr[r - 1][c])) {
                    arr2[r][c] = 1;
                } else if (!layer3.contains(arr[r + 1][c])) {
                    arr2[r][c] = 37;
                } else if (!layer3.contains(arr[r][c - 1])) {
                    arr2[r][c] = 18;
                } else if (!layer3.contains(arr[r][c + 1])) {
                    arr2[r][c] = 20;
                }
            } else if (cnt == 2) {
                if (layer3.contains(arr[r][c - 1]) && layer3.contains(arr[r + 1][c])) {
                    arr2[r][c] = 2;
                } else if (layer3.contains(arr[r][c - 1]) && layer3.contains(arr[r - 1][c])) {
                    arr2[r][c] = 38;
                } else if (layer3.contains(arr[r][c + 1]) && layer3.contains(arr[r + 1][c])) {
                    arr2[r][c] = 0;
                } else if (layer3.contains(arr[r][c + 1]) && layer3.contains(arr[r - 1][c])) {
                    arr2[r][c] = 36;
                }
            }
        }
    }

    private final HashSet<Integer> layer4 = new HashSet<>(Arrays.asList(3));

    private void layer4(int[][] arr, int[][] arr2, int r, int c) {
        if (arr[r][c] == 0) {
            arr2[r][c] = 145;
            return;
        }
        if (layer4.contains(arr[r][c])) {
            int cnt = 0;
            for (int d[] : direct) {
                if (r + d[0] == -1 || r + d[0] == arr.length || c + d[1] == -1 || c + d[1] == arr[0].length) {
                    cnt++;
                } else if (layer4.contains(arr[r + d[0]][c + d[1]])) {
                    cnt++;
                }
            }
            if (cnt == 4) {
                if (arr[r + 1][c - 1] == 0) {
                    arr2[r][c] = 147;
                } else if (arr[r + 1][c + 1] == 0) {
                    arr2[r][c] = 148;
                } else if (arr[r - 1][c - 1] == 0) {
                    arr2[r][c] = 129;
                } else if (arr[r - 1][c + 1] == 0) {
                    arr2[r][c] = 130;
                }
            } else if (cnt == 3) {
                if (arr[r - 1][c] == 0) {
                    arr2[r][c] = 127;
                } else if (arr[r + 1][c] == 0) {
                    arr2[r][c] = 163;
                } else if (arr[r][c - 1] == 0) {
                    arr2[r][c] = 144;
                } else if (arr[r][c + 1] == 0) {
                    arr2[r][c] = 146;
                }
            } else if (cnt == 2) {
                if (arr[r][c - 1] == 0 && arr[r - 1][c] == 0) {
                    arr2[r][c] = 126;
                } else if (arr[r][c + 1] == 0 && arr[r - 1][c] == 0) {
                    arr2[r][c] = 128;
                } else if (arr[r][c - 1] == 0 && arr[r + 1][c] == 0) {
                    arr2[r][c] = 162;
                } else if (arr[r][c + 1] == 0 && arr[r + 1][c] == 0) {
                    arr2[r][c] = 164;
                }
            }
        }
    }
}
package edu.school21.World;

import java.util.Arrays;
import java.util.Random;

import edu.school21.app.StaticVariables;
import javafx.util.Pair;

public class WorldMap {

    private final char[][] map;
    private Pair<Integer, Integer> pos;
    private final int size;
    private int enemyCount;
    private final Random random = new Random();
    private final int enemyDensity;

    public WorldMap(int size) {
        this.size = size;
        map = new char[size][size];
        enemyDensity = size / 2;
        enemyCount = size * enemyDensity;
    }

    public void initMap(int diff) {
        pos = new Pair<>(size / 2, size / 2);
        map[size/2][size/2] = 'p';
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == (size / 2) && j == (size / 2)) {
                    continue ;
                }
                if (random.nextInt(2) != 0 && enemyCount != 0) {
                    map[i][j] = random.nextInt(1000) > (50 * diff) ? 'e' : 'E';
                    enemyCount--;
                }
            }
        }
        enemyCount = size + enemyDensity;
    }

    public void printMap() {
        System.out.println(StaticVariables.BORDER);
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public int movePlayer(String direction) throws IndexOutOfBoundsException {
        int horiz = 0, vert = 0, ret = 0;

        switch (direction) {
            case "SOUTH":
                vert = 1;
                break;
            case "NORTH":
                vert = -1;
                break;
            case "EAST":
                horiz = 1;
                break;
            case "WEST":
                horiz = -1;
                break;
        }

        if (map[pos.getKey() + vert][pos.getValue() + horiz] == 'e') {
            ret = 1;
        } else if (map[pos.getKey() + vert][pos.getValue() + horiz] == 'E') {
            ret = 2;
        }

        map[pos.getKey()][pos.getValue()] = 0;
        map[pos.getKey() + vert][pos.getValue() + horiz] = 'p';
        pos = new Pair<>(pos.getKey() + vert, pos.getValue() + horiz);

        return ret;
    }
}

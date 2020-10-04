package org.sloubi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cell {
    private final Map<Maze.DIR, Boolean> walls;

    public Cell() {
        // Les cellules sont fermées au début, les 4 murs existent.
        walls = new HashMap<>();
        walls.put(Maze.DIR.N, true);
        walls.put(Maze.DIR.S, true);
        walls.put(Maze.DIR.E, true);
        walls.put(Maze.DIR.W, true);
    }

    public Boolean hasWall(Maze.DIR direction) {
        return walls.get(direction);
    }

    /**
     * Si un mur n'existe pas alors la cellule est ouverte
     * @return boolean
     */
    public boolean isClosed() {
        Collection<Boolean> w = walls.values();
        for (Boolean wall : w) {
            if (!wall)
                return false;
        }
        return true;
    }

    /**
     * Supprime un mur
     * @param direction
     */
    public void openWall(Maze.DIR direction) {
        walls.put(direction, false);
    }
}

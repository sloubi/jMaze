package org.sloubi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Générateur de labyrinthe utilisant l'algo Recursive Backtracking
public class Maze {
    private Cell[][] grid;
    private int width; // Nombre de cellules horizontales
    private int height; // Nombre de cellules verticales

    public enum DIR {
        N(0, -1), S(0, 1), E(1, 0), W( -1, 0);

        private final int dx;
        private final int dy;
        private DIR opposite;

        DIR(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
    }

    public Maze(int w, int h) {
        width = w;
        height = h;

        init();
    }

    private void init() {
        // Crée la grille avec les dimensions souhaitées
        grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell();
            }
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        return grid[y][x];
    }

    public void gen() {
        init();

        carvePassageFrom(0, 0);

        // Entrée
        grid[0][0].openWall(DIR.W);
        // Sortie
        grid[height - 1][width - 1].openWall(DIR.E);
    }

    private void carvePassageFrom(int x, int y) {
        List<DIR> directions = Arrays.asList(DIR.values());
        Collections.shuffle(directions);

        for (DIR direction : directions) {
            // Calcul de la position de la cellule suivante en fonction de la direction
            int newX = x + direction.dx;
            int newY = y + direction.dy;

            // Si la nouvelle cellule ne sort pas de la grille et qu'elle n'a pas encore été visitée
            if (newY >= 0 && newY < height && newX >= 0 && newX < width && grid[newY][newX].isClosed()) {
                // On ouvre le mur correspondant à la direction dans la cellule courante
                grid[y][x].openWall(direction);

                // On ouvre le mur opposé à la direction dans la nouvelle cellule
                grid[newY][newX].openWall(direction.opposite);

                // On recommence avec la nouvelle cellule
                carvePassageFrom(newX, newY);
            }
        }
    }

    public void displayAscii() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || grid[y][x].hasWall(DIR.N)) {
                    System.out.print("+---");
                }
                else {
                    System.out.print("+   ");
                }
            }
            System.out.println("+");

            for (int x = 0; x < width; x++) {
                if (grid[y][x].hasWall(DIR.W)) {
                    System.out.print("|   ");
                }
                else {
                    System.out.print("    ");
                }
            }

            // Bordure de droite ou sortie
            if (!grid[y][width - 1].hasWall(DIR.E)) {
                System.out.print(" ");
            }
            else {
                System.out.print("|");
            }

            System.out.println();
        }

        // Barre du bas
        for (int x = 0; x < width; x++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

}


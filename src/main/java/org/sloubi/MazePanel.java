package org.sloubi;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private final int cellWidth;
    private final int cellHeight;
    private final Maze maze;

    MazePanel(Maze maze, int cellWidth, int cellHeight) {
        this.maze = maze;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (maze.getCell(x, y).hasWall(Maze.DIR.N)) {
                    g.drawLine(x * cellWidth, y * cellHeight, x * cellWidth + cellWidth, y * cellHeight);
                }
                if (maze.getCell(x, y).hasWall(Maze.DIR.W)) {
                    g.drawLine(x * cellWidth, y * cellHeight, x * cellWidth, y * cellHeight + cellHeight);
                }
            }
        }

        // Mur de droite en prenant en compte la sortie
        for (int y = 0; y < maze.getHeight(); y++) {
            if (maze.getCell(maze.getWidth() - 1, y).hasWall(Maze.DIR.E)) {
                g.drawLine(maze.getWidth() * cellWidth, y * cellHeight, maze.getWidth() * cellWidth, y * cellHeight + cellHeight);
            }
        }

        // Mur du bas
        g.drawLine(0, maze.getHeight() * cellHeight, maze.getWidth() * cellWidth, maze.getHeight() * cellHeight);
    }

    public int getWidth() {
        return cellWidth * maze.getWidth() + 1;
    }

    public int getHeight() {
        return cellHeight * maze.getHeight() + 1;
    }

    public void refresh() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        repaint();
    }
}

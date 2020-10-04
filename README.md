# jMaze

Générateur de labytinthe utilisant un algorithme dit de Recursive Backtracking.

![jMaze](https://sloubi.eu/images/projects/img-20201004-5f79cadacd262.jpg)

Il est aussi possible de générer le labyrinthe en ASCII :
```java
Maze maze = new Maze(8,8);
maze.gen();
maze.displayAscii();
```

```
+---+---+---+---+---+---+---+---+
        |                   |   |
+---+   +---+   +---+---+   +   +
|   |   |       |       |       |
+   +   +   +---+---+   +---+   +
|       |       |       |       |
+   +---+---+   +   +---+   +---+
|               |   |       |   |
+---+---+---+---+   +   +---+   +
|   |       |       |           |
+   +   +   +   +   +---+---+   +
|       |       |               |
+   +---+---+---+---+---+---+---+
|       |           |           |
+---+   +   +---+   +   +---+   +
|           |           |        
+---+---+---+---+---+---+---+---+
```

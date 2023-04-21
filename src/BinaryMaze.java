import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BinaryMaze {
    static final String FILENAME = "maze.dat";

    static List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getNeighbours(boolean[][] maze, Pair<Integer, Integer> from) {
        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> neighbours = new ArrayList<>();
        
        if (from.first > 0 && maze[from.first - 1][from.second]) 
            neighbours.add(new Pair<>(from, new Pair<>(from.first - 1, from.second)));
        if (from.first < maze.length - 1 && maze[from.first + 1][from.second]) 
            neighbours.add(new Pair<>(from, new Pair<>(from.first + 1, from.second)));
        if (from.second > 0 && maze[from.first][from.second - 1]) 
            neighbours.add(new Pair<>(from, new Pair<>(from.first, from.second - 1)));
        if (from.second < maze[0].length - 1 && maze[from.first][from.second + 1]) 
            neighbours.add(new Pair<>(from, new Pair<>(from.first, from.second + 1)));
        
        return neighbours;
    }

    static void binaryMaze(boolean[][] maze, Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> adjencies = new ArrayList<>();
        for (int i = 0; i < maze.length; i++) for (int j = 0; j < maze[0].length; j++) 
            if (maze[i][j]) adjencies.addAll(getNeighbours(maze, new Pair<>(i, j)));

        @SuppressWarnings("unchecked")
        Graph<Pair<Integer, Integer>> graph = new Graph<Pair<Integer, Integer>>(adjencies.toArray(new Pair[0]), true);

        System.out.println(graph.getShortestDistance(from, to));
    }

    public static void main(String[] args) {
        Scanner in = Helper.read(FILENAME);
        Scanner first = new Scanner(in.nextLine());
        int r = first.nextInt(),
            c = first.nextInt(),
            t = first.nextInt();

        boolean[][] maze = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            Scanner line = new Scanner(in.nextLine());
            for (int j = 0; j < c; j++) maze[i][j] = line.nextInt() == 1;
        }

        for (int i = 0; i < t; i++) {
            Scanner line = new Scanner(in.nextLine());
            binaryMaze(
                maze, 
                new Pair<>(line.nextInt(), line.nextInt()),
                new Pair<>(line.nextInt(), line.nextInt())
);}}}
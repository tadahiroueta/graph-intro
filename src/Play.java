import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Play {
    static final String FILENAME = "play.dat";

    static void play(Pair<Integer, Integer>[] adjencies, int[] manuallyKnocked) {
        Graph<Integer> graph = new Graph<Integer>(adjencies, true);

        Set<Integer> connections = new HashSet<>();
        for (int knocked : manuallyKnocked) connections.addAll(graph.getConnections(knocked));

        System.out.println(connections.size());
    }

    public static void main(String[] args) {
        Scanner in = Helper.read(FILENAME);
        int testsNumber = Integer.parseInt(in.nextLine());
        for (int i = 0; i < testsNumber; i++) {

            Scanner firstLine = new Scanner(in.nextLine());
            firstLine.nextInt(); // ignore n
            int m = firstLine.nextInt(),
                l = firstLine.nextInt();

            @SuppressWarnings("unchecked")
            Pair<Integer, Integer>[] adjencies = new Pair[m];
            for (int j = 0; j < m; j++) {
                Scanner line = new Scanner(in.nextLine());
                adjencies[j] = new Pair<Integer, Integer>(line.nextInt(), line.nextInt());
            }

            int[] manuallyKnocked = new int[l];
            for (int j = 0; j < l; j++) manuallyKnocked[j] = Integer.parseInt(in.nextLine());

            play(adjencies, manuallyKnocked);
            try { in.nextLine(); } catch (Exception e) {} // ignore empty line
}}}

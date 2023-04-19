import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Play {
    static final String FILENAME = "play.dat";

    static void play(String[] adjencies, String[] manuallyKnocked) {
        Graph graph = new Graph(adjencies, true);

        Set<String> connections = new HashSet<>();
        for (String knocked : manuallyKnocked) connections.addAll(graph.getConnections(knocked));


        System.out.println(connections);
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

            String[] adjencies = new String[m];
            for (int j = 0; j < m; j++) adjencies[j] = in.nextLine().replace(" ", "");

            String[] manuallyKnocked = new String[l];
            for (int j = 0; j < l; j++) manuallyKnocked[j] = in.nextLine();

            play(adjencies, manuallyKnocked);
            try { in.nextLine(); } catch (Exception e) {} // ignore empty line
}}}

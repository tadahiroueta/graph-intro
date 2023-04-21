import java.util.Scanner;

class Scooby {
    static final String FILENAME = "scooby.dat";

    @SuppressWarnings("rawtypes")
    static void scooby(Pair[] adjencies, String trip) {
        @SuppressWarnings("unchecked")
        Graph<String> graph = new Graph<String>(adjencies, false);

        String from = trip.substring(0, 1);
        String to = trip.substring(1, 2);

        System.out.println(graph.getConnections(from).contains(to) ? "yes" : "no");
    }

    public static void main(String[] args) {
        Scanner in = Helper.read(FILENAME);
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] combos = in.nextLine().split(" ");
            @SuppressWarnings("unchecked")
            Pair<String, String>[] adjencies = new Pair[combos.length];
            for (int j = 0; j < combos.length; j++) adjencies[j] = new Pair<String, String>(
                combos[j].substring(0, 1), 
                combos[j].substring(1, 2)
            );
            scooby(adjencies, in.nextLine());}
    }
}

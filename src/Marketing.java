import java.util.ArrayList;
import java.util.List;

class Marketing {

    @SuppressWarnings("unchecked")
    static Pair<Integer, Integer>[] getAdjencies(String[] compete) {
        List<Pair<Integer, Integer>> adjencies = new ArrayList<>();
        for (int i = 0; i < compete.length; i++)
            for (String neighbour : compete[i].split(" "))
                adjencies.add(new Pair<>(i, neighbour.equals("") ? null : Integer.parseInt(neighbour)));

        return adjencies.toArray(new Pair[0]);
    }

    static long howMany(String[] compete) {
        Pair<Integer, Integer>[] adjencies = getAdjencies(compete);
        Graph<Integer> graph = new Graph<>(adjencies, false);
        return graph.getBinaryCompinationsN();
    }

    public static void main(String[] args) {
        String[] compete = {"1","2","3","0","5","6","4"};
        System.out.println(howMany(compete));
}}

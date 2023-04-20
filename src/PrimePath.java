import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class PrimePath {

    static Set<Integer> getPrimes(int digitsN) {
        Set<Integer> primes = new HashSet<>();

        outerloop:
        for (int i = 2; i < Math.pow(10, digitsN); i++) {
            for (int prime : primes) if (i % prime == 0) continue outerloop;
            primes.add(i);
        }

        // only the numbers with the right amount of digits
        return primes.stream()
            .filter(n -> n >= Math.pow(10, digitsN - 1)) 
            .collect(Collectors.toSet());
    }

    static Pair<Integer, Integer>[] getAdjencies(Set<Integer> primes) {
        List<Pair<Integer, Integer>> adjencies = new ArrayList<>();
        
        for (int from : primes) {
            String fromString = String.valueOf(from);

            for (int i = 0; i < fromString.length(); i++) {
                for (int j = 0; j < 10; j++) {
                    int to = Integer.parseInt( // changing one digit at a time
                        fromString.substring(0, i) + j + fromString.substring(i + 1)
                    );
                    
                    if (primes.contains(to)) adjencies.add(new Pair<>(from, to));
        }}}
        return adjencies.toArray(new Pair[0]);
    }

    static void primePath(Graph<Integer> graph, int from, int to) { 
        System.out.println(graph.getShortestPath(from, to));
        System.out.println(graph.getShortestPathA(from, to));
    }

    public static void main(String[] args) {
        String[] input = {
            "1033 8179",
            "1373 8017",
            "1033 1033"
        };

        Graph<Integer> graph = new Graph<>(getAdjencies(getPrimes(4)), false);

        for (String line : input) {
            String[] numbers = line.split(" ");
            int from = Integer.parseInt(numbers[0]);
            int to = Integer.parseInt(numbers[1]);

            primePath(graph, from, to);
}}}

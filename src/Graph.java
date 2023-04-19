import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Graph<T> {
    Map<T, List<T>> map = new HashMap<>();

    Graph(Pair<T, T>[] adjencies, boolean isDirected) {
        for (Pair<T, T> adjency : adjencies) {
        
            if (!map.containsKey(adjency.first)) map.put(adjency.first, new ArrayList<>());
            map.get(adjency.first).add(adjency.second);

            if (!isDirected) {
                if (!map.containsKey(adjency.second)) map.put(adjency.second, new ArrayList<>());
                map.get(adjency.second).add(adjency.first);
            }
    }}

    Set<T> getConnections(Set<T> connections, T from) {
        connections.add(from);
        if (!map.containsKey(from)) return connections;

        for (T neighbour : map.get(from)) 
            if (!connections.contains(neighbour)) 
                connections.addAll(getConnections(connections, neighbour));

        return connections;
    }

    Set<T> getConnections(T from) { return getConnections(new HashSet<>(), from); }

    @Override
    public String toString() { return map.toString(); }
}
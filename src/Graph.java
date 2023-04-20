import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Graph<T> {
    Map<T, Set<T>> map = new HashMap<>();

    Graph(Pair<T, T>[] adjencies, boolean isDirected) {
        for (Pair<T, T> adjency : adjencies) {
        
            if (!map.containsKey(adjency.first)) map.put(adjency.first, new HashSet<>());
            map.get(adjency.first).add(adjency.second);

            if (!isDirected) {
                if (!map.containsKey(adjency.second)) map.put(adjency.second, new HashSet<>());
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

    int getShortestPath(T from, T to) {
        Queue<T> queue = new CircularQueue<T>();
        Set<T> visited = new HashSet<>();

        queue.add(from);
        int stepN = 0;
        while (!queue.isEmpty()) {
            for (int i = 0, limit = queue.size(); i < limit; i++) {
                T current = queue.poll();

                if (current.equals(to)) return stepN;
                if (visited.contains(current) || !map.containsKey(current)) continue;

                visited.add(current);
                queue.addAll(map.get(current));
            }
            stepN++;
        }
        return -1;
    }

    @Override
    public String toString() { return map.toString(); }
}
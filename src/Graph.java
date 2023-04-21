import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Graph<T> {
    Map<T, Set<T>> map = new HashMap<>();

    Graph(Pair<T, T>[] adjencies, boolean isDirected) {
        for (Pair<T, T> adjency : adjencies) {
        
            if (!map.containsKey(adjency.first)) map.put(adjency.first, new HashSet<>());
            if (adjency.second == null) continue; // solitary node

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

    int getShortestDistance(T from, T to) {
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

    Stack<T> getShortestPath(T from, T to) {
        Queue<Stack<T>> queue = new CircularQueue<Stack<T>>();
        Set<T> visited = new HashSet<>();

        Stack<T> stack = new Stack<>();
        stack.add(from);
        queue.add(stack);
        while (!queue.isEmpty()) for (int i = 0, limit = queue.size(); i < limit; i++) {
                Stack<T> current = queue.poll();

                if (current.peek().equals(to)) return current;
                if (visited.contains(current.peek()) || !map.containsKey(current.peek())) continue;

                visited.add(current.peek());
                for (T neighbour : map.get(current.peek())) {
                    @SuppressWarnings("unchecked")
                    Stack<T> newStack = (Stack<T>) current.clone();
                    newStack.add(neighbour);
                    queue.add(newStack);
        }}
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    int getShortestDistanceWeightedNodes(T from, T to) {
        if (!(from instanceof Trio)) throw new IllegalArgumentException("T must be a Trio");

        Queue<Pair<T, Integer>> queue = new CircularQueue<>();
        Map<T, Integer> distances = new HashMap<>();
        
        queue.add(new Pair<>(from, 0));
        
        while (!queue.isEmpty()) {
            Pair<Trio, Integer> current = (Pair<Trio, Integer>) queue.poll();
            Trio trio = current.first;
            int currentWeight = (int) trio.third;
            int totalWeight = current.second + currentWeight;
            if (trio.equals(from)) currentWeight = totalWeight = 0;

            if (currentWeight == Integer.MAX_VALUE) continue; // too heavy

            // found one solution
            if (trio.equals(to)) {
                int previousSolution = distances.getOrDefault(trio, Integer.MAX_VALUE);
                distances.put((T) trio, Math.min(totalWeight, previousSolution));
                continue;
            }

            if (!map.containsKey(trio)) continue; // nowhere to go

            // if we've been here before and it was lighter before
            int previousDistance = distances.getOrDefault(trio, Integer.MAX_VALUE);
            if (totalWeight >= previousDistance) continue;
            distances.put((T) trio, totalWeight);
            
            // keep going
            for (T neighbour : map.get(trio)) queue.add(new Pair<>(neighbour, totalWeight));
        }
        return distances.getOrDefault(to, -1);
    }

    Map<T, Boolean> getBinaryNetwork(Map<T, Boolean> network, T from, boolean isPositive) {
        if (network.containsKey(from)) // been here before
            // null for non-binary network
            return network.get(from) != isPositive ? null : network; 

        network.put(from, isPositive);
        for (T neighbour : map.get(from)) {
            Map<T, Boolean> newNetwork = getBinaryNetwork(network, neighbour, !isPositive);
            if (newNetwork == null) return null; // non-binary network
            network = newNetwork;
        }
        return network;
    }

    Set<T> getBinaryNetwork(T from) {
        Map<T, Boolean> network = getBinaryNetwork(new HashMap<>(), from, true); 
        return network != null ? network.keySet() : new HashSet<>();
    }

    int getNetworksN() {
        Set<T> nodes = new HashSet<>(map.keySet());
        for (int i = 0; true; i++) {
            if (nodes.isEmpty()) return i;

            T node = nodes.iterator().next();
            Set<T> network = getBinaryNetwork(node);
            if (network.isEmpty()) return -1; 
            nodes.removeAll(network);
    }}

    long getBinaryCompinationsN() {
        int networksN = getNetworksN();
        return networksN == -1 ? -1 : (long) Math.pow(2, networksN); 
    }

    @Override
    public String toString() { return map.toString(); }
}
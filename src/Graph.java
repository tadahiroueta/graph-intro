import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<String, List<String>> map = new HashMap<>();

    Graph(String[] adjencies) {
        for (String adjency : adjencies) {
            
            String from = adjency.substring(0, 1);
            String to = adjency.substring(1, 2);

            if (!map.containsKey(from)) map.put(from, new ArrayList<>());
            map.get(from).add(to);

            if (!map.containsKey(to)) map.put(to, new ArrayList<>());
            map.get(to).add(from);
    }}

    boolean isConnected(List<String> travelled, String from, String to) {
        if (!map.containsKey(from)) return false;
        if (from.equals(to)) return true;
        
        travelled.add(from);
        for (String neighbour : map.get(from)) 
            if (!travelled.contains(neighbour) && isConnected(travelled, neighbour, to)) 
                return true;

        return false;
    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    protected static Scanner read(String filename) {
        try { return new Scanner(new File(filename)); } 
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.exit(1);
        }
        return null;
    }

    protected static Map<String, List<String>> getGraph(String[] adjencies) {
        Map<String, List<String>> map = new HashMap<>();
        for (String adjency : adjencies) {
            String from = adjency.substring(0, 1);
            String to = adjency.substring(1, 2);

            if (!map.containsKey(from)) map.put(from, new ArrayList<>());
            map.get(from).add(to);

            if (!map.containsKey(to)) map.put(to, new ArrayList<>());
            map.get(to).add(from);
        }
        return map;
    }

    protected static boolean isConnected(Map<String, List<String>> map, List<String> travelled, String from, String to) {
        if (!map.containsKey(from)) return false;
        if (from.equals(to)) return true;
        
        travelled.add(from);
        for (String neighbour : map.get(from)) 
            if (!travelled.contains(neighbour) && isConnected(map, travelled, neighbour, to)) 
                return true;

        return false;
    }

    protected static void scooby(String[] adjencies, String trip) {
        Map<String, List<String>> map = getGraph(adjencies);

        String from = trip.substring(0, 1);
        String to = trip.substring(1, 2);

        System.out.println(isConnected(map, new ArrayList<>(), from, to) ? "yes" : "no");
    }

    public static void scooby(String filename) {
        Scanner in = read(filename);
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) scooby(in.nextLine().split(" "), in.nextLine());
    }

    public static void main(String[] args) {
        scooby("scooby.dat");
    }
}
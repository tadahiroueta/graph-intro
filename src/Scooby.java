import java.util.ArrayList;
import java.util.Scanner;

public class Scooby {
    protected static void scooby(String[] adjencies, String trip) {
        Graph graph = new Graph(adjencies);

        String from = trip.substring(0, 1);
        String to = trip.substring(1, 2);

        System.out.println(graph.isConnected(new ArrayList<>(), from, to) ? "yes" : "no");
    }

    public static void scooby(String filename) {
        Scanner in = Helper.read(filename);
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) scooby(in.nextLine().split(" "), in.nextLine());
    }

    public static void main(String[] args) {
        scooby("scooby.dat");
    }
}

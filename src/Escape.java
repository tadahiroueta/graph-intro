import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Escape {
    static final int SIZE = 501;
    
    @SuppressWarnings("unchecked")
    static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>[] getBounds(String[] areas) {
        return Arrays.stream(areas)
            .map(s -> s.split(" "))
            .map(s -> new Pair<>(new Pair<>(Integer.parseInt(s[0]), Integer.parseInt(s[1])),
                                 new Pair<>(Integer.parseInt(s[2]), Integer.parseInt(s[3]))))
            .toArray(Pair[]::new);
    }

    /** Top left and bottom right corner */
    static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coolerBounds(Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> bounds) { return new Pair<>(
        new Pair<>(
            Math.min(bounds.first.first, bounds.second.first), 
            Math.min(bounds.first.second, bounds.second.second)
        ),
        new Pair<>(
            Math.max(bounds.first.first, bounds.second.first), 
            Math.max(bounds.first.second, bounds.second.second)
    ));}

    static int[][] getWeights(Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>[] harmfulBounds, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>[] deadlyBounds) {
        int[][] weights = new int[SIZE][SIZE];

        for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> bounds : harmfulBounds) {
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> theCoolerBounds = coolerBounds(bounds);
            for (int i = theCoolerBounds.first.first; i <= theCoolerBounds.second.first; i++)
                for (int j = theCoolerBounds.first.second; j <= theCoolerBounds.second.second; j++)
                    weights[i][j] = 1;
        }

        for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> bounds : deadlyBounds) {
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> theCoolerBounds = coolerBounds(bounds);
            for (int i = theCoolerBounds.first.first; i <= theCoolerBounds.second.first; i++)
                for (int j = theCoolerBounds.first.second; j <= theCoolerBounds.second.second; j++)
                    weights[i][j] = Integer.MAX_VALUE;

}        return weights;
    }

    @SuppressWarnings("unchecked")
    static Trio<Integer, Integer, Integer>[] getNeighbours(int[][] weights, int x, int y) {
        List<Trio<Integer, Integer, Integer>> neighbours = new ArrayList<>();

        if (x > 0)
            neighbours.add(new Trio<>(x - 1, y, weights[x - 1][y]));
        if (x < SIZE - 1)
            neighbours.add(new Trio<>(x + 1, y, weights[x + 1][y]));
        if (y > 0)
            neighbours.add(new Trio<>(x, y - 1, weights[x][y - 1]));
        if (y < SIZE - 1)
            neighbours.add(new Trio<>(x, y + 1, weights[x][y + 1]));

        return neighbours.toArray(new Trio[0]);
    }

    @SuppressWarnings("unchecked")
    static Pair<Trio<Integer, Integer, Integer>, Trio<Integer, Integer, Integer>>[] getAdjencies(int[][] weights) {
        List<Pair<Trio<Integer, Integer, Integer>, Trio<Integer, Integer, Integer>>> adjencies = 
            new ArrayList<>();

        for (int i = 0; i < SIZE; i++) for (int j = 0; j < SIZE; j++) {
            Trio<Integer, Integer, Integer>[] neighbours = getNeighbours(weights, i, j);
            for (Trio<Integer, Integer, Integer> neighbour : neighbours)
                adjencies.add(new Pair<>(new Trio<>(i, j, weights[i][j]), neighbour));
        }
        return adjencies.toArray(new Pair[0]);
    }

    public static void main(String[] args) {
        String[] harmful = {
            "468 209 456 32",
            "71 260 306 427",
            "420 90 424 492",
            "374 253 54 253",
            "319 334 152 431",
            "38 93 204 84",
            "246 0 434 263",
            "12 18 118 461",
            "215 462 44 317",
            "447 214 28 475",
            "3 89 38 125",
            "157 108 138 264",
            "363 17 333 387",
            "457 362 396 324",
            "95 27 374 175",
            "381 196 265 302",
            "105 255 253 134",
            "0 308 453 55",
            "169 28 313 498",
            "103 247 165 376",
            "264 287 363 407",
            "185 255 110 415",
            "475 126 293 112",
            "285 200 66 484",
            "60 178 461 301",
            "347 352 470 479",
            "433 130 383 370",
            "405 378 117 377",
            "403 324 369 133",
            "12 63 174 309",
            "181 0 356 56",
            "473 380 315 378"
        },
            deadly = {
                "250 384 355 234",
                "28 155 470 4",
                "333 405 12 456",
                "329 221 239 215",
                "334 20 429 338",
                "85 42 188 388",
                "219 187 12 111",
                "467 453 358 133",
                "472 172 257 288",
                "412 246 431 86",
                "335 22 448 47",
                "150 14 149 11",
                "224 136 466 328",
                "369 209 184 262",
                "274 488 425 195",
                "55 82 279 253",
                "153 201 65 228",
                "208 230 132 223",
                "369 305 397 267",
                "200 145 98 198",
                "422 67 252 479",
                "231 252 401 190",
                "312 20 0 350",
                "406 72 207 294",
                "488 329 338 326",
                "117 264 497 447",
                "491 341 139 438",
                "40 413 329 290",
                "148 245 53 386",
                "147 70 186 131",
                "300 407 71 183",
                "300 186 251 198",
                "178 67 487 77",
                "98 158 55 433",
                "167 231 253 90",
                "268 406 81 271",
                "312 161 387 153",
                "33 442 25 412",
                "56 69 177 428",
                "5 92 61 247"
            };

        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>[] harmfulBounds = getBounds(harmful),
            deadlyBounds = getBounds(deadly);    

        int[][] weights = getWeights(harmfulBounds, deadlyBounds);

        Pair<Trio<Integer, Integer, Integer>, Trio<Integer, Integer, Integer>>[] adjencies =
            getAdjencies(weights);

        Graph<Trio<Integer, Integer, Integer>> graph = new Graph<>(adjencies, false);

        System.out.println(graph.getShortestDistanceWeightedNodes(new Trio<>(0, 0, weights[0][0]), new Trio<>(500, 500, weights[SIZE - 1][SIZE - 1])));
    }
}

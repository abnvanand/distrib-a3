package server;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class Graph {
    private HashMap<String, HashSet<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
        System.out.println("Graph()");
    }

    boolean addEdge(String node1, String node2) {
        adjacencyList.putIfAbsent(node1, new HashSet<>());
        adjacencyList.putIfAbsent(node2, new HashSet<>());

        adjacencyList.get(node1).add(node2);
        adjacencyList.get(node2).add(node1);

        System.out.println("Graph:addEdge()");
        System.out.println(adjacencyList);
        return true;
    }

    Integer bfs(String source, String destination) {
        Queue<String> queue = new ArrayDeque<>();
        HashMap<String, Integer> distance = new HashMap<>();

        // Initialize distances
        for (String node : adjacencyList.keySet()) {
            distance.put(node, -1); // -1 signifies unvisited node
        }

        distance.put(source, 0);    // set distance of source to 0
        queue.add(source);

        while (!queue.isEmpty()) {
            String node = queue.remove();

            for (String neighbour : adjacencyList.get(node)) {
                if (distance.get(neighbour) == -1) {   // unprocessed node
                    distance.put(neighbour, distance.get(node) + 1);
                    queue.add(neighbour);
                    if (neighbour.equals(destination)) {
                        break;
                    }
                }
            }
        }

        return distance.getOrDefault(destination, -1);
    }


    @Override
    public String toString() {
        System.out.println("Graph:toString" + this.adjacencyList);
        return this.adjacencyList.toString();
    }
}

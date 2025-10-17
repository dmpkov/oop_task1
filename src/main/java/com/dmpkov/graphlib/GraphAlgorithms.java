package com.dmpkov.graphlib;

import java.util.*;
public class GraphAlgorithms {


    public static <T> List<Vertex<T>> breadthFirstSearch(Graph<T> graph, T startValue) {
        Vertex<T> startVertex = new Vertex<>(startValue);
        if (!graph.getVertices().contains(startVertex)) {
            return Collections.emptyList();
        }

        List<Vertex<T>> traversalOrder = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            traversalOrder.add(current);
            for (Vertex<T> neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return traversalOrder;
    }

    public static <T> List<Vertex<T>> depthFirstSearch(Graph<T> graph, T startValue) {
        Vertex<T> startVertex = new Vertex<>(startValue);
        if (!graph.getVertices().contains(startVertex)) {
            return Collections.emptyList();
        }

        List<Vertex<T>> result = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        dfsRecursive(graph, startVertex, visited, result);

        return result;
    }

    private static <T> void dfsRecursive(Graph<T> graph, Vertex<T> current, Set<Vertex<T>> visited, List<Vertex<T>> result) {
        visited.add(current);
        result.add(current);

        for (Vertex<T> neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(graph, neighbor, visited, result);
            }
        }
    }
    public static <T> Map<Vertex<T>, Double> dijkstra(Graph<T> graph, T startValue) {
        Vertex<T> startVertex = new Vertex<>(startValue);
        if (!graph.getVertices().contains(startVertex)) {
            return Collections.emptyMap();
        }

        Map<Vertex<T>, Double> distances = new HashMap<>();
        PriorityQueue<Vertex<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Vertex<T> vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(startVertex, 0.0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<T> current = pq.poll();

            for (Vertex<T> neighbor : graph.getNeighbors(current)) {
                Double weight = graph.getWeight(current, neighbor);
                if (weight == null) continue;

                double newDist = distances.get(current) + weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }
        return distances;
    }
}

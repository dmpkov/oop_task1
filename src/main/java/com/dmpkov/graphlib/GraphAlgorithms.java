package com.dmpkov.graphlib;

import java.util.*;
public class GraphAlgorithms {
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

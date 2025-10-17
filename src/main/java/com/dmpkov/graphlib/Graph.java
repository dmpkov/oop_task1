package com.dmpkov.graphlib;

import java.util.*;

public class Graph<T> {

    private final boolean isDirected;

    private final Map<Vertex<T>, Map<Vertex<T>, Double>> adjacencyMap;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        this.adjacencyMap = new HashMap<>();
    }

    public void addVertex(T value) {
        adjacencyMap.putIfAbsent(new Vertex<>(value), new HashMap<>());
    }

    public void addEdge(T sourceValue, T destValue, double weight) {
        Vertex<T> source = new Vertex<>(sourceValue);
        Vertex<T> dest = new Vertex<>(destValue);

        addVertex(sourceValue);
        addVertex(destValue);

        adjacencyMap.get(source).put(dest, weight);

        if (!isDirected) {
            adjacencyMap.get(dest).put(source, weight);
        }
    }

    public Set<Vertex<T>> getVertices() {
        return adjacencyMap.keySet();
    }

    public Set<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        return adjacencyMap.getOrDefault(vertex, Collections.emptyMap()).keySet();
    }

    public Double getWeight(Vertex<T> source, Vertex<T> destination) {
        return adjacencyMap.getOrDefault(source, Collections.emptyMap()).get(destination);
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T> vertex : adjacencyMap.keySet()) {
            sb.append(vertex).append(" -> ").append(adjacencyMap.get(vertex)).append("\n");
        }
        return sb.toString();
    }

    public String toEdgeListString() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<T> source : adjacencyMap.keySet()) {
            Map<Vertex<T>, Double> neighbors = adjacencyMap.get(source);

            for (Map.Entry<Vertex<T>, Double> edge : neighbors.entrySet()) {
                Vertex<T> destination = edge.getKey();

                if (!isDirected && source.hashCode() > destination.hashCode()) {
                    continue;
                }

                sb.append(source.getValue())
                        .append(" ")
                        .append(destination.getValue())
                        .append(" ")
                        .append(edge.getValue())
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}

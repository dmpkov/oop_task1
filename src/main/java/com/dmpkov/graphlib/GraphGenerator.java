package com.dmpkov.graphlib;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {

    public static Graph<Integer> createRandomGraph(int numVertices, int numEdges, boolean isDirected, int maxWeight) {
        if (numVertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        Graph<Integer> graph = new Graph<>(isDirected);
        Random random = new Random();

        for (int i = 0; i < numVertices; i++) {
            graph.addVertex(i);
        }

        if (numVertices < 2 || numEdges == 0) {
            return graph;
        }

        Set<String> existingEdges = new HashSet<>();
        int edgesAdded = 0;

        while (edgesAdded < numEdges) {
            int source = random.nextInt(numVertices);
            int dest = random.nextInt(numVertices);

            if (source == dest) {
                continue;
            }

            String edgeKey = source + "-" + dest;
            String reverseEdgeKey = dest + "-" + source;

            if (existingEdges.contains(edgeKey)) {
                continue;
            }
            if (!isDirected && existingEdges.contains(reverseEdgeKey)) {
                continue;
            }

            int weight = random.nextInt(maxWeight) + 1;
            graph.addEdge(source, dest, weight);
            existingEdges.add(edgeKey);
            edgesAdded++;
        }

        return graph;
    }
}

package com.dmpkov.graphlib;

import java.util.Random;

public class GraphGenerator {

    public static Graph<Integer> createRandomGraph(int numVertices, int numEdges, boolean isDirected, double maxWeight) {
        if (numVertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        Graph<Integer> graph = new Graph<>(isDirected);
        Random random = new Random();

        for (int i = 0; i < numVertices; i++) {
            graph.addVertex(i);
        }

        if (numVertices == 0 || numEdges == 0) {
            return graph;
        }

        // 2. Add random edges
        for (int i = 0; i < numEdges; i++) {
            int source = random.nextInt(numVertices);
            int dest = random.nextInt(numVertices);

            if (source == dest) {
                i--;
                continue;
            }

            double weight = 1.0 + random.nextDouble() * (maxWeight - 1.0);

            graph.addEdge(source, dest, weight);
        }

        return graph;
    }
}

package com.dmpkov.graphlib;

import java.util.Objects;
public class Vertex<T> {
    private final T value;

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    @Override
    public boolean equals(Object o) {
        return Objects.equals(value, ((Vertex<?>) o).value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

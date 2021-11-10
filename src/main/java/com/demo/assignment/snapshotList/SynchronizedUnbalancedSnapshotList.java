package com.demo.assignment.snapshotList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class SynchronizedUnbalancedSnapshotList<E> extends ArrayList<E> implements SnapshotList<E> {
    private int currentVersion = 0;

    /*This map stores the version number as key and the end index for that version as the value.
     * */
    private final LinkedHashMap<Integer, Integer> versions = new LinkedHashMap<>();

    @Override
    public void dropPriorSnapshots(int version) {
        if (version >= currentVersion) {
            throw new InvalidVersionException("Expected version lesser than the current version");
        }
        for (int i = 1; i < version; i++) {
            versions.remove(i);
        }
    }

    @Override
    public E getAtVersion(int index, int version) {
        if (version > currentVersion) {
            throw new InvalidVersionException("Queried version less than or equal to current version");
        }
        if (index > versions.get(version)) {
            throw new IndexOutOfBoundsException("Could not find index " + index + " in version " + version);
        } else {
            return get(index);
        }
    }

    @Override
    public int snapshot() {
        versions.put(++currentVersion, size() - 1);
        return currentVersion;
    }

    @Override
    public int version() {
        return currentVersion;
    }

    @Override
    public String toString() {
        return "Latest Version : " + version() + ", List Elements : " + super.toString();
    }

    /*Blocking all operations which can remove elements or randomly insert elements at some index.
    * This list is supposed to grow only by appending elements.*/
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Changes to existing elements is not supported");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Adding element at random location is not supported");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Adding element at random location is not supported");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("Removing elements is not supported");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException("Replacing elements is not supported");
    }
}

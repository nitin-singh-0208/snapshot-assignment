package com.demo.assignment.snapshotList;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class SynchronizedUnbalancedSnapshotList<E> extends CopyOnWriteArrayList<E> implements SnapshotList<E> {
    private final AtomicInteger currentVersion = new AtomicInteger(0);

    /*This map stores the version number as key and the end index for that version as the value.
     * */
    private final ConcurrentHashMap<Integer, Integer> versions = new ConcurrentHashMap<>();

    @Override
    public void dropPriorSnapshots(int version) {
        if (version > currentVersion.get()) {
            throw new InvalidVersionException("Expected version lesser than the current version");
        }
        for (int i = 1; i < version; i++) {
            versions.remove(i);
        }
    }

    @Override
    public E getAtVersion(int index, int version) {
        if (version > currentVersion.get()) {
            throw new InvalidVersionException("Queried version less than or equal to current version");
        }
        if (index > versions.get(version)) {
            throw new IndexOutOfBoundsException("Could not find value at index " + index + " in version " + version);
        } else {
            return get(index);
        }
    }

    @Override
    public synchronized int snapshot() {
        //Check to not create multiple snapshots when no change has been made to the list.
        if (versions.get(currentVersion.get()) == null || (versions.get(currentVersion.get()) < (size() - 1))) {
            versions.put(currentVersion.incrementAndGet(), size() - 1);
        }
        return currentVersion.get();
    }

    @Override
    public int version() {
        return currentVersion.get();
    }

    public List<E> getSnapshotAtVersion(int version) {
        if (version > currentVersion.get()) {
            throw new InvalidVersionException("Entered version " + version + " should be less than the latest version of list ");
        }
        if (versions.get(version) == null || versions.get(version) == 0) {
            throw new InvalidVersionException("Version " + version + " not found");
        }
        return subList(0, versions.get(version) + 1);

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

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("Changes to existing elements is not supported");
    }

}
